package src.main.java.product;

public class Movie extends Media {
    private int runtime;

    Movie(String name, double price, int stock,
          String genre, int release, int runtime, String creator) {
        super(name, price, stock, genre, release, creator); // creator here means director
        this.runtime = runtime;
    }

    public int getRuntime() {
        return runtime;
    }

    void displayProduct() {
        System.out.println("Title: " + getName()+ " by " + getCreator()+ ", a " + getGenre()+ " released in " + getRelease()+ ". Price: " + getPrice()+ ", Stock: " + getStock());
    }
}
