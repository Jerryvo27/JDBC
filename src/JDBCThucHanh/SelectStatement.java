package JDBCThucHanh;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectStatement {
    public static void main(String[] args) {

        Connection conn = JDBCConnection.getJDBCConnection(); {

            try {
                Statement statement = conn.createStatement();
                final String sql = "SELECT * FROM hello.demo where id=2";
                ResultSet rs =statement.executeQuery(sql);
                while (rs.next()) {
                    int id =rs.getInt("id");
                    String name = rs.getString("name");
                    System.out.println(id+"   "+name);
                }
            } catch (SQLException e) {
                e.printStackTrace();


            }

        }
    }
}
