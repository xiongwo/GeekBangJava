package witcher.cirilla.test.dao;

import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Repository
public class TestDao {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Transactional
    @ShardingTransactionType(TransactionType.XA)
    public void insert() {
        jdbcTemplate.execute("update order_master set city = ? where customer_id = ?", (PreparedStatementCallback<Object>) preparedStatement -> {
            preparedStatement.setString(1, "HongKong");
            preparedStatement.setInt(2, 666);
            preparedStatement.executeUpdate();
            return null;
        });
    }

}
