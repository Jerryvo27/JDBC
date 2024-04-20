package JDBCThucHanh;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class MetaData {
    public static void main(String[] args) throws Exception{
        Connection connection = JDBCConnection.getJDBCConnection();
        Statement statement = connection.createStatement();

        String sql = " Select * from demo";
        ResultSet resultSet = statement.executeQuery(sql);
        ResultSetMetaData metaData = resultSet.getMetaData();
        System.out.println(metaData.getColumnCount());
        System.out.println(metaData.getColumnName(1));
        System.out.println(metaData.getColumnName(2));
        System.out.println(metaData.getColumnTypeName(1));
        System.out.println(metaData.getTableName(1));

    }
}
