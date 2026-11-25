package product;

public class Movie extends Media {

    private int runtime;


    public Movie(
        String name,
        double price,
        int stock,
        String genre,
        int release,
        int runtime,
        String creator
    ) {

        super(
            name,
            price,
            stock,
            genre,
            release,
            creator
        );

        this.runtime = runtime;
    }


    // GETTER

    public int getRuntime() {
        return runtime;
    }


    // SETTER

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }


    // OVERRIDING

    @Override
    public String displayProduct() {

        return "Title: " + getName()
            + " by " + getCreator()
            + "\nA " + getGenre()
            + " movie released in " + getRelease()
            + "\nRuntime: " + getRuntime() + " minutes"
            + "\nPrice: PHP " + String.format("%.2f", getPrice())
            + "\nStock: " + getStock();
    }
}