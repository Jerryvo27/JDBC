package Section19JDBC;

import java.sql.SQLException;

public interface CategoryDao {

    void input(Category category);

    void info(Category category);

    void add(Category category) throws SQLException;

    void update(Category category);

    void delete(int id) throws SQLException;

    Category getOne(int id);



}
