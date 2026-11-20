public class Product {
    abstract class Product {
        private String name;
        private double price;
        private int stock;
        private String creator;


        public Product(String name, double price, int stock, String creator) {
            this.name = name;
            this.price = price;
            this.stock = stock;
            this.creator = creator;
        }


        public String getName()  { return name; }
        public double getPrice() { return price; }
        public int getStock()    { return stock; }
        public String getCreator()    { return creator; }


        abstract void whoCreator();


        @Override
        public String toString() {
            return String.format("%-10s PHP %.2f  (%d pcs)", name, price, stock);
        }
}


class Media extends Product {
   Media (String name, double price, int stock, String creator) {
       super(name, price, stock, creator);
   }
   void whoCreator() {
       // creator = author if book, director if movie
       System.out.println("Name's Maine.");
   }
}

}
