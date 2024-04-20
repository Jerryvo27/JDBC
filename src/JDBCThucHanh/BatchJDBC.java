package JDBCThucHanh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class BatchJDBC {
    public static void main(String[] args) throws Exception {
        Connection connection = JDBCConnection.getJDBCConnection();

        String sql1 = "INSERT INTO DEMO(id,name) values (1,'A')";
        String sql2 = "INSERT INTO DEMO(id,name) values (2,'B')";
        String sql3 = "INSERT INTO DEMO(id,name) values (3,'C')";

        Statement statement = connection.createStatement();

        statement.addBatch(sql1);
        statement.addBatch(sql2);
        statement.addBatch(sql3);

//        statement.executeBatch();

        String sql4 = "INSERT INTO DEMO(id,name) values (?,?)";

        PreparedStatement preparedStatement = connection.prepareStatement(sql4);
        preparedStatement.setInt(1,4);
        preparedStatement.setString(2,"B");
        preparedStatement.addBatch();

        preparedStatement.setInt(1,5);
        preparedStatement.setString(2,"B");
        preparedStatement.addBatch();

        preparedStatement.setInt(1,6);
        preparedStatement.setString(2,"B");
        preparedStatement.addBatch();

        preparedStatement.executeBatch();




    }
}
