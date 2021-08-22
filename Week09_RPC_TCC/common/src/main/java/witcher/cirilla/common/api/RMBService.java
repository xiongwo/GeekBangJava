package witcher.cirilla.common.api;

import org.dromara.hmily.annotation.Hmily;

import java.math.BigDecimal;

public interface RMBService {

    BigDecimal selectBalanceByUserId(Long userId);

    @Hmily
    int subtractBalanceByUserId(Long userId, BigDecimal balance, BigDecimal newBalance);

    int plusBalanceByUserId(Long userId, BigDecimal balance);

}
