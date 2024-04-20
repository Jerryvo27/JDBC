package Section19JDBC;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BilldaoImpl implements Billdao {
    @Override
    public void add(Bill bill) throws SQLException {
          String sql = "INSERT INTO bill(quantity,price,product,buyDate) VALUES(?,?,?,?)";
        Connection conn = JDBCConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, bill.getQuantity());
        ps.setDouble(2, bill.getPrice());
        ps.setInt(3, bill.getProduct().getId());
        ps.setDate(4, new java.sql.Date(bill.getBuyDate().getTime()));

        ps.executeUpdate();
// update product quantity
        int quantityAfter = bill.getProduct().getQuantity() - bill.getQuantity();
        ProductDao productDao = new ProductDaoImpl();
        productDao.updateQuantity(bill.getProduct().getId(), quantityAfter);
    }

    @Override
    public List<Bill> findBuyDate(Date fromDate, Date toDate) {
        List<Bill> bills = new ArrayList<Bill>();
        try{
            String sql = "SELECT bill.*, product From bill INNER JOIN product_name ON bill.product = product.id where buyDate >= ? and buyDate <= ? ORDER BY buyDate ASC";
            Connection connection = JDBCConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDate(1, new java.sql.Date(fromDate.getTime()));
            preparedStatement.setDate(2, new java.sql.Date(toDate.getTime()));

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()){
                Bill b = new Bill();
                b.setId(rs.getInt("id"));
                b.setQuantity(rs.getInt("quantity"));
                b.setPrice(rs.getDouble("price"));
                b.setBuyDate(rs.getDate("buyDate"));

                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setName(rs.getString("name"));

                b.setProduct(p);

                bills.add(b);

            }
        } catch (Exception e){
            e.printStackTrace();
        }


        return bills;
    }

    @Override
    public void input(Bill bill) {
        Scanner sc = new Scanner(System.in);
        ProductDao productDao = new ProductDaoImpl();

        // nhap va tim product theo id
        while (true) {
            System.out.println("Dien id san pham muon mua: ");
            int id = sc.nextInt();

            bill.setProduct(productDao.getOne(id));

            if (bill.getProduct() != null) {
                break;
            } else {
                System.out.println("Khong co san pham nay. Chon lai.");
            }
        }

        while (true) {
            System.out.print("Nhap so luong muon mua: ");
            bill.setQuantity(sc.nextInt());

            if (bill.getQuantity() > bill.getProduct().getQuantity()) {
                System.out.println("Khong du so luong");
            } else {
                break;
            }
        }

        bill.setPrice(bill.getProduct().getPrice());

        while (true) {
            try {
                // lay ngay hien tai
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                System.out.print("Nhap ngay mua: ");
                String date = new Scanner(System.in).nextLine();

                bill.setBuyDate(dateFormat.parse(date));
            } catch (ParseException ex) {
                System.out.println("Nhap lai ngay");
            }
        }

    }

    @Override
    public void info(Bill bill) {
        System.out.println("ID: " + bill.getId());
        System.out.println("Quantity: " + bill.getQuantity());
        System.out.println("Price: " + bill.getPrice());
        System.out.println("Total Price: " + bill.getQuantity() * bill.getPrice());

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        System.out.println("Buy Date: " + dateFormat.format(bill.getBuyDate()));

        System.out.println("----PRODUCT INFO----");
        ProductDao productDao = new ProductDaoImpl();
        productDao.info(bill.getProduct());
    }


    }

