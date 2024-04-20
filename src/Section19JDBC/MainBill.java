package Section19JDBC;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainBill {
    public static void main(String[] args) throws ParseException {
        while (true) {
            System.out.println("1. Mua san pham");
            System.out.println("2. Tim theo ngay mua");
            System.out.println("3. Thoat");
            System.out.println("Moi ban chon: ");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            if (choice == 1) {
                buyProduct();
            } else if (choice == 2) {
                find();
            } else if (choice == 3) {
                break;
            } else {
                System.out.println("Chon tu 1-3");
            }
        }
    }

    public static void buyProduct() {
        Bill b = new Bill();
        Billdao billDao = new BilldaoImpl();
        billDao.input(b);
        try {
            billDao.add(b);
            System.out.println("Thanh cong");
        } catch (SQLException e) {
            System.out.println("That bai");
            e.printStackTrace();
        }
    }

    public static void find() {
        Scanner sc = new Scanner(System.in);
        Billdao billDao = new BilldaoImpl();

        Date from = new Date();// ngay hien tai
        Date to = new Date();
        while (true) {
            try {
                // lay ngay hien tai
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                System.out.print("Nhap ngay from: ");

                from = dateFormat.parse(sc.nextLine());
                break;
            } catch (ParseException ex) {
                System.out.println("Nhap lai ngay");
            }
        }
        while (true) {
            try {
                // lay ngay hien tai
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                System.out.print("Nhap ngay to: ");

                to = dateFormat.parse(sc.nextLine());
                break;
            } catch (ParseException ex) {
                System.out.println("Nhap lai ngay");
            }
        }

        List<Bill> bills = billDao.findBuyDate(from, to);
        for (Bill bill : bills)
            billDao.info(bill);
}}

