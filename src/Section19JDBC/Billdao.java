package Section19JDBC;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public interface Billdao {
    void add(Bill bill) throws SQLException;

    List<Bill> findBuyDate(Date fromDate, Date toDate);
    void input(Bill bill);

    void info(Bill bill);

}
