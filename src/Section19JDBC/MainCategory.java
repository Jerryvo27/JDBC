package Section19JDBC;

import java.sql.SQLException;
import java.util.Scanner;

public class MainCategory {
    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Create Category");
            System.out.println("2. Update Category");
            System.out.println("3. Delete Category");
            System.out.println("4. Exit");
            System.out.println("Moi ban chon: ");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            if (choice == 1) {
                createCategory();
            } else if (choice == 2) {
                updateCategory();
            } else if (choice == 3) {
                deleteCategory();
            } else if (choice == 4) {
                break;
            } else {
                System.out.println("Nhap tu 1-4");
            }
        }
    }

    public static void createCategory() {
        System.out.println("Nhap thong tin");
        CategoryDao categoryDao = new CategoryDaoImpl();

        Category category = new Category();

        categoryDao.input(category);
        try {
            categoryDao.add(category);
            System.out.println("Thanh cong");
        } catch (SQLException e) {
            System.out.println("That bai");
            e.printStackTrace();
        }
    }

    public static void updateCategory() {
        Category category = new Category();

        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap id can update: ");
        int id = sc.nextInt();
        category.setId(id);

        CategoryDao categoryDao = new CategoryDaoImpl();
        categoryDao.input(category);

        categoryDao.update(category);

        System.out.println("Thanh cong");
    }

    public static void deleteCategory() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap id can delete: ");

        int id = sc.nextInt();
        CategoryDao categoryDao = new CategoryDaoImpl();
        try {
            categoryDao.delete(id);
            System.out.println("Thanh cong");
        } catch (SQLException e) {
            System.out.println("That bai");
            e.printStackTrace();
        }
    }
}
