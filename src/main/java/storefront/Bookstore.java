package storefront;

import java.util.ArrayList;

import product.Book;
import product.Movie;
import product.Product;

public class Bookstore extends Storefront {

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


    public Bookstore(String name, double earnings, int customers,
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

        book.setName(name);
        book.setPrice(price);
        book.setStock(stock);
        book.setGenre(genre);
        book.setRelease(release);
        book.setCreator(creator);
        book.setPageCount(pageCount);
    }
    public void updateMovie(Movie movie, String name, double price, int stock,
                          String genre, int release, int runtime,
                          String creator) {

        movie.setName(name);
        movie.setPrice(price);
        movie.setStock(stock);
        movie.setGenre(genre);
        movie.setRelease(release);
        movie.setCreator(creator);
        movie.setRuntime(runtime);
    }

    // DELETE
        
    public void deleteBook(Book book) {
        if (Books.remove(book)) {
            BookStock--;
        }
    }

    public void deleteMovie(Movie movie) {
        if (Movies.remove(movie)) {
            MovieStock--;
        }
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
