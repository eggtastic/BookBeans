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

    @FXML private TableView<Product> productTable;
    @FXML private TableColumn<Product, String> nameColumn;
    @FXML private TableColumn<Product, Double> priceColumn;
    @FXML private TableColumn<Product, Integer> stockColumn;
    @FXML private TableColumn<Product, String> genreColumn;
    @FXML private TableColumn<Product, Integer> releaseColumn;
    @FXML private TableColumn<Product, String> creatorColumn;
    @FXML private TableColumn<Product, String> detailsColumn;

    @FXML private TextField nameField;
    @FXML private TextField priceField;
    @FXML private TextField stockField;
    @FXML private TextField genreField;
    @FXML private TextField releaseField;
    @FXML private TextField creatorField;
    @FXML private TextField pageCountField;
    @FXML private TextField runtimeField;

    @FXML private ComboBox<String> productTypeComboBox;

    @FXML private ImageView librarianImage;
    @FXML private Label earningsLabel;
    @FXML private Label dialogueLabel;

    @FXML private Button bookStockButton;
    @FXML private Button movieStockButton;
    @FXML private Button consumablesStockButton;

    private Bookstore bookstore;
    private Cafe cafe;

    private Product selectedProduct;

    private String currentStore = "Bookstore";

    // Stores one permanent dialogue for each product
    private Map<Product, String> productDialogue = new HashMap<>();

    private Random random = new Random();

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

    // =========================================================
    // DEFAULT PRODUCTS
    // =========================================================

    private void addDefaultProducts() {

        // BOOKS

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

        assignDialogue(percyJackson);
        assignDialogue(metamorphosis);
        assignDialogue(nineteenEightyFour);


        // MOVIES

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

        assignDialogue(projectHailMary);
        assignDialogue(ironLung);


        // CONSUMABLES

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

        assignDialogue(strawberrySundae);
        assignDialogue(carbonara);
    }

    // =========================================================
    // PRODUCT DIALOGUE
    // =========================================================

    private void assignDialogue(Product product) {

        String[] lines;

        if (product instanceof Book) {

            lines = new String[] {
                "Ah, this one. Brings good memories.",
                "You have refined taste. Would you like that one?",
                "Oh, no. I wouldn't..."
            };

        } else if (product instanceof Movie) {

            lines = new String[] {
                "You're interested in this?",
                "I suppose we all have our tastes.",
                "This one! It's quite nice, yes."
            };

        } else {

            lines = new String[] {
                "This one is delightful!",
                "I do hope you enjoy this one.",
                "Ah, a lovely choice."
            };
        }

        int chosenLine = random.nextInt(lines.length);

        productDialogue.put(product, lines[chosenLine]);
    }

    private String getProductDialogue(Product product) {

        if (!productDialogue.containsKey(product)) {
            assignDialogue(product);
        }

        return productDialogue.get(product);
    }

    private void showProductDialogue(Product product) {

        String librarianLine =
            getProductDialogue(product);

        String productInformation =
            product.displayProduct();

        // Change Azira's expression depending on the dialogue

        if (librarianLine.equals(
            "Oh, no. I wouldn't..."
        )) {

            setLibrarianExpression("displeased");

        } else if (
            librarianLine.equals(
                "You have refined taste. Would you like that one?"
            )
        ) {

            setLibrarianExpression("happier");

        } else if (
            librarianLine.equals(
                "I suppose we all have our tastes."
            )
        ) {

            setLibrarianExpression("displeased");

        } else if (
            librarianLine.equals(
                "This one! It's quite nice, yes."
            )
        ) {

            setLibrarianExpression("happier");

        } else if (
            librarianLine.equals("This one is delightful!")
            || librarianLine.equals("I do hope you enjoy this one.")
            || librarianLine.equals("Ah, a lovely choice.")
        ) {

            setLibrarianExpression("happier");

        } else {

            setLibrarianExpression("happy");
        }

        dialogueLabel.setText(
            librarianLine
            + "\n\n"
            + productInformation
        );
    }

    // =========================================================
    // LIBRARIAN IMAGE
    // =========================================================

    private void setupLibrarianImage() {

        // Default expression
        setLibrarianExpression("happy");
    }

    private void setLibrarianExpression(String expression) {

        String imagePath =
            "/app/images/azira_" + expression + ".png";

        Image image = new Image(
            getClass().getResourceAsStream(imagePath)
        );

        librarianImage.setImage(image);
    }

    // =========================================================
    // ACTION DIALOGUES
    // =========================================================

    private String getBuyDialogue(Product product) {

        if (product instanceof Book) {

            return "Do enjoy that one.";

        } else if (product instanceof Movie) {

            return "Thank you for taking that off my hands.";

        } else {

            return "Thank you for ordering.";
        }
    }

    private String getDeleteDialogue(Product product) {

        if (product instanceof Book) {

            return "Aahh! My book!";

        } else if (product instanceof Movie) {

            return "Oh, did it fall off, where'd it go?";

        } else {

            return "Did you just... consume that without paying?";
        }
    }

    private String getUpdateDialogue(Product product) {

        if (product instanceof Book) {

            return "Always good to keep these things in tip top shape.";

        } else if (product instanceof Movie) {

            return "Does that have a new title? I forget.";

        } else {

            return "Oh, is that right? I swear I got the ingredients right...!";
        }
    }

    // =========================================================
    // TABLE SETUP
    // =========================================================

    private void setupTable() {

        nameColumn.setCellValueFactory(
            data -> new javafx.beans.property.SimpleStringProperty(
                data.getValue().getName()
            )
        );

        priceColumn.setCellValueFactory(
            data -> new javafx.beans.property.SimpleObjectProperty<>(
                data.getValue().getPrice()
            )
        );

        stockColumn.setCellValueFactory(
            data -> new javafx.beans.property.SimpleObjectProperty<>(
                data.getValue().getStock()
            )
        );

        genreColumn.setCellValueFactory(
            data -> {

                Product product = data.getValue();

                if (product instanceof Media) {

                    return new javafx.beans.property.SimpleStringProperty(
                        ((Media) product).getGenre()
                    );
                }

                return new javafx.beans.property.SimpleStringProperty("");
            }
        );

        releaseColumn.setCellValueFactory(
            data -> {

                Product product = data.getValue();

                if (product instanceof Media) {

                    return new javafx.beans.property.SimpleObjectProperty<>(
                        ((Media) product).getRelease()
                    );
                }

                return new javafx.beans.property.SimpleObjectProperty<>(
                    null
                );
            }
        );

        creatorColumn.setCellValueFactory(
            data -> {

                Product product = data.getValue();

                if (product instanceof Media) {

                    return new javafx.beans.property.SimpleStringProperty(
                        ((Media) product).getCreator()
                    );
                }

                return new javafx.beans.property.SimpleStringProperty("");
            }
        );

        detailsColumn.setCellValueFactory(
            data -> {

                Product product = data.getValue();

                if (product instanceof Book) {

                    return new javafx.beans.property.SimpleStringProperty(
                        "Pages: "
                        + ((Book) product).getPageCount()
                    );

                } else if (product instanceof Movie) {

                    return new javafx.beans.property.SimpleStringProperty(
                        "Runtime: "
                        + ((Movie) product).getRuntime()
                        + " min"
                    );
                }

                return new javafx.beans.property.SimpleStringProperty("");
            }
        );

        productTable.getSelectionModel()
            .selectedItemProperty()
            .addListener(
                (observable, oldValue, newValue) -> {

                    selectedProduct = newValue;

                    if (selectedProduct != null) {

                        fillFields(selectedProduct);

                        showProductDialogue(
                            selectedProduct
                        );
                    }
                }
            );
    }

    // =========================================================
    // STORE SWITCHING
    // =========================================================

    @FXML
    private void handleBookstore() {

        showBookstore();
    }

    private void showBookstore() {

        currentStore = "Bookstore";

        bookStockButton.setVisible(true);
        bookStockButton.setManaged(true);

        movieStockButton.setVisible(true);
        movieStockButton.setManaged(true);

        consumablesStockButton.setVisible(false);
        consumablesStockButton.setManaged(false);

        productTable.getItems().clear();

        productTable.getItems().addAll(
            bookstore.getProducts()
        );

        productTypeComboBox.getItems().clear();

        productTypeComboBox.getItems().addAll(
            "Book",
            "Movie"
        );

        productTypeComboBox.setValue("Book");

        updateProductFields();
        updateEarnings();

        setLibrarianExpression("happy");

        dialogueLabel.setText(
            "Welcome to the Bookstore!"
        );
    }

    @FXML
    private void handleCafe() {

        showCafe();
    }

    private void showCafe() {

        currentStore = "Cafe";

        bookStockButton.setVisible(false);
        bookStockButton.setManaged(false);

        movieStockButton.setVisible(false);
        movieStockButton.setManaged(false);

        consumablesStockButton.setVisible(true);
        consumablesStockButton.setManaged(true);

        productTable.getItems().clear();

        productTable.getItems().addAll(
            cafe.getMenu()
        );

        productTypeComboBox.getItems().clear();

        productTypeComboBox.getItems().add(
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

    // =========================================================
    // PRODUCT FIELD CONTROL
    // =========================================================

    private void updateProductFields() {

        String type =
            productTypeComboBox.getValue();

        boolean isBook =
            "Book".equals(type);

        boolean isMovie =
            "Movie".equals(type);

        boolean isConsumable =
            "Consumable".equals(type);

        genreField.setDisable(isConsumable);
        releaseField.setDisable(isConsumable);
        creatorField.setDisable(isConsumable);

        pageCountField.setDisable(!isBook);
        runtimeField.setDisable(!isMovie);

        if (isConsumable) {

            genreField.clear();
            releaseField.clear();
            creatorField.clear();
            pageCountField.clear();
            runtimeField.clear();
        }
    }

    // =========================================================
    // INPUT VALIDATION
    // =========================================================

    private boolean isNumber(String value) {

        try {

            Double.parseDouble(value);

            return true;

        } catch (NumberFormatException e) {

            return false;
        }
    }

    private boolean isWholeNumber(String value) {

        try {

            Integer.parseInt(value);

            return true;

        } catch (NumberFormatException e) {

            return false;
        }
    }

    private boolean validateBasicFields() {

        if (nameField.getText().trim().isEmpty()) {

            setLibrarianExpression("displeased");

            dialogueLabel.setText(
                "Please enter a product name."
            );

            return false;
        }

        if (priceField.getText().trim().isEmpty()) {

            setLibrarianExpression("displeased");

            dialogueLabel.setText(
                "Please enter a price."
            );

            return false;
        }

        if (!isNumber(
            priceField.getText().trim()
        )) {

            setLibrarianExpression("displeased");

            dialogueLabel.setText(
                "Please enter a valid number for the price."
            );

            return false;
        }

        if (stockField.getText().trim().isEmpty()) {

            setLibrarianExpression("displeased");

            dialogueLabel.setText(
                "Please enter the stock amount."
            );

            return false;
        }

        if (!isWholeNumber(
            stockField.getText().trim()
        )) {

            setLibrarianExpression("displeased");

            dialogueLabel.setText(
                "Please enter a whole number for the stock."
            );

            return false;
        }

        if (Double.parseDouble(
            priceField.getText().trim()
        ) < 0) {

            setLibrarianExpression("displeased");

            dialogueLabel.setText(
                "Price cannot be negative."
            );

            return false;
        }

        if (Integer.parseInt(
            stockField.getText().trim()
        ) < 0) {

            setLibrarianExpression("displeased");

            dialogueLabel.setText(
                "Stock cannot be negative."
            );

            return false;
        }

        return true;
    }

    private boolean validateMediaFields() {

        if (genreField.getText().trim().isEmpty()) {

            setLibrarianExpression("displeased");

            dialogueLabel.setText(
                "Please enter a genre."
            );

            return false;
        }

        if (releaseField.getText().trim().isEmpty()) {

            setLibrarianExpression("displeased");

            dialogueLabel.setText(
                "Please enter the release year."
            );

            return false;
        }

        if (!isWholeNumber(
            releaseField.getText().trim()
        )) {

            setLibrarianExpression("displeased");

            dialogueLabel.setText(
                "Please enter a valid whole number for the release year."
            );

            return false;
        }

        if (creatorField.getText().trim().isEmpty()) {

            setLibrarianExpression("displeased");

            dialogueLabel.setText(
                "Please enter a creator."
            );

            return false;
        }

        return true;
    }

    private boolean validateDetailField(String type) {

        if (type.equals("Book")) {

            if (pageCountField.getText().trim().isEmpty()) {

                setLibrarianExpression("displeased");

                dialogueLabel.setText(
                    "Please enter the page count."
                );

                return false;
            }

            if (!isWholeNumber(
                pageCountField.getText().trim()
            )) {

                setLibrarianExpression("displeased");

                dialogueLabel.setText(
                    "Please enter a valid whole number for the page count."
                );

                return false;
            }

            if (Integer.parseInt(
                pageCountField.getText().trim()
            ) < 0) {

                setLibrarianExpression("displeased");

                dialogueLabel.setText(
                    "Page count cannot be negative."
                );

                return false;
            }

        } else if (type.equals("Movie")) {

            if (runtimeField.getText().trim().isEmpty()) {

                setLibrarianExpression("displeased");

                dialogueLabel.setText(
                    "Please enter the movie runtime."
                );

                return false;
            }

            if (!isWholeNumber(
                runtimeField.getText().trim()
            )) {

                setLibrarianExpression("displeased");

                dialogueLabel.setText(
                    "Please enter a valid whole number for the runtime."
                );

                return false;
            }

            if (Integer.parseInt(
                runtimeField.getText().trim()
            ) < 0) {

                setLibrarianExpression("displeased");

                dialogueLabel.setText(
                    "Runtime cannot be negative."
                );

                return false;
            }
        }

        return true;
    }

    // =========================================================
    // ADD PRODUCT
    // =========================================================

    @FXML
    private void handleAdd() {

        if (!validateBasicFields()) {
            return;
        }

        String type =
            productTypeComboBox.getValue();

        double price =
            Double.parseDouble(
                priceField.getText().trim()
            );

        int stock =
            Integer.parseInt(
                stockField.getText().trim()
            );

        if (currentStore.equals("Bookstore")) {

            if (!validateMediaFields()) {
                return;
            }

            if (!validateDetailField(type)) {
                return;
            }

            String name =
                nameField.getText().trim();

            String genre =
                genreField.getText().trim();

            int release =
                Integer.parseInt(
                    releaseField.getText().trim()
                );

            String creator =
                creatorField.getText().trim();

            if (type.equals("Book")) {

                int pageCount =
                    Integer.parseInt(
                        pageCountField.getText().trim()
                    );

                Book book = new Book(
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

            } else if (type.equals("Movie")) {

                int runtime =
                    Integer.parseInt(
                        runtimeField.getText().trim()
                    );

                Movie movie = new Movie(
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

        } else {

            Consumables consumable =
                new Consumables(
                    nameField.getText().trim(),
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

    // =========================================================
    // UPDATE PRODUCT
    // =========================================================

    @FXML
    private void handleUpdate() {

        if (selectedProduct == null) {

            setLibrarianExpression("displeased");

            dialogueLabel.setText(
                "Please select a product first."
            );

            return;
        }

        if (!validateBasicFields()) {
            return;
        }

        if (
            selectedProduct instanceof Book
            || selectedProduct instanceof Movie
        ) {

            if (!validateMediaFields()) {
                return;
            }

            if (selectedProduct instanceof Book) {

                if (!validateDetailField("Book")) {
                    return;
                }

            } else {

                if (!validateDetailField("Movie")) {
                    return;
                }
            }
        }

        double price =
            Double.parseDouble(
                priceField.getText().trim()
            );

        int stock =
            Integer.parseInt(
                stockField.getText().trim()
            );

        String updateDialogue =
            getUpdateDialogue(selectedProduct);

        Product updatedProduct =
            selectedProduct;

        if (selectedProduct instanceof Book) {

            Book book =
                (Book) selectedProduct;

            book.setName(
                nameField.getText().trim()
            );

            book.setPrice(price);
            book.setStock(stock);

            book.setGenre(
                genreField.getText().trim()
            );

            book.setRelease(
                Integer.parseInt(
                    releaseField.getText().trim()
                )
            );

            book.setCreator(
                creatorField.getText().trim()
            );

            book.setPageCount(
                Integer.parseInt(
                    pageCountField.getText().trim()
                )
            );

        } else if (selectedProduct instanceof Movie) {

            Movie movie =
                (Movie) selectedProduct;

            movie.setName(
                nameField.getText().trim()
            );

            movie.setPrice(price);
            movie.setStock(stock);

            movie.setGenre(
                genreField.getText().trim()
            );

            movie.setRelease(
                Integer.parseInt(
                    releaseField.getText().trim()
                )
            );

            movie.setCreator(
                creatorField.getText().trim()
            );

            movie.setRuntime(
                Integer.parseInt(
                    runtimeField.getText().trim()
                )
            );

        } else if (
            selectedProduct instanceof Consumables
        ) {

            Consumables consumable =
                (Consumables) selectedProduct;

            consumable.setName(
                nameField.getText().trim()
            );

            consumable.setPrice(price);
            consumable.setStock(stock);
        }

        if (currentStore.equals("Bookstore")) {

            showBookstore();

        } else {

            showCafe();
        }

        selectedProduct = null;

        clearFields();

        if (updatedProduct instanceof Book) {

            setLibrarianExpression("happier");

        } else {

            setLibrarianExpression("displeased");
        }

        dialogueLabel.setText(
            updateDialogue
        );
    }

    // =========================================================
    // DELETE PRODUCT
    // =========================================================

    @FXML
    private void handleDelete() {

        if (selectedProduct == null) {

            setLibrarianExpression("shock");

            dialogueLabel.setText(
                "Please select a product first."
            );

            return;
        }

        String deleteDialogue =
            getDeleteDialogue(
                selectedProduct
            );

        if (selectedProduct instanceof Book) {

            bookstore.deleteBook(
                (Book) selectedProduct
            );

        } else if (
            selectedProduct instanceof Movie
        ) {

            bookstore.deleteMovie(
                (Movie) selectedProduct
            );

        } else if (
            selectedProduct instanceof Consumables
        ) {

            cafe.deleteConsumable(
                (Consumables) selectedProduct
            );
        }

        productDialogue.remove(
            selectedProduct
        );

        if (currentStore.equals("Bookstore")) {

            showBookstore();

        } else {

            showCafe();
        }

        selectedProduct = null;

        clearFields();

        setLibrarianExpression("shock");

        dialogueLabel.setText(
            deleteDialogue
        );
    }

    // =========================================================
    // BUY PRODUCT
    // =========================================================

    @FXML
    private void handleBuy() {

        if (selectedProduct == null) {

            setLibrarianExpression("happier");

            dialogueLabel.setText(
                "Please select a product first."
            );

            return;
        }

        if (selectedProduct.getStock() <= 0) {

            setLibrarianExpression("displeased");

            dialogueLabel.setText(
                "Sorry, that product is out of stock."
            );

            return;
        }

        String buyDialogue =
            getBuyDialogue(
                selectedProduct
            );

        Product boughtProduct =
            selectedProduct;

        if (currentStore.equals("Bookstore")) {

            bookstore.BuyProduct(
                selectedProduct
            );

            showBookstore();

        } else {

            cafe.BuyProduct(
                selectedProduct
            );

            showCafe();
        }

        updateEarnings();

        setLibrarianExpression("happier");

        dialogueLabel.setText(
            buyDialogue
        );

        selectedProduct =
            boughtProduct;
    }

    // =========================================================
    // STOCK BUTTONS
    // =========================================================

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

    // =========================================================
    // FIELD METHODS
    // =========================================================

    private void fillFields(Product product) {

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

        if (product instanceof Media) {

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

        } else {

            genreField.clear();
            releaseField.clear();
            creatorField.clear();
        }

        if (product instanceof Book) {

            Book book =
                (Book) product;

            pageCountField.setText(
                String.valueOf(
                    book.getPageCount()
                )
            );

            runtimeField.clear();

        } else if (product instanceof Movie) {

            Movie movie =
                (Movie) product;

            runtimeField.setText(
                String.valueOf(
                    movie.getRuntime()
                )
            );

            pageCountField.clear();

        } else {

            pageCountField.clear();
            runtimeField.clear();
        }
    }

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

    private void updateEarnings() {

        double bookstoreEarnings =
            bookstore.getEarnings();

        double cafeEarnings =
            cafe.getEarnings();

        double totalEarnings =
            bookstoreEarnings
            + cafeEarnings;

        earningsLabel.setText(
            String.format(
                "Earnings: PHP %.2f",
                totalEarnings
            )
        );
    }
}