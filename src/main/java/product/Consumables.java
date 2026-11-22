package product;

public class Consumables extends Product {
    // food and drink
    
    public Consumables(String name, double price, int stock) {
        super(name, price, stock);
    }

    public void displayProduct() {
        System.out.println("Name: " + getName()+ ". Price: " + getPrice()+ ", Stock: " + getStock());
    }
}
