package Section19JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
    public static Connection getConnection() {
        final String url = "jdbc:mysql://localhost:3306/section18";
        final String user = "root";
        final String password = "Hoaihoai2211";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        if (JDBCConnection.getConnection() != null) {
            System.out.println("thanh cong");
        } else {
            System.out.println("that bai");
        }
    }
}