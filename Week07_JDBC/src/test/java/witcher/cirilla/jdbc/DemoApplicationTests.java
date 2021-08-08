package witcher.cirilla.jdbc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

@SpringBootTest
class DemoApplicationTests {

    @Autowired
    private DataSource datasource;

    private Connection connection;

    @BeforeEach
    void initConnection() throws SQLException {
        connection = datasource.getConnection();
        connection.setAutoCommit(false);
    }

    @AfterEach
    void closeConnection() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    void insert01() throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Statement statement = connection.createStatement();
        for (int i = 1; i <= 1000000; i++) {
            statement.execute("insert into order_cart values (null, " + i + ", " + i + ", " + i + ", 1.1, now(), now())");
        }

        stopWatch.stop();
    }

    @Test
    void insert02() throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Statement statement = connection.createStatement();
        for (int i = 1; i <= 1000000; i++) {
            statement.addBatch("insert into order_cart values (null, " + i + ", " + i + ", " + i + ", 1.1, now(), now())");
            if (i % 60000 == 0) {
                statement.executeBatch();
            }
        }

        statement.executeBatch();

        stopWatch.stop();
    }

    @Test
    void insert03() throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Statement statement = connection.createStatement();
        StringBuilder stringBuilder = new StringBuilder("insert into order_cart values ");
        for (int i = 1; i <= 1000000; i++) {
            stringBuilder.append("(null, " + i + ", " + i + ", " + i + ", 1.1, now(), now())");
            if (i == 1000000) {
                stringBuilder.append(";");
            } else {
                stringBuilder.append(",");
            }
        }

        statement.execute(stringBuilder.toString());

        stopWatch.stop();
    }

    @Test
    void insert04() throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        PreparedStatement preparedStatement = connection.prepareStatement("insert into order_cart values (null, ?, ?, ?, 1.1, now(), now())");

        for (int i = 1; i <= 1000000; i++) {
            preparedStatement.setInt(1, i);
            preparedStatement.setInt(2, i);
            preparedStatement.setInt(3, i);
            preparedStatement.executeUpdate();
        }

        stopWatch.stop();
    }

    @Test
    void insert05() throws SQLException {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        PreparedStatement preparedStatement = connection.prepareStatement("insert into order_cart values (null, ?, ?, ?, 1.1, now(), now())");
        for (int i = 1; i <= 1000000; i++) {
            preparedStatement.setInt(1, i);
            preparedStatement.setInt(2, i);
            preparedStatement.setInt(3, i);
            preparedStatement.addBatch();

            if (i % 60000 == 0) {
                preparedStatement.executeBatch();
            }
        }

        preparedStatement.executeBatch();

        stopWatch.stop();
    }

}
