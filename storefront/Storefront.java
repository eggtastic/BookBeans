package storefront;
import java.util.ArrayList;

abstract class Storefront {
    private String name;
    private double earnings;
    private int customers;


    Storefront(String name, double earnings, int customers) {
        this.name = name;
        this.earnings = earnings;
        this.customers = customers;
    }


    public String getName()  {
        return name;
    }
    public double getEarnings() {
        return earnings;
    }
    public int getCustomers()    {
        return customers;
    }

    public void setEarnings(double earnings) { this.earnings = earnings; }

    abstract void displayStorefront();
    abstract void BuyProduct();
}

class Bookstore extends Storefront {
    private int BookStock;
    private int MovieStock;
    private ArrayList<Book> Books = new ArrayList<>();
    private ArrayList<Movie> Movies = new ArrayList<>();

    public String getBookStock() {
        return BookStock;
    }
    public String getMovieStock() {
        return MovieStock;
    }

    Bookstore(String name, double earnings, int customers,  int BookStock, int MovieStock) {
        super(name, earnings, customers);
        this.BookStock = BookStock;
        this.MovieStock = MovieStock;
    }
}

class Cafe extends Storefront {
    private int MenuItems;
    private ArrayList<Consumables> Menu = new ArrayList<>();

    public int getMenuItems() {
        return MenuItems;
    }

    Cafe(String name, double earnings, int customers, int MenuItems) {
        super(name, earnings, customers);
        this.MenuItems = MenuItems;
}