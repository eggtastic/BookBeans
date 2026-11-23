package storefront;
import product.Product;


abstract class Storefront {

    private String name;
    private double earnings;


    Storefront(String name, double earnings) {
        this.name = name;
        this.earnings = earnings;
    }


    public String getName() {
        return name;
    }

    public double getEarnings() {
        return earnings;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }


    abstract void displayStorefront();

    abstract void BuyProduct(Product product);
}