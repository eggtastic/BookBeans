package storefront;
import product.Product;


abstract class Storefront {

    private String name;
    private double earnings;
    private int customers;


    Storefront(String name, double earnings, int customers) {
        this.name = name;
        this.earnings = earnings;
        this.customers = customers;
    }


    public String getName() {
        return name;
    }

    public double getEarnings() {
        return earnings;
    }

    public int getCustomers() {
        return customers;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }


    abstract void displayStorefront();

    abstract void BuyProduct(Product product);
}