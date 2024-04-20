package JDBCThucHanh;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCTransaction {
    public static void main(String[] args) throws SQLException {
        Connection connection = JDBCConnection.getJDBCConnection();
        Statement statement1 = connection.createStatement();
//        Statement statement2 = connection.createStatement();
        connection.setAutoCommit(false);
        String sql1 = "INSERT INTO demo(id,name) values (6,'Hoa')";
        String sql2 = "DELETE FROM demo WHERE ID = 4 ";

        statement1.executeUpdate(sql2);
        statement1.executeUpdate(sql1);

        connection.commit();
    }
}
