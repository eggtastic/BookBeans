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

    public void addBook(Book book) {

        Books.add(book);

        Products.add(book);

        BookStock++;
    }


    public void addMovie(Movie movie) {

        Movies.add(movie);

        Products.add(movie);

        MovieStock++;
    }


    // =====================================================
    // UPDATE
    // =====================================================

    public void updateBook(
        Book book,
        String name,
        double price,
        int stock,
        String genre,
        int release,
        int pageCount,
        String creator
    ) {

        book.setName(name);
        book.setPrice(price);
        book.setStock(stock);
        book.setGenre(genre);
        book.setRelease(release);
        book.setPageCount(pageCount);
        book.setCreator(creator);
    }


    public void updateMovie(
        Movie movie,
        String name,
        double price,
        int stock,
        String genre,
        int release,
        int runtime,
        String creator
    ) {

        movie.setName(name);
        movie.setPrice(price);
        movie.setStock(stock);
        movie.setGenre(genre);
        movie.setRelease(release);
        movie.setRuntime(runtime);
        movie.setCreator(creator);
    }


    // =====================================================
    // DELETE
    // =====================================================

    public void deleteBook(Book book) {

        if (Books.remove(book)) {

            Products.remove(book);

            BookStock--;
        }
    }


    public void deleteMovie(Movie movie) {

        if (Movies.remove(movie)) {

            Products.remove(movie);

            MovieStock--;
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