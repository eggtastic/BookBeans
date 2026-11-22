package product;

public class Book extends Media {
    private int pageCount;

    public Book(String name, double price, int stock,
         String genre, int release, int pageCount, String creator) {

        super(name, price, stock, genre, release, creator); // creator here means author
        this.pageCount = pageCount;
    }

    public int getPageCount() {
        return pageCount;
    }
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public void displayProduct() {
        System.out.println("Title: " + getName()+ " by " + getCreator()+ ", a " + getGenre()+ " released in " + getRelease()+ ". Price: " + getPrice()+ ", Stock: " + getStock());
    }
}
