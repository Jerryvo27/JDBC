package Section19JDBC;

import java.util.Date;

public class Bill {
    private int id;
    private int quantity;
    private double price;
    private Product product;

    private Date buyDate;

    public Bill(int id, int quantity, double price, Product product, Date buyDate) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.product = product;
        this.buyDate = buyDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public Bill() {
    }
}
