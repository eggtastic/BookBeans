package product;

public abstract class Media extends Product {

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
}