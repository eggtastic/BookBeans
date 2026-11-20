package src.main.java.product;

public class Media extends Product {
    private String genre;
    private int release;
    private String creator;

    public String getGenre()  { 
            return genre; 
    }
    public int getRelease()  { 
            return release; 
    }
    public String getCreator()  { 
            return creator; 
    }

    public Media (String name, double price, int stock, String genre, int release, String creator) {
       super(name, price, stock);
       this.genre = genre;
       this.release = release;
    }

    void displayProduct() {
        System.out.println(getName() + ", a " + genre + " released in " + release + ". Price: " + getPrice() + ", Stock: " + getStock());
    }
}
