package witcher.cirilla.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import witcher.cirilla.test.dao.TestDao;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SpringBootTest
class TestApplicationTests {

    //======================== ShardingSphere 数据分片
    @Resource
    private DataSource dataSource;

    @Test
    void test01()  {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        try {
            connection = dataSource.getConnection();

            connection.setAutoCommit(false);

            // update
            String updateSql = "update order_master set city = ? where customer_id = ?";
            preparedStatement = connection.prepareStatement(updateSql);
            preparedStatement.setString(1, "GuangZhou");
            preparedStatement.setInt(2, 666);
            preparedStatement.executeUpdate();

            // select
            String selectSql = "select city from order_master where customer_id =?";
            preparedStatement1 = connection.prepareStatement(selectSql);
            preparedStatement1.setInt(1, 666);
            ResultSet resultSet = preparedStatement1.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }

            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            if (preparedStatement1 != null) {
                try {
                    preparedStatement1.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
        }
    }

    //======================== ShardingSphere XA

    @Autowired
    private TestDao testDao;

    @Test
    void test02() {
        testDao.insert();
    }

}
