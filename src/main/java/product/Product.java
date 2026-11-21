package product;

public abstract class Product {
        private String name;
        private double price;
        private int stock;


        public Product(String name, double price, int stock) {
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
class Movie extends Media {
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
class Consumables extends Product {
    // food and drink
    
    Consumables(String name, double price, int stock) {
        super(name, price, stock);
    }

    void displayProduct() {
        System.out.println("Name: " + getName()+ ". Price: " + getPrice()+ ", Stock: " + getStock());
    }
}
