package app;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import product.Book;
import product.Consumables;
import product.Media;
import product.Movie;
import product.Product;

import storefront.Bookstore;
import storefront.Cafe;


public class Controller {


    // =====================================================
    // FXML
    // =====================================================

    @FXML
    private TableView<Product> productTable;

    @FXML
    private TableColumn<Product, String> nameColumn;

    @FXML
    private TableColumn<Product, Double> priceColumn;

    @FXML
    private TableColumn<Product, Integer> stockColumn;

    @FXML
    private TableColumn<Product, String> genreColumn;

    @FXML
    private TableColumn<Product, Integer> releaseColumn;

    @FXML
    private TableColumn<Product, String> creatorColumn;

    @FXML
    private TableColumn<Product, String> detailsColumn;


    @FXML
    private TextField nameField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField stockField;

    @FXML
    private TextField genreField;

    @FXML
    private TextField releaseField;

    @FXML
    private TextField creatorField;

    @FXML
    private TextField pageCountField;

    @FXML
    private TextField runtimeField;


    @FXML
    private ComboBox<String> productTypeComboBox;


    @FXML
    private ImageView librarianImage;


    @FXML
    private Label earningsLabel;

    @FXML
    private Label dialogueLabel;


    @FXML
    private Button bookStockButton;

    @FXML
    private Button movieStockButton;

    @FXML
    private Button consumablesStockButton;



    // =====================================================
    // VARIABLES
    // =====================================================

    private Bookstore bookstore;
    private Cafe cafe;

    private Product selectedProduct;

    private String currentStore = "Bookstore";


    /*
     * Each Product object gets one librarian dialogue.
     *
     * The Product is the key.
     * The selected dialogue is the value.
     *
     * Once assigned, it does not change.
     */
    private Map<Product, String> productDialogue =
        new HashMap<>();


    private Random random =
        new Random();



    // =====================================================
    // INITIALIZE
    // =====================================================

    @FXML
    public void initialize() {

        bookstore = new Bookstore(
            "BookBeans Bookstore",
            0,
            0,
            0
        );


        cafe = new Cafe(
            "BookBeans Cafe",
            0,
            0
        );


        setupTable();

        setupLibrarianImage();

        addDefaultProducts();

        showBookstore();


        productTypeComboBox.setOnAction(
            event -> updateProductFields()
        );
    }



    // =====================================================
    // DEFAULT PRODUCTS
    // =====================================================

    private void addDefaultProducts() {


        // -------------------------------------------------
        // BOOKS
        // -------------------------------------------------

        Book percyJackson = new Book(
            "Percy Jackson and the Olympians: The Lightning Thief",
            599.00,
            5,
            "Fantasy",
            2005,
            377,
            "Rick Riordan"
        );


        Book metamorphosis = new Book(
            "Metamorphosis",
            450.00,
            3,
            "Fiction",
            1915,
            74,
            "Franz Kafka"
        );


        Book nineteenEightyFour = new Book(
            "1984",
            500.00,
            7,
            "Dystopian",
            1949,
            328,
            "George Orwell"
        );


        bookstore.addBook(percyJackson);
        bookstore.addBook(metamorphosis);
        bookstore.addBook(nineteenEightyFour);



        // -------------------------------------------------
        // MOVIES
        // -------------------------------------------------

        Movie projectHailMary = new Movie(
            "Project Hail Mary",
            650.00,
            2,
            "Science Fiction",
            2026,
            150,
            "Phil Lord & Christopher Miller"
        );


        Movie ironLung = new Movie(
            "Iron Lung",
            600.00,
            6,
            "Horror",
            2026,
            93,
            "Markiplier"
        );


        bookstore.addMovie(projectHailMary);
        bookstore.addMovie(ironLung);



        // -------------------------------------------------
        // FOOD
        // -------------------------------------------------

        Consumables strawberrySundae =
            new Consumables(
                "Strawberry Sundae",
                120.00,
                12
            );


        Consumables carbonara =
            new Consumables(
                "Carbonara",
                180.00,
                5
            );


        cafe.addConsumable(strawberrySundae);
        cafe.addConsumable(carbonara);



        // -------------------------------------------------
        // ASSIGN PERMANENT DIALOGUE
        // -------------------------------------------------

        assignDialogue(percyJackson);
        assignDialogue(metamorphosis);
        assignDialogue(nineteenEightyFour);

        assignDialogue(projectHailMary);
        assignDialogue(ironLung);

        assignDialogue(strawberrySundae);
        assignDialogue(carbonara);
    }



    // =====================================================
    // LIBRARIAN SPRITES
    // =====================================================

    private void setupLibrarianImage() {

        /*
         * Images are located in:
         *
         * src/main/resources/app/
         */

        setLibrarianExpression("happy");
    }


    private void setLibrarianExpression(
        String expression
    ) {

        String imagePath =
            "/app/azira_" + expression + ".png";


        Image image =
            new Image(
                getClass()
                    .getResourceAsStream(imagePath)
            );


        librarianImage.setImage(image);
    }



    // =====================================================
    // DIALOGUE SYSTEM
    // =====================================================

    private void assignDialogue(Product product) {

        String[] lines;


        // BOOK

        if (product instanceof Book) {

            lines = new String[] {

                "Ah, this one. Brings good memories.",

                "You have refined taste. Would you like that one?",

                "Oh, no. I wouldn't..."
            };
        }


        // MOVIE

        else if (product instanceof Movie) {

            lines = new String[] {

                "You're interested in this?",

                "I suppose we all have our tastes.",

                "This one! It's quite nice, yes."
            };
        }


        // FOOD

        else {

            lines = new String[] {

                "This one is delightful!",

                "I do hope you enjoy this one.",

                "Ah, a lovely choice."
            };
        }


        int chosenLine =
            random.nextInt(lines.length);


        productDialogue.put(
            product,
            lines[chosenLine]
        );
    }



    private String getProductDialogue(
        Product product
    ) {

        if (!productDialogue.containsKey(product)) {

            assignDialogue(product);
        }


        return productDialogue.get(product);
    }



    private void showProductDialogue(
        Product product
    ) {

        String librarianLine =
            getProductDialogue(product);


        String productInformation =
            product.displayProduct();


        /*
         * Choose an expression based on
         * the randomly assigned dialogue.
         */

        if (
            librarianLine.equals(
                "Oh, no. I wouldn't..."
            )
        ) {

            setLibrarianExpression("displeased");

        }

        else if (
            librarianLine.equals(
                "You have refined taste. Would you like that one?"
            )
        ) {

            setLibrarianExpression("happier");

        }

        else if (
            librarianLine.equals(
                "I suppose we all have our tastes."
            )
        ) {

            setLibrarianExpression("displeased");

        }

        else if (
            librarianLine.equals(
                "This one! It's quite nice, yes."
            )
        ) {

            setLibrarianExpression("happier");

        }

        else if (
            librarianLine.equals(
                "This one is delightful!"
            )
        ) {

            setLibrarianExpression("happier");

        }

        else if (
            librarianLine.equals(
                "I do hope you enjoy this one."
            )
        ) {

            setLibrarianExpression("happier");

        }

        else if (
            librarianLine.equals(
                "Ah, a lovely choice."
            )
        ) {

            setLibrarianExpression("happier");

        }

        else {

            setLibrarianExpression("happy");
        }


        dialogueLabel.setText(
            librarianLine
            + "\n\n"
            + productInformation
        );
    }



    // =====================================================
    // ACTION DIALOGUE
    // =====================================================

    private String getBuyDialogue(
        Product product
    ) {

        if (product instanceof Book) {

            return "Do enjoy that one.";

        }

        else if (product instanceof Movie) {

            return "Thank you for taking that off my hands.";

        }

        else {

            return "Thank you for ordering.";
        }
    }



    private String getDeleteDialogue(
        Product product
    ) {

        if (product instanceof Book) {

            return "Aahh! My book!";

        }

        else if (product instanceof Movie) {

            return "Oh, did it fall off, where'd it go?";

        }

        else {

            return "Did you just... consume that without paying?";
        }
    }



    private String getUpdateDialogue(
        Product product
    ) {

        if (product instanceof Book) {

            return "Always good to keep these things in tip top shape.";

        }

        else if (product instanceof Movie) {

            return "Does that have a new title? I forget.";

        }

        else {

            return "Oh, is that right? I swear I got the ingredients right...!";
        }
    }



    // =====================================================
    // ACTION EXPRESSIONS
    // =====================================================

    private void showDeleteExpression() {

        setLibrarianExpression("shock");
    }



    private void showUpdateExpression(
        Product product
    ) {

        if (product instanceof Book) {

            setLibrarianExpression("happier");

        }

        else {

            setLibrarianExpression("displeased");
        }
    }



    // =====================================================
    // TABLE
    // =====================================================

    private void setupTable() {


        // NAME

        nameColumn.setCellValueFactory(
            cell ->
                new javafx.beans.property.SimpleStringProperty(
                    cell.getValue().getName()
                )
        );


        // PRICE

        priceColumn.setCellValueFactory(
            cell ->
                new javafx.beans.property.SimpleObjectProperty<>(
                    cell.getValue().getPrice()
                )
        );


        // STOCK

        stockColumn.setCellValueFactory(
            cell ->
                new javafx.beans.property.SimpleObjectProperty<>(
                    cell.getValue().getStock()
                )
        );


        // GENRE

        genreColumn.setCellValueFactory(
            cell -> {

                Product product =
                    cell.getValue();


                if (product instanceof Media) {

                    Media media =
                        (Media) product;


                    return new javafx.beans.property.SimpleStringProperty(
                        media.getGenre()
                    );
                }


                return new javafx.beans.property.SimpleStringProperty(
                    ""
                );
            }
        );



        // RELEASE

        releaseColumn.setCellValueFactory(
            cell -> {

                Product product =
                    cell.getValue();


                if (product instanceof Media) {

                    Media media =
                        (Media) product;


                    return new javafx.beans.property.SimpleObjectProperty<>(
                        media.getRelease()
                    );
                }


                return new javafx.beans.property.SimpleObjectProperty<>(
                    null
                );
            }
        );



        // CREATOR

        creatorColumn.setCellValueFactory(
            cell -> {

                Product product =
                    cell.getValue();


                if (product instanceof Media) {

                    Media media =
                        (Media) product;


                    return new javafx.beans.property.SimpleStringProperty(
                        media.getCreator()
                    );
                }


                return new javafx.beans.property.SimpleStringProperty(
                    ""
                );
            }
        );



        // DETAILS

        detailsColumn.setCellValueFactory(
            cell -> {

                Product product =
                    cell.getValue();


                if (product instanceof Book) {

                    Book book =
                        (Book) product;


                    return new javafx.beans.property.SimpleStringProperty(
                        book.getPageCount()
                        + " pages"
                    );
                }


                else if (product instanceof Movie) {

                    Movie movie =
                        (Movie) product;


                    return new javafx.beans.property.SimpleStringProperty(
                        movie.getRuntime()
                        + " min"
                    );
                }


                return new javafx.beans.property.SimpleStringProperty(
                    ""
                );
            }
        );



        // SELECTED PRODUCT

        productTable
            .getSelectionModel()
            .selectedItemProperty()
            .addListener(
                (observable, oldValue, newValue) -> {

                    selectedProduct =
                        newValue;


                    if (selectedProduct != null) {

                        fillFields(
                            selectedProduct
                        );

                        showProductDialogue(
                            selectedProduct
                        );
                    }
                }
            );
    }



    // =====================================================
    // STORE BUTTONS
    // =====================================================

    @FXML
    private void handleBookstore() {

        showBookstore();
    }


    @FXML
    private void handleCafe() {

        showCafe();
    }



    // =====================================================
    // SHOW BOOKSTORE
    // =====================================================

    private void showBookstore() {

        currentStore =
            "Bookstore";


        bookStockButton.setVisible(true);
        bookStockButton.setManaged(true);

        movieStockButton.setVisible(true);
        movieStockButton.setManaged(true);

        consumablesStockButton.setVisible(false);
        consumablesStockButton.setManaged(false);


        productTable
            .getItems()
            .clear();


        /*
         * This is the mixed ArrayList<Product>.
         *
         * It contains both Book and Movie objects.
         */

        productTable
            .getItems()
            .addAll(
                bookstore.getProducts()
            );


        productTypeComboBox
            .getItems()
            .clear();


        productTypeComboBox
            .getItems()
            .addAll(
                "Book",
                "Movie"
            );


        productTypeComboBox.setValue(
            "Book"
        );


        updateProductFields();
        updateEarnings();


        setLibrarianExpression("happy");


        dialogueLabel.setText(
            "Welcome to the Bookstore!"
        );
    }



    // =====================================================
    // SHOW CAFE
    // =====================================================

    private void showCafe() {

        currentStore =
            "Cafe";


        bookStockButton.setVisible(false);
        bookStockButton.setManaged(false);

        movieStockButton.setVisible(false);
        movieStockButton.setManaged(false);

        consumablesStockButton.setVisible(true);
        consumablesStockButton.setManaged(true);


        productTable
            .getItems()
            .clear();


        productTable
            .getItems()
            .addAll(
                cafe.getMenu()
            );


        productTypeComboBox
            .getItems()
            .clear();


        productTypeComboBox
            .getItems()
            .add(
                "Consumable"
            );


        productTypeComboBox.setValue(
            "Consumable"
        );


        updateProductFields();
        updateEarnings();


        setLibrarianExpression("happy");


        dialogueLabel.setText(
            "Welcome to the Cafe!"
        );
    }



    // =====================================================
    // ADD
    // =====================================================

    @FXML
    private void handleAdd() {

        String name =
            nameField.getText();

        String priceText =
            priceField.getText();

        String stockText =
            stockField.getText();


        if (
            name.isEmpty()
            || priceText.isEmpty()
            || stockText.isEmpty()
        ) {

            setLibrarianExpression("displeased");


            dialogueLabel.setText(
                "Please fill in the basic product information."
            );

            return;
        }


        double price =
            Double.parseDouble(
                priceText
            );

        int stock =
            Integer.parseInt(
                stockText
            );



        // BOOKSTORE

        if (
            currentStore.equals(
                "Bookstore"
            )
        ) {

            String genre =
                genreField.getText();


            int release =
                Integer.parseInt(
                    releaseField.getText()
                );


            String creator =
                creatorField.getText();



            // BOOK

            if (
                productTypeComboBox
                    .getValue()
                    .equals("Book")
            ) {

                int pageCount =
                    Integer.parseInt(
                        pageCountField.getText()
                    );


                Book book =
                    new Book(
                        name,
                        price,
                        stock,
                        genre,
                        release,
                        pageCount,
                        creator
                    );


                bookstore.addBook(book);

                assignDialogue(book);

            }



            // MOVIE

            else {

                int runtime =
                    Integer.parseInt(
                        runtimeField.getText()
                    );


                Movie movie =
                    new Movie(
                        name,
                        price,
                        stock,
                        genre,
                        release,
                        runtime,
                        creator
                    );


                bookstore.addMovie(movie);

                assignDialogue(movie);
            }


            showBookstore();
        }



        // CAFE

        else {

            Consumables consumable =
                new Consumables(
                    name,
                    price,
                    stock
                );


            cafe.addConsumable(
                consumable
            );


            assignDialogue(
                consumable
            );


            showCafe();
        }


        clearFields();
    }



    // =====================================================
    // UPDATE
    // =====================================================

    @FXML
    private void handleUpdate() {

        if (selectedProduct == null) {

            setLibrarianExpression("displeased");

            dialogueLabel.setText(
                "Please select a product first."
            );

            return;
        }


        String name =
            nameField.getText();


        double price =
            Double.parseDouble(
                priceField.getText()
            );


        int stock =
            Integer.parseInt(
                stockField.getText()
            );


        String updateDialogue =
            getUpdateDialogue(
                selectedProduct
            );


        // BOOK

        if (selectedProduct instanceof Book) {

            Book book =
                (Book) selectedProduct;


            bookstore.updateBook(
                book,
                name,
                price,
                stock,
                genreField.getText(),
                Integer.parseInt(
                    releaseField.getText()
                ),
                Integer.parseInt(
                    pageCountField.getText()
                ),
                creatorField.getText()
            );
        }



        // MOVIE

        else if (selectedProduct instanceof Movie) {

            Movie movie =
                (Movie) selectedProduct;


            bookstore.updateMovie(
                movie,
                name,
                price,
                stock,
                genreField.getText(),
                Integer.parseInt(
                    releaseField.getText()
                ),
                Integer.parseInt(
                    runtimeField.getText()
                ),
                creatorField.getText()
            );
        }



        // FOOD

        else if (selectedProduct instanceof Consumables) {

            cafe.updateConsumable(
                (Consumables) selectedProduct,
                name,
                price,
                stock
            );
        }



        Product updatedProduct =
            selectedProduct;


        if (
            currentStore.equals(
                "Bookstore"
            )
        ) {

            showBookstore();

        }

        else {

            showCafe();
        }


        selectedProduct = null;

        clearFields();


        showUpdateExpression(
            updatedProduct
        );


        dialogueLabel.setText(
            updateDialogue
        );
    }



    // =====================================================
    // DELETE
    // =====================================================

    @FXML
    private void handleDelete() {

        if (selectedProduct == null) {

            setLibrarianExpression("displeased");


            dialogueLabel.setText(
                "Please select a product first."
            );

            return;
        }


        String deleteDialogue =
            getDeleteDialogue(
                selectedProduct
            );



        if (
            selectedProduct instanceof Book
        ) {

            bookstore.deleteBook(
                (Book) selectedProduct
            );
        }


        else if (
            selectedProduct instanceof Movie
        ) {

            bookstore.deleteMovie(
                (Movie) selectedProduct
            );
        }


        else if (
            selectedProduct instanceof Consumables
        ) {

            cafe.deleteConsumable(
                (Consumables) selectedProduct
            );
        }


        productDialogue.remove(
            selectedProduct
        );


        showDeleteExpression();


        if (
            currentStore.equals(
                "Bookstore"
            )
        ) {

            showBookstore();

        }

        else {

            showCafe();
        }


        selectedProduct = null;

        clearFields();


        dialogueLabel.setText(
            deleteDialogue
        );
    }



    // =====================================================
    // BUY
    // =====================================================

    @FXML
    private void handleBuy() {

        if (selectedProduct == null) {

            setLibrarianExpression("displeased");


            dialogueLabel.setText(
                "Please select a product first."
            );

            return;
        }


        if (
            selectedProduct.getStock()
            <= 0
        ) {

            setLibrarianExpression("displeased");


            dialogueLabel.setText(
                "Sorry, this product is out of stock."
            );

            return;
        }


        String buyDialogue =
            getBuyDialogue(
                selectedProduct
            );


        Product boughtProduct =
            selectedProduct;



        if (
            selectedProduct instanceof Book
            || selectedProduct instanceof Movie
        ) {

            bookstore.BuyProduct(
                selectedProduct
            );


            showBookstore();
        }



        else if (
            selectedProduct instanceof Consumables
        ) {

            cafe.BuyProduct(
                selectedProduct
            );


            showCafe();
        }


        updateEarnings();


        setLibrarianExpression(
            "happier"
        );


        dialogueLabel.setText(
            buyDialogue
        );


        selectedProduct =
            boughtProduct;
    }



    // =====================================================
    // STOCK BUTTONS
    // =====================================================

    @FXML
    private void handleBookStock() {

        setLibrarianExpression(
            "happier"
        );


        dialogueLabel.setText(
            "Oh, I'm so glad you asked! There are "
            + bookstore.getBookStock()
            + " books in stock!"
        );
    }



    @FXML
    private void handleMovieStock() {

        setLibrarianExpression(
            "happy"
        );


        dialogueLabel.setText(
            "Oh, you want to know? There are "
            + bookstore.getMovieStock()
            + " movies in stock."
        );
    }



    @FXML
    private void handleConsumablesStock() {

        setLibrarianExpression(
            "happier"
        );


        dialogueLabel.setText(
            "How much food, you say? Well, currently there are "
            + cafe.getMenuItems()
            + " items in the cafe."
        );
    }



    // =====================================================
    // FILL FIELDS
    // =====================================================

    private void fillFields(
        Product product
    ) {

        nameField.setText(
            product.getName()
        );


        priceField.setText(
            String.valueOf(
                product.getPrice()
            )
        );


        stockField.setText(
            String.valueOf(
                product.getStock()
            )
        );



        if (
            product instanceof Media
        ) {

            Media media =
                (Media) product;


            genreField.setText(
                media.getGenre()
            );


            releaseField.setText(
                String.valueOf(
                    media.getRelease()
                )
            );


            creatorField.setText(
                media.getCreator()
            );
        }

        else {

            genreField.clear();
            releaseField.clear();
            creatorField.clear();
        }



        if (
            product instanceof Book
        ) {

            Book book =
                (Book) product;


            pageCountField.setText(
                String.valueOf(
                    book.getPageCount()
                )
            );

        }

        else {

            pageCountField.clear();
        }



        if (
            product instanceof Movie
        ) {

            Movie movie =
                (Movie) product;


            runtimeField.setText(
                String.valueOf(
                    movie.getRuntime()
                )
            );

        }

        else {

            runtimeField.clear();
        }
    }



    // =====================================================
    // PRODUCT TYPE FIELDS
    // =====================================================

    private void updateProductFields() {

        String type =
            productTypeComboBox.getValue();


        boolean isBook =
            "Book".equals(type);

        boolean isMovie =
            "Movie".equals(type);

        boolean isConsumable =
            "Consumable".equals(type);



        genreField.setDisable(
            isConsumable
        );

        releaseField.setDisable(
            isConsumable
        );

        creatorField.setDisable(
            isConsumable
        );

        pageCountField.setDisable(
            !isBook
        );

        runtimeField.setDisable(
            !isMovie
        );



        if (isConsumable) {

            genreField.clear();
            releaseField.clear();
            creatorField.clear();
            pageCountField.clear();
            runtimeField.clear();
        }
    }



    // =====================================================
    // EARNINGS
    // =====================================================

    private void updateEarnings() {

        double totalEarnings =
            bookstore.getEarnings()
            + cafe.getEarnings();


        earningsLabel.setText(
            String.format(
                "Earnings: PHP %.2f",
                totalEarnings
            )
        );
    }



    // =====================================================
    // CLEAR FIELDS
    // =====================================================

    private void clearFields() {

        nameField.clear();
        priceField.clear();
        stockField.clear();
        genreField.clear();
        releaseField.clear();
        creatorField.clear();
        pageCountField.clear();
        runtimeField.clear();
    }
}