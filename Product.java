abstract class Product {
        private String name;
        private double price;
        private int stock;


        Product(String name, double price, int stock) {
            this.name = name;
            this.price = price;
            this.stock = stock;
        }


        public String getName()  { 
            return name; 
        }
        public double getPrice() { 
            return price; 
        }
        public int getStock()    { 
            return stock; 
        }

        public void setPrice(double price) { this.price = price; }
        
        abstract void displayProduct();

        @Override
        public String toString() {
            return String.format("%-10s PHP %.2f  (%d pcs)", name, price, stock);
        }
}
class Media extends Product {
    private String genre;
    private int release;

    public String getGenre()  { 
            return genre; 
    }
    public int getRelease()  { 
            return release; 
    }

    Media (String name, double price, int stock, String genre, int release) {
       super(name, price, stock);
       this.genre = genre;
       this.release = release;
    }

    void displayProduct() {
        System.out.println(getName() + ", a " + genre + " released in " + release + ". Price: " + getPrice() + ", Stock: " + getStock());
    }
}
class Book extends Media {
    private String author;
    private int pageCount;

    Book(String name, double price, int stock,
         String genre, int release, String author, int pageCount) {

        super(name, price, stock, genre, release);
        this.author = author;
        this.pageCount = pageCount;
    }

    public String getAuthor() {
        return author;
    }

    public int getPageCount() {
        return pageCount;
    }

    void displayProduct() {
        System.out.println("Title: " + getName()+ " by " + author+ ", a " + getGenre()+ " released in " + getRelease()+ ". Price: " + getPrice()+ ", Stock: " + getStock());
    }
}
class Movie extends Media {
    private String director;
    private int runtime;

    Movie(String name, double price, int stock,
          String genre, int release, String director, int runtime) {
        super(name, price, stock, genre, release);
        this.director = director;
        this.runtime = runtime;
    }

    public String getDirector() {
        return director;
    }

    public int getRuntime() {
        return runtime;
    }

    void displayProduct() {
        System.out.println("Title: " + getName()
                + " by " + director
                + ", a " + getGenre()
                + " released in " + getRelease()
                + ". Price: " + getPrice()
                + ", Stock: " + getStock());
    }
}
class Consumables extends Product {
    // food and drink
    
    Consumables(String name, double price, int stock) {
        super(name, price, stock);
    }

    void displayProduct() {
        System.out.println("Name: " + getName()
                + ". Price: " + getPrice()
                + ", Stock: " + getStock());
    }
}
