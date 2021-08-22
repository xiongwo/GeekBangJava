package witcher.cirilla.dollar.dao;

import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

public interface DollarMapper {

    @Select("SELECT balance FROM t_dollar WHERE user_id = #{userId} AND lock_balance is null")
    BigDecimal selectBalanceByUserId(Long userId);

    @Update("UPDATE t_dollar SET lock_balance = #{newBalance} WHERE user_id = #{userId} AND balance = #{balance} AND lock_balance is null")
    int lockBalanceByUserId(Long userId, BigDecimal balance, BigDecimal newBalance);

    @Update("UPDATE t_dollar SET balance = #{newBalance}, lock_balance = null WHERE user_id = #{userId} AND balance = #{balance} AND lock_balance = #{newBalance}")
    int updateBalanceByUserId(Long userId, BigDecimal balance, BigDecimal newBalance);

    @Update("UPDATE t_dollar SET lock_balance = null WHERE user_id = #{userId} AND balance = #{balance} AND lock_balance = #{newBalance}")
    int cancelLockBalanceByUserId(Long userId, BigDecimal balance, BigDecimal newBalance);

    @Update("UPDATE t_dollar SET balance = balance + #{balance} WHERE user_id = #{userId}")
    int plusBalanceByUserId(Long userId, BigDecimal balance);

}
