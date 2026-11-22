package storefront;

import java.util.ArrayList;

import product.Book;
import product.Movie;
import product.Consumables;
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


class Bookstore extends Storefront {

    // Number of different book and movie products
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


    Bookstore(String name, double earnings, int customers,
              int BookStock, int MovieStock) {

        super(name, earnings, customers);

        this.BookStock = BookStock;
        this.MovieStock = MovieStock;
    }


    // CREATE

    public void addBook(Book book) {
        Books.add(book);
        BookStock++;
    }

    public void addMovie(Movie movie) {
        Movies.add(movie);
        MovieStock++;
    }
    // UPDATE

    public void updateBook(Book book, String name, double price, int stock,
                        String genre, int release, int pageCount,
                        String creator) {

        book.setStock(stock);
        book.setPrice(price);
    }


    // READ

    public ArrayList<Book> getBooks() {
        return Books;
    }

    public ArrayList<Movie> getMovies() {
        return Movies;
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


    // BUY

    @Override
    public void BuyProduct(Product product) {

        if (product instanceof Book || product instanceof Movie) {

            if (product.getStock() > 0) {
                product.reduceStock();
                setEarnings(getEarnings() + product.getPrice());
                System.out.println(
                    "Thank you for your purchase of " +
                    product.getName()
                );
            } else {
                System.out.println(
                    "Sorry, " + product.getName() +
                    " is out of stock."
                );
            }

        } else {

            System.out.println(
                "This product is not sold in the bookstore."
            );
        }
    }
}


class Cafe extends Storefront {

    // Number of different menu products
    private int MenuItems;

    private ArrayList<Consumables> Menu = new ArrayList<>();


    public int getMenuItems() {
        return MenuItems;
    }


    Cafe(String name, double earnings, int customers,
         int MenuItems) {

        super(name, earnings, customers);

        this.MenuItems = MenuItems;
    }


    // CREATE

    public void addConsumable(Consumables consumable) {
        Menu.add(consumable);
        MenuItems++;
    }


    // READ

    public ArrayList<Consumables> getMenu() {
        return Menu;
    }


    @Override
    public void displayStorefront() {

        for (Consumables item : Menu) {
            item.displayProduct();
        }
    }


    // BUY

    @Override
    public void BuyProduct(Product product) {

        if (product instanceof Consumables) {

            if (product.getStock() > 0) {

                product.reduceStock();

                setEarnings(getEarnings() + product.getPrice());

                System.out.println(
                    "Thank you for your purchase of " +
                    product.getName()
                );

            } else {

                System.out.println(
                    "Sorry, " + product.getName() +
                    " is out of stock."
                );
            }

        } else {

            System.out.println(
                "This product is not sold in the cafe."
            );
        }
    }
}