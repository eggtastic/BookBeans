# BookBeans

A JavaFX bookstore and cafe management system demonstrating core OOP concepts.

## UML Diagram

```text
                         <<abstract>>
                         Storefront
                    ----------------------
                    - name: String
                    - earnings: double
                    ----------------------
                    + addProduct(Product): void
                    + updateProduct(Product): void
                    + deleteProduct(Product): void
                    + displayStorefront(): void
                    + BuyProduct(Product): void
                              △
                              |
                +-------------+-------------+
                |                           |
          Bookstore                       Cafe
     ------------------             ------------------
     - BookStock: int               - MenuItems: int
     - MovieStock: int              - Menu: ArrayList<Consumables>
     - Books: ArrayList<Book>
     - Movies: ArrayList<Movie>
     - Products: ArrayList<Product>
     ------------------             ------------------
     + Bookstore(...)               + Cafe(...)
     + getBookStock(): int          + getMenuItems(): int
     + getMovieStock(): int         + addProduct(Product): void
     + addProduct(Product): void    + updateProduct(Product): void
     + updateProduct(Product): void + deleteProduct(Product): void
     + deleteProduct(Product): void + getMenu(): ArrayList<Consumables>
     + getBooks(): ArrayList<Book>  + displayStorefront(): void
     + getMovies(): ArrayList<Movie>+ BuyProduct(Product): void
     + getProducts(): ArrayList<Product>
     + displayStorefront(): void
     + BuyProduct(Product): void


                              <<abstract>>
                                Product
                         ----------------------
                         - name: String
                         - price: double
                         - stock: int
                         ----------------------
                         + Product(String, double, int)
                         + getName(): String
                         + getPrice(): double
                         + getStock(): int
                         + setName(String): void
                         + setPrice(double): void
                         + setStock(int): void
                         + reduceStock(): void
                         + displayProduct(): String
                         + toString(): String
                                  △
                    +-------------+-------------+
                    |                           |
             <<abstract>>                  Consumables
                 Media                  ------------------
          ----------------------         + Consumables(...)
          - genre: String                + displayProduct(): String
          - release: int
          - creator: String
          ----------------------
          + Media(...)
          + getGenre(): String
          + getRelease(): int
          + getCreator(): String
          + setGenre(String): void
          + setRelease(int): void
          + setCreator(String): void
          + displayProduct(): String
                    △
                    |
             +------+------+
             |             |
           Book           Movie
      ------------     ------------
      - pageCount: int - runtime: int
      ------------     ------------
      + Book(...)       + Movie(...)
      + getPageCount(): int
      + setPageCount(int): void
      + displayProduct(): String
                       + getRuntime(): int
                       + setRuntime(int): void
                       + displayProduct(): String


                           Controller
                    --------------------------
                    - bookstore: Bookstore
                    - cafe: Cafe
                    - currentStore: Storefront
                    - selectedProduct: Product
                    - productDialogue: HashMap<Product, String>
                    - productTable: TableView<Product>
                    - productType: ComboBox
                    - nameField: TextField
                    - priceField: TextField
                    - stockField: TextField
                    - genreField: TextField
                    - releaseField: TextField
                    - creatorField: TextField
                    - detailField: TextField
                    - dialogueLabel: Label
                    - earningsLabel: Label
                    - librarianImage: ImageView
                    --------------------------
                    + initialize(): void
                    + handleAdd(): void
                    + handleUpdate(): void
                    + handleDelete(): void
                    + handleBuy(): void
                    + handleBookstore(): void
                    + handleCafe(): void
                    + isNumber(String): boolean
                    + isWholeNumber(String): boolean
                    + validateBasicFields(): boolean
                    + validateMediaFields(): boolean
                    + validateDetailField(String): boolean


                            Main
                    -------------------
                    + start(Stage): void
                    + main(String[]): void

Project Overview
- Abstraction — Product, Media, and Storefront are abstract classes that define common behavior without being directly instantiated.
- Inheritance — Book and Movie inherit from Media; Media and Consumables inherit from Product; Bookstore and Cafe inherit from Storefront.
- Encapsulation — Data fields such as name, price, stock, genre, release, creator, and earnings are declared private. They are accessed and modified using get and set methods, such as getName() and setName().
- Polymorphism — ArrayList<Product> stores different product types, such as Book and Movie, allowing overridden displayProduct() methods to behave differently.
- CRUD — The Controller provides Add, Update, Delete, and Buy operations through the JavaFX interface.
- MVC-style separation — Model classes handle data and business logic, while Controller handles JavaFX UI actions.
- Collections — ArrayList stores products, while HashMap<Product, String> stores product dialogue.

Repository Structure
BookBeans/
├── pom.xml
└── src/
    └── main/
        ├── java/
        │   ├── app/
        │   │   ├── Main.java
        │   │   └── Controller.java
        │   │
        │   ├── product/
        │   │   ├── Product.java
        │   │   ├── Media.java
        │   │   ├── Book.java
        │   │   ├── Movie.java
        │   │   └── Consumables.java
        │   │
        │   └── storefront/
        │       ├── Storefront.java
        │       ├── Bookstore.java
        │       └── Cafe.java
        │
        └── resources/
            └── app/
                ├── BookBeansFX.fxml
                └── images/
                    ├── azira_displeased.png
                    ├── azira_happier.png
                    ├── azira_happy.png
                    └── azira_shock.png