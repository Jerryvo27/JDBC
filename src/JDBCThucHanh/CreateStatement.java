package JDBCThucHanh;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateStatement {
    public static void createTable(){
        Connection connection = JDBCConnection.getJDBCConnection();
        try {
            Statement statement = connection.createStatement();
            String sql = "Create table person(id int,)";
            int rs = statement.executeUpdate(sql);
            System.out.println(rs);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
       public static void deleteTable(){
           Connection connection = JDBCConnection.getJDBCConnection();
           try {
               Statement statement = connection.createStatement();
               String sql = "DROP table person";
               int rs = statement.executeUpdate(sql);
               System.out.println(rs);
           } catch (SQLException e){
               e.printStackTrace();
           }
       }
    public static void main(String[] args) {
//        createTable();
        deleteTable();
    }
}
