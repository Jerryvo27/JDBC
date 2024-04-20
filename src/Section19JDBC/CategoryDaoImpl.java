package Section19JDBC;

import Section19JDBC.Category;
import Section19JDBC.CategoryDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class CategoryDaoImpl implements CategoryDao {

    @Override
    public void input(Category category) {

            Scanner sc = new Scanner(System.in);
            System.out.println("Nhap name: ");
            category.setName(sc.nextLine());

        }



    @Override
    public void info(Category category) {
        System.out.println("ID: " + category.getId());
        System.out.println("Name: " + category.getName());
    }

    @Override
    public void add(Category category) throws SQLException {
        String sql = "INSERT INTO category(name) VALUES(?)";
        Connection conn = JDBCConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, category.getName());

        ps.executeUpdate();
    }

    @Override
    public void update(Category category) {
        try {
            String sql = "UPDATE category SET name = ? WHERE id = ?";
            Connection conn = JDBCConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, category.getName());
            ps.setInt(2, category.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM category WHERE id = ?";

        Connection conn = JDBCConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        ps.executeUpdate();
    }




    @Override
    public Category getOne(int id) {
        try {
            String sql = "SELECT * FROM category WHERE id = ?";

            Connection conn = JDBCConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category c = new Category();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));

                return c;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
