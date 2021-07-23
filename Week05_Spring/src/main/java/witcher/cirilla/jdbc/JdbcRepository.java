package witcher.cirilla.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class JdbcRepository {

    @Autowired
    private DataSource datasource;

    public void test() throws Exception {
        Connection connection = datasource.getConnection();

        insert(connection);

        select(connection);
    }

    public void insert(Connection connection) {
        PreparedStatement preparedStatement = null;
        try {
            connection.setAutoCommit(false);
            String inertSQL = "insert into t_article (id, title, content, created, modified, categories, tags, allow_comment, thumbnail)\n" +
                    "VALUE (NULL, ?, ?, now(), now(), ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(inertSQL);
            preparedStatement.setString(1, "title");
            preparedStatement.setString(2, "content");
            preparedStatement.setString(3, "categories");
            preparedStatement.setString(4, "tags");
            preparedStatement.setInt(5, 1);
            preparedStatement.setString(6, "thumbnail");
            preparedStatement.executeUpdate();
            connection.commit();
        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void select(Connection connection) {
        PreparedStatement preparedStatement = null;
        try {
            String selectSql = "select * from t_article where title = ?";
            preparedStatement = connection.prepareStatement(selectSql);
            preparedStatement.setString(1, "title");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("id: " + resultSet.getInt(1));
                System.out.println("title: " + resultSet.getString(2));
                System.out.println("content: " + resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
