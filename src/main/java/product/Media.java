package product;

public class Media extends Product {

    private String genre;
    private int release;
    private String creator;


    public Media(
        String name,
        double price,
        int stock,
        String genre,
        int release,
        String creator
    ) {

        super(name, price, stock);

        this.genre = genre;
        this.release = release;
        this.creator = creator;
    }


    // GETTERS

    public String getGenre() {
        return genre;
    }

    public int getRelease() {
        return release;
    }

    public String getCreator() {
        return creator;
    }


    // SETTERS

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setRelease(int release) {
        this.release = release;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }


    @Override
    public String displayProduct() {

        return getName()
            + ", a " + genre
            + " released in " + release
            + ". Price: PHP " + String.format("%.2f", getPrice())
            + ", Stock: " + getStock();
    }
}