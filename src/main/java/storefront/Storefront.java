package storefront;
import java.util.ArrayList;
import product.Book;
import product.Movie;
import product.Consumables;

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

    public int getBookStock() {
        return BookStock;
    }
    public int getMovieStock() {
        return MovieStock;
    }

    Bookstore(String name, double earnings, int customers,  int BookStock, int MovieStock) {
        super(name, earnings, customers);
        this.BookStock = BookStock;
        this.MovieStock = MovieStock;
    };

    public void addBook(Book book) {
        Books.add(book);
    }

    public void addMovie(Movie movie) {
        Movies.add(movie);
    }

    @Override
    public void displayStorefront() {
        for (Book book : Books) {
            book.displayProduct();
        }

        for (Movie movie : Movies) {
            movie.displayProduct();
        }
    }

    public ArrayList<Book> getBooks() {
        return Books;
    }

    public ArrayList<Movie> getMovies() {
        return Movies;
    }

    @Override
    public void BuyProduct() {
        int copies = 0;
        System.out.print("Thank you for your purchase. You have purchased " + copies + " copy/copies of " + getName());
        MovieStock -= 1;
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
    @Override
    public void displayStorefront() {
        for (Consumables item : Menu) {
            item.displayProduct();
        }
    }

    public ArrayList<Consumables> getMenu() {
        return Menu;
    }

    public void addConsumable(Consumables consumable) {
        Menu.add(consumable);
    }
    @Override
    public void BuyProduct() {
        int copies = 0;
        System.out.print("Thank you for your purchase. You have purchased " + copies + " serving(s) of " + getName());
        MenuItems -= 1;
    }
}