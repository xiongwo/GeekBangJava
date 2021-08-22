package witcher.cirilla.dollar.service;

import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import witcher.cirilla.common.api.DollarService;
import witcher.cirilla.dollar.dao.DollarMapper;

import java.math.BigDecimal;

@Service("dollarService")
public class DollarServiceImpl implements DollarService {

    @Autowired
    private DollarMapper dollarMapper;

    @Override
    public BigDecimal selectBalanceByUserId(Long userId) {
        return dollarMapper.selectBalanceByUserId(userId);
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmUpdate", cancelMethod = "cancelUpdate")
    public int subtractBalanceByUserId(Long userId, BigDecimal balance, BigDecimal newBalance) {
        return dollarMapper.lockBalanceByUserId(userId, balance, newBalance);
    }

    @Override
    public int plusBalanceByUserId(Long userId, BigDecimal balance) {
        return dollarMapper.plusBalanceByUserId(userId, balance);
    }

    public int confirmUpdate(Long userId, BigDecimal balance, BigDecimal newBalance) {
        return dollarMapper.updateBalanceByUserId(userId, balance, newBalance);
    }

    public int cancelUpdate(Long userId, BigDecimal balance, BigDecimal newBalance) {
        return dollarMapper.cancelLockBalanceByUserId(userId, balance, newBalance);
    }

}
