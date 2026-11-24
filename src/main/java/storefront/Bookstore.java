package storefront;

import java.util.ArrayList;

import product.Book;
import product.Movie;
import product.Product;


public class Bookstore extends Storefront {

    private int BookStock;
    private int MovieStock;


    private ArrayList<Book> Books =
        new ArrayList<>();

    private ArrayList<Movie> Movies =
        new ArrayList<>();


    // MIXED PARENT-TYPE LIST
    // Contains both Book and Movie objects.

    private ArrayList<Product> Products =
        new ArrayList<>();


    public Bookstore(
        String name,
        double earnings,
        int BookStock,
        int MovieStock
    ) {

        super(name, earnings);

        this.BookStock = BookStock;
        this.MovieStock = MovieStock;
    }


    // =====================================================
    // STOCK
    // =====================================================

    public int getBookStock() {
        return BookStock;
    }

    public int getMovieStock() {
        return MovieStock;
    }


    // =====================================================
    // CREATE
    // =====================================================

    @Override
    public void addProduct(Product product) {

        if (product instanceof Book) {

            Books.add((Book) product);
            BookStock++;

        } else if (product instanceof Movie) {

            Movies.add((Movie) product);
            MovieStock++;
        }

        Products.add(product);
    }


    // =====================================================
    // UPDATE
    // =====================================================

    @Override
    public void updateProduct(Product product) {

        /*
         * The Product object is already inside the lists.
         *
         * Since Product, Media, Book, and Movie use setters,
         * the Controller can update the object's information
         * and then send the Product here.
         *
         * This method makes sure the product is still part
         * of the correct bookstore collection.
         */

        if (product instanceof Book) {

            Book book = (Book) product;

            if (!Books.contains(book)) {
                Books.add(book);
            }

            if (!Products.contains(book)) {
                Products.add(book);
            }

        } else if (product instanceof Movie) {

            Movie movie = (Movie) product;

            if (!Movies.contains(movie)) {
                Movies.add(movie);
            }

            if (!Products.contains(movie)) {
                Products.add(movie);
            }
        }
    }


    // =====================================================
    // DELETE
    // =====================================================

    @Override
    public void deleteProduct(Product product) {

        if (Products.remove(product)) {

            if (product instanceof Book) {

                Books.remove((Book) product);
                BookStock--;

            } else if (product instanceof Movie) {

                Movies.remove((Movie) product);
                MovieStock--;
            }
        }
    }


    // =====================================================
    // READ
    // =====================================================

    public ArrayList<Book> getBooks() {
        return Books;
    }


    public ArrayList<Movie> getMovies() {
        return Movies;
    }


    // THIS IS THE MIXED PARENT-TYPE LIST

    public ArrayList<Product> getProducts() {
        return Products;
    }


    // =====================================================
    // DISPLAY STOREFRONT
    // =====================================================

    @Override
    public void displayStorefront() {

        /*
         * Products contains Books and Movies.
         *
         * Product is the parent type.
         * The actual displayProduct() method that runs
         * depends on the object's subclass.
         *
         * This demonstrates polymorphism.
         */

        for (Product product : Products) {

            product.displayProduct();
        }
    }


    // =====================================================
    // BUY
    // =====================================================

    @Override
    public void BuyProduct(Product product) {

        if (
            product instanceof Book
            || product instanceof Movie
        ) {

            if (product.getStock() > 0) {

                product.reduceStock();

                setEarnings(
                    getEarnings()
                    + product.getPrice()
                );
            }
        }
    }
}