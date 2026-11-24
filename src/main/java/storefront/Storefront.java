package storefront;

import product.Product;

public abstract class Storefront {

    private String name;
    private double earnings;


    public Storefront(
        String name,
        double earnings
    ) {

        this.name = name;
        this.earnings = earnings;
    }


    // GETTERS

    public String getName() {
        return name;
    }

    public double getEarnings() {
        return earnings;
    }


    // SETTERS

    public void setName(String name) {
        this.name = name;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }


    // ABSTRACT CRUD METHODS

    public abstract void addProduct(Product product);

    public abstract void updateProduct(Product product);

    public abstract void deleteProduct(Product product);


    // ABSTRACT METHODS

    public abstract void displayStorefront();

    public abstract void BuyProduct(Product product);
}