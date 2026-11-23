package product;

public class Book extends Media {

    private int pageCount;


    public Book(
        String name,
        double price,
        int stock,
        String genre,
        int release,
        int pageCount,
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

        this.pageCount = pageCount;
    }


    // GETTER

    public int getPageCount() {
        return pageCount;
    }


    // SETTER

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }


    // OVERRIDING

    @Override
    public String displayProduct() {

        return "Title: " + getName()
            + " by " + getCreator()
            + "\nA " + getGenre()
            + " book released in " + getRelease()
            + "\nPages: " + getPageCount()
            + "\nPrice: PHP " + String.format("%.2f", getPrice())
            + "\nStock: " + getStock();
    }
}