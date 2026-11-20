abstract class Product {
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
        

        
        abstract void displayProduct();


        @Override
        public String toString() {
            return String.format("%-10s PHP %.2f  (%d pcs)", name, price, stock);
        }
}


class Media extends Product {
    private String genre;
    private int release;
    private String creator;


    public String getName()  { 
            return genre; 
    }
    public int getRelease()  { 
            return release; 
    }
    public String getCreator()    { 
            return creator;  // media: author if book, director if movie
    }


    Media (String name, double price, int stock) {
       super(name, price, stock);
    }

    void displayProduct() {
       System.out.println("Author:");
    }
}

class Consumables extends Product {
    // food and drink
    Consumables (String name, double price, int stock) {
       super(name, price, stock);
    }

    void displayProduct() {
       // creator = author if book, director if movie
       System.out.println("Author:");
    }
}
