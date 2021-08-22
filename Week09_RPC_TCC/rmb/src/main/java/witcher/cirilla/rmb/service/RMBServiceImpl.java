package witcher.cirilla.rmb.service;

import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import witcher.cirilla.common.api.RMBService;
import witcher.cirilla.rmb.dao.RMBMapper;

import java.math.BigDecimal;

@Service("rmbService")
public class RMBServiceImpl implements RMBService {

    @Autowired
    private RMBMapper rmbMapper;

    @Override
    public BigDecimal selectBalanceByUserId(Long userId) {
        return rmbMapper.selectBalanceByUserId(userId);
    }

    @Override
    @HmilyTCC(confirmMethod = "confirmUpdate", cancelMethod = "cancelUpdate")
    public int subtractBalanceByUserId(Long userId, BigDecimal balance, BigDecimal newBalance) {
        return rmbMapper.lockBalanceByUserId(userId, balance, newBalance);
    }

    @Override
    public int plusBalanceByUserId(Long userId, BigDecimal balance) {
        return rmbMapper.plusBalanceByUserId(userId, balance);
    }

    public int confirmUpdate(Long userId, BigDecimal balance, BigDecimal newBalance) {
        return rmbMapper.updateBalanceByUserId(userId, balance, newBalance);
    }

    public int cancelUpdate(Long userId, BigDecimal balance, BigDecimal newBalance) {
        return rmbMapper.cancelLockBalanceByUserId(userId, balance, newBalance);
    }

}
