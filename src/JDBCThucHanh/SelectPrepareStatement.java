package JDBCThucHanh;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectPrepareStatement {
    public static void main(String[] args) {
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "Select * From demo where id = ? or name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,4);
            preparedStatement.setString(2,"vu");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next())
                System.out.println(rs.getInt("id") + rs.getString("name"));
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

