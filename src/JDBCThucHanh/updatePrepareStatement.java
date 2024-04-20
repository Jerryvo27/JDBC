package JDBCThucHanh;

import com.mysql.cj.protocol.PacketReceivedTimeHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class updatePrepareStatement {
    public static void insertRecords(int id, String name){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "Insert into demo (id, name) values(?,?)";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateRecords(int id, String name){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "update demo set name = ? where id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteRecords(int id){
        Connection connection = JDBCConnection.getJDBCConnection();
        String sql = "delete from demo where id= ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            int rs = preparedStatement.executeUpdate();
            System.out.println(rs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        insertRecords(5,"d");
        updateRecords(3,"a");
        deleteRecords(2);
    }
}
