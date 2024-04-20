package JDBCRowSet;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.SQLException;

public class RowSet {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        JdbcRowSet jdbcRowSet = RowSetProvider.newFactory().createJdbcRowSet();

        jdbcRowSet.setUrl("jdbc:mysql://localhost:3306/hello");
        jdbcRowSet.setUsername("root");
        jdbcRowSet.setPassword("");

        String sql = "Select * from demo";
        jdbcRowSet.setCommand(sql);
        jdbcRowSet.execute();

        while (jdbcRowSet.next()){
            System.out.println(jdbcRowSet.getInt("id") + jdbcRowSet.getString("name"));
        }
    }
}
