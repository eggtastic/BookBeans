package product;

public class Consumables extends Product {
    // food and drink
    
    Consumables(String name, double price, int stock) {
        super(name, price, stock);
    }

    void displayProduct() {
        System.out.println("Name: " + getName()+ ". Price: " + getPrice()+ ", Stock: " + getStock());
    }
}
 