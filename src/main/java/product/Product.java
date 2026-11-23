package product;

public abstract class Product {

    private String name;
    private double price;
    private int stock;


    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }


    // GETTERS

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }


    // SETTERS

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


    // STOCK

    public void reduceStock() {
        if (stock > 0) {
            stock--;
        }
    }


    // ABSTRACT METHOD
    // Each subclass gives its own version.

    public abstract String displayProduct();


    @Override
    public String toString() {
        return String.format(
            "%-10s PHP %.2f  (%d pcs)",
            name,
            price,
            stock
        );
    }
}