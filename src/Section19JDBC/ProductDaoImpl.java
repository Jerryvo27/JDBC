package Section19JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductDaoImpl implements ProductDao {
    @Override
    public void input(Product product) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter quantity: ");
        product.setQuantity(sc.nextInt());

        System.out.println("Enter price: ");
        product.setPrice(sc.nextDouble());


        System.out.println("Enter name: ");
        product.setName(sc.nextLine());

        while (true) {
            System.out.println("Enter category_id: ");
            CategoryDao categoryDao = new CategoryDaoImpl();
            Category c = categoryDao.getOne(sc.nextInt());

            product.setCategory(c);

            if (product.getCategory() != null) {
                break;
            }
        }

    }

    @Override
    public void info(Product product) {
        System.out.println("ID: " + product.getId());
        System.out.println("Name: " + product.getName());
        System.out.println("Quantity: " + product.getQuantity());
        System.out.println("Price: " + product.getPrice());
        if (product.getCategory() != null) {
            CategoryDao categoryDao = new CategoryDaoImpl();
            categoryDao.info(product.getCategory());
        }



    }

    @Override
    public void add(Product product) throws Exception {
// tu gen id
        String sql = "INSERT INTO product(category_id,quantity,price,name) VALUES(?,?,?,?)";
        Connection conn = JDBCConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

        ps.setInt(1, product.getCategory().getId());
        ps.setInt(2, product.getQuantity());
        ps.setDouble(3, product.getPrice());
        ps.setString(4,product.getName());

        ps.executeUpdate();

        ResultSet rs = ps.getGeneratedKeys();
        while (rs.next()) {
            int id = rs.getInt(1);
            product.setId(id);
        }
    }

    @Override
    public void update(Product product) {
        try {
            String sql = "UPDATE product SET category_id = ?, quantity = ?, price = ?,name = ? WHERE id = ?";
            Connection conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(5, product.getId());
            ps.setInt(1, product.getCategory().getId());
            ps.setInt(2, product.getQuantity());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getName());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) throws Exception {
        String sql = "DELETE FROM product WHERE id = ?";
        Connection conn = JDBCConnection.getConnection();

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        ps.executeUpdate();
    }

    @Override
    public List<Product> searchByName(String name) {
        List<Product> list = new ArrayList<Product>();

        try {
            String sql = "SELECT pr.id,pr.name,pr.quantity,pr.price,pr.category_id,ct.name as 'c_name' FROM category "
                    + " INNER JOIN product AS pr ON category.id = pr.category_id  WHERE pr.name LIKE ?";
            Connection conn = JDBCConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + name + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();

                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getDouble("price"));

                Category c = new Category();
                c.setId(rs.getInt("category_id"));
                c.setName(rs.getString("c_name"));

                p.setCategory(c);

                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Product> searchByPrice(int fromPrice, int toPrice) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT pr.id,pr.name,pr.quantity,pr.price,pr.category_id, ct.name as 'c_name' FROM category"
                    + " INNER JOIN product AS pr category.id = pr.category_id WHERE pr.price >= ? AND pr.price <= ?";

            Connection conn = JDBCConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, fromPrice);
            ps.setInt(2, toPrice);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();

                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getDouble("price"));

                Category c = new Category();
                c.setId(rs.getInt("category_id"));
                c.setName(rs.getString("c_name"));
                p.setCategory(c);

                list.add(p);
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public List<Product> searchByNameOfCategory(String nameOfCategory) {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "SELECT pr.id, pr.name, pr.quantity, pr.price, pr.category_id, ct.name as 'c_name' FROM product AS pr "
                    + " INNER JOIN category ct AS pr ON ct.id = pr.category_id  WHERE ct.name LIKE ?";

            Connection conn = JDBCConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + nameOfCategory + "%");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();

                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getDouble("price"));

                Category c = new Category();
                c.setId(rs.getInt("category_id"));
                c.setName(rs.getString("c_name"));
                p.setCategory(c);

                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Product getOne(int id) {
        try {
            String sql = "SELECT * FROM product WHERE product.id = ?";
            Connection conn = JDBCConnection.getConnection();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));
                p.setQuantity(rs.getInt("quantity"));
                p.setPrice(rs.getDouble("price"));

                Category c = new Category();
                c.setId(rs.getInt("category_id"));

                p.setCategory(c);
                return p;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateQuantity(int id, int amount) {
        try {
            String sql = "UPDATE product SET quantity = ? WHERE product.id = ?";

            Connection conn = JDBCConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, amount);
            ps.setInt(2, id);

            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    }

