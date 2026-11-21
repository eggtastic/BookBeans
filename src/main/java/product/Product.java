package src.main.java.product;

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
