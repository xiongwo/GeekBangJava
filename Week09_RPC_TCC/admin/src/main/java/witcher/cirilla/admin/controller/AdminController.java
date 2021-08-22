package witcher.cirilla.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import witcher.cirilla.common.api.DollarService;
import witcher.cirilla.common.api.RMBService;

import java.math.BigDecimal;

@RestController
public class AdminController {

    @Autowired
    private RMBService rmbService;

    @Autowired
    private DollarService dollarService;

    @PutMapping("dollar")
    public String exchangeDollar(@RequestParam Long userId, @RequestParam BigDecimal rmbAmount) {
        // 1
        BigDecimal rmbBalance = rmbService.selectBalanceByUserId(userId);
        if (rmbBalance == null) {
            return "account is invalid";
        }
        BigDecimal newBalance = rmbBalance.subtract(rmbAmount);
        if (newBalance.compareTo(new BigDecimal("0.0")) <= 0) {
            return "balance is not enough!";
        }

        // 2
        rmbService.subtractBalanceByUserId(userId, rmbBalance, newBalance);

        // 3
        int rows = dollarService.plusBalanceByUserId(userId, rmbAmount.divide(new BigDecimal("7"), BigDecimal.ROUND_UP));
        if (rows <= 0) {
            return "account is invalid";
        }

        return "trade success!";
    }

    @PutMapping("rmb")
    public String exchangeRMB(@RequestParam Long userId, @RequestParam BigDecimal dollarAmount) {
        // 1
        BigDecimal dollarBalance = dollarService.selectBalanceByUserId(userId);
        if (dollarBalance == null) {
            return "account is invalid";
        }
        BigDecimal newBalance = dollarBalance.subtract(dollarAmount);
        if (newBalance.compareTo(new BigDecimal("0.0")) <= 0) {
            return "balance is not enough!";
        }

        // 2
        dollarService.subtractBalanceByUserId(userId, dollarBalance, newBalance);

        // 3
        int rows = rmbService.plusBalanceByUserId(userId, dollarAmount.divide(new BigDecimal("7"), BigDecimal.ROUND_UP));
        if (rows <= 0) {
            return "account is invalid";
        }

        return "trade success!";
    }

}
