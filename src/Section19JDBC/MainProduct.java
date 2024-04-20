package Section19JDBC;

import java.util.List;
import java.util.Scanner;

public class MainProduct {
    public static void main(String[] args) {
        while (true) {
            System.out.println("1. Them Product");
            System.out.println("2. Sua Product");
            System.out.println("3. Xoa Product");
            System.out.println("4. Tim theo ten Product");
            System.out.println("5. Tim theo gia Product");
            System.out.println("6. Tim theo ten cua Category");
            System.out.println("7. Tim theo id Product");
            System.out.println("8. Thay doi so luong Product");
            System.out.println("9. Exit");
            System.out.println("Moi ban chon: ");

            Scanner sc = new Scanner(System.in);
            int choice = sc.nextInt();
            if (choice == 1) {
                createProduct();
            } else if (choice == 2) {
                updateProduct();
            } else if (choice == 3) {
                deleteProduct();
            } else if (choice == 4) {
                searchByName();
            } else if (choice == 5) {
                searchByPrice();
            } else if (choice == 6) {
                searchByNameOfCategory();
            } else if (choice == 7) {
                getOne();
            } else if (choice == 8) {
                updateQuantity();
            } else if (choice == 9) {
                break;
            } else {
                System.out.println("Nhap tu 1 - 9");
            }
        }
    }

    public static void createProduct() {
        System.out.println("Nhap thong tin");

        ProductDao productDao = new ProductDaoImpl();
        Product product = new Product();
        productDao.input(product);

        try {
            productDao.add(product);
            System.out.println("Thanh cong");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap id can update: ");

        int id = sc.nextInt();
        Product product = new Product();
        product.setId(id);

        ProductDao productDao = new ProductDaoImpl();
        productDao.input(product);
        productDao.update(product);

        System.out.println("Thanh cong");
    }

    public static void deleteProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap id can delete: ");
        int id = sc.nextInt();

        ProductDao productDao = new ProductDaoImpl();
        try {
            productDao.delete(id);
            System.out.println("Thanh cong");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void searchByName() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ten product can tim: ");
        String name = sc.nextLine();

        ProductDao productDao = new ProductDaoImpl();
        List<Product> productList = productDao.searchByName(name);

        if (productList.isEmpty()) {
            System.out.println("Khong co du lieu");
        } else {
            System.out.println("Product can tim theo ten:");
            for (Product product : productList) {
                productDao.info(product);
            }
        }

    }

    public static void searchByPrice() {
        Scanner sc = new Scanner(System.in);
        ProductDao productDao = new ProductDaoImpl();

        System.out.print("Gia tu: ");
        int from = sc.nextInt();

        System.out.print("Den : ");
        int to = sc.nextInt();

        List<Product> productList = productDao.searchByPrice(from, to);
        for (Product product : productList)
            productDao.info(product);

    }

    public static void searchByNameOfCategory() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap ten Category can tim: ");
        String name = sc.nextLine();

        ProductDao productDao = new ProductDaoImpl();
        List<Product> productList = productDao.searchByNameOfCategory(name);

        if (productList.isEmpty()) {
            System.out.println("Khong co du lieu");
        } else {
            System.out.println("Product can tim theo ten Category:");
            for (Product product : productList) {
                productDao.info(product);
            }
        }

    }

    public static Product getOne() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap id of Product can tim: ");
        int id = sc.nextInt();

        ProductDao productDao = new ProductDaoImpl();
        Product product = productDao.getOne(id);

        if (product == null) {
            System.out.println("Khong co du lieu");
        } else {
            System.out.println("Product can tim theo id");
            productDao.info(product);
        }
        return product;
    }

    public static void updateQuantity() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap id of Product can update: ");
        int id = sc.nextInt();

        sc = new Scanner(System.in);
        System.out.println("Nhap quantity of Product can update: ");
        int amount = sc.nextInt();

        ProductDao productDao = new ProductDaoImpl();

        Product product = new Product();
        product.setId(id);
        product.setQuantity(amount);

        productDao.updateQuantity(id, amount);
        System.out.println("Thanh cong");
    }
}
