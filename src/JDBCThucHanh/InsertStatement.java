package JDBCThucHanh;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertStatement {
    public static void insertRecords(int id, String name){
        Connection connection = JDBCConnection.getJDBCConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "Insert into demo(id, name) values (4,'A')";
            int rs = statement.executeUpdate(sql);
            System.out.println(rs);
        }catch (SQLException e) {
            e.printStackTrace();
        }
            }
    public static void updateRecords(int id, String newName){
        Connection connection = JDBCConnection.getJDBCConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "update demo set name ='"+newName+"' where id = " +id;
            int rs = statement.executeUpdate(sql);
            System.out.println(rs);
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteRecords (int id){
        Connection connection = JDBCConnection.getJDBCConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "truncate table demo";
            int rs = statement.executeUpdate(sql);
            System.out.println(rs);
        }catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
//        insertRecords(4,"ha");
//        updateRecords(4,"hana");
        deleteRecords(1);
    }
}
