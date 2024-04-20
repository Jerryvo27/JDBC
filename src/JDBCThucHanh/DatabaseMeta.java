package JDBCThucHanh;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

public class DatabaseMeta {
    public static void main(String[] args) throws SQLException {
        Connection connection = JDBCConnection.getJDBCConnection();

        DatabaseMetaData databaseMeta = connection.getMetaData();

        System.out.println(databaseMeta.getDatabaseProductName());
        System.out.println(databaseMeta.getUserName());
        System.out.println(databaseMeta.getURL());

    }
}
