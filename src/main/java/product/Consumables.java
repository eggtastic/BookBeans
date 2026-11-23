package product;

public class Consumables extends Product {


    public Consumables(
        String name,
        double price,
        int stock
    ) {

        super(name, price, stock);
    }


    @Override
    public String displayProduct() {

        return "Name: " + getName()
            + "\nPrice: PHP " + String.format("%.2f", getPrice())
            + "\nStock: " + getStock();
    }
}