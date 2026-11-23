package app;

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
import product.Movie;
import product.Product;
import storefront.Bookstore;
import storefront.Cafe;

public class Controller {

    @FXML
    private Button bookstoreButton;

    @FXML
    private Button cafeButton;

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

    private Bookstore bookstore;
    private Cafe cafe;

    private Product selectedProduct;

    private String currentStore = "Bookstore";

    @FXML
    public void initialize() {

        bookstore = new Bookstore(
            "BookBeans Bookstore",
            0,
            0,
            0,
            0
        );

        cafe = new Cafe(
            "BookBeans Cafe",
            0,
            0,
            0
        );

        setupTable();

        setupLibrarianImage();

        showBookstore();

        productTypeComboBox.setOnAction(
            event -> updateProductFields()
        );
    }

    // ---------------------------------------------------------
    // TABLE
    // ---------------------------------------------------------

    private void setupTable() {

        nameColumn.setCellValueFactory(cell ->
            new javafx.beans.property.SimpleStringProperty(
                cell.getValue().getName()
            )
        );

        priceColumn.setCellValueFactory(cell ->
            new javafx.beans.property.SimpleObjectProperty<>(
                cell.getValue().getPrice()
            )
        );

        stockColumn.setCellValueFactory(cell ->
            new javafx.beans.property.SimpleObjectProperty<>(
                cell.getValue().getStock()
            )
        );

        genreColumn.setCellValueFactory(cell -> {

            if (cell.getValue() instanceof Book) {

                Book book = (Book) cell.getValue();

                return new javafx.beans.property.SimpleStringProperty(
                    book.getGenre()
                );

            } else if (cell.getValue() instanceof Movie) {

                Movie movie = (Movie) cell.getValue();

                return new javafx.beans.property.SimpleStringProperty(
                    movie.getGenre()
                );
            }

            return new javafx.beans.property.SimpleStringProperty("");
        });

        releaseColumn.setCellValueFactory(cell -> {

            if (cell.getValue() instanceof Book) {

                Book book = (Book) cell.getValue();

                return new javafx.beans.property.SimpleObjectProperty<>(
                    book.getRelease()
                );

            } else if (cell.getValue() instanceof Movie) {

                Movie movie = (Movie) cell.getValue();

                return new javafx.beans.property.SimpleObjectProperty<>(
                    movie.getRelease()
                );
            }

            return new javafx.beans.property.SimpleObjectProperty<>(0);
        });

        creatorColumn.setCellValueFactory(cell -> {

            if (cell.getValue() instanceof Book) {

                Book book = (Book) cell.getValue();

                return new javafx.beans.property.SimpleStringProperty(
                    book.getCreator()
                );

            } else if (cell.getValue() instanceof Movie) {

                Movie movie = (Movie) cell.getValue();

                return new javafx.beans.property.SimpleStringProperty(
                    movie.getCreator()
                );
            }

            return new javafx.beans.property.SimpleStringProperty("");
        });

        detailsColumn.setCellValueFactory(cell -> {

            if (cell.getValue() instanceof Book) {

                Book book = (Book) cell.getValue();

                return new javafx.beans.property.SimpleStringProperty(
                    book.getPageCount() + " pages"
                );

            } else if (cell.getValue() instanceof Movie) {

                Movie movie = (Movie) cell.getValue();

                return new javafx.beans.property.SimpleStringProperty(
                    movie.getRuntime() + " min"
                );
            }

            return new javafx.beans.property.SimpleStringProperty("");
        });

        // Detect which product the user selected
        productTable.getSelectionModel()
            .selectedItemProperty()
            .addListener((observable, oldValue, newValue) -> {

                selectedProduct = newValue;

                if (selectedProduct != null) {
                    fillFields(selectedProduct);
                }
            });
    }

    // ---------------------------------------------------------
    // LIBRARIAN IMAGE
    // ---------------------------------------------------------

    private void setupLibrarianImage() {

        /*
         * Put your future image here:
         *
         * src/main/resources/app/images/librarian.png
         *
         * For now, if the image does not exist,
         * the ImageView simply stays empty.
         */

        String imagePath = "/app/images/librarian.png";

        if (getClass().getResource(imagePath) != null) {

            Image image = new Image(
                getClass().getResource(imagePath).toExternalForm()
            );

            librarianImage.setImage(image);
        }
    }

    // ---------------------------------------------------------
    // STORE BUTTONS
    // ---------------------------------------------------------

    @FXML
    private void handleBookstore() {

        showBookstore();
    }

    @FXML
    private void handleCafe() {

        showCafe();
    }

    // ---------------------------------------------------------
    // SHOW BOOKSTORE
    // ---------------------------------------------------------

    private void showBookstore() {

        currentStore = "Bookstore";

        productTable.getItems().clear();

        productTable.getItems().addAll(
            bookstore.getBooks()
        );

        productTable.getItems().addAll(
            bookstore.getMovies()
        );

        productTypeComboBox.getItems().clear();

        productTypeComboBox.getItems().addAll(
            "Book",
            "Movie"
        );

        productTypeComboBox.setValue("Book");

        updateProductFields();

        updateEarnings();

        dialogueLabel.setText(
            "Welcome to the Bookstore!"
        );
    }

    // ---------------------------------------------------------
    // SHOW CAFE
    // ---------------------------------------------------------

    private void showCafe() {

        currentStore = "Cafe";

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

        dialogueLabel.setText(
            "Welcome to the Cafe!"
        );
    }

    // ---------------------------------------------------------
    // PRODUCT FIELD VISIBILITY
    // ---------------------------------------------------------

    private void updateProductFields() {

        String productType =
            productTypeComboBox.getValue();

        if (productType == null) {
            return;
        }

        if (productType.equals("Book")) {

            pageCountField.setVisible(true);
            pageCountField.setManaged(true);

            runtimeField.setVisible(false);
            runtimeField.setManaged(false);

            runtimeField.clear();

        } else if (productType.equals("Movie")) {

            pageCountField.setVisible(false);
            pageCountField.setManaged(false);

            runtimeField.setVisible(true);
            runtimeField.setManaged(true);

            pageCountField.clear();

        } else if (productType.equals("Consumable")) {

            pageCountField.setVisible(false);
            pageCountField.setManaged(false);

            runtimeField.setVisible(false);
            runtimeField.setManaged(false);

            pageCountField.clear();
            runtimeField.clear();
        }
    }

    // ---------------------------------------------------------
    // ADD PRODUCT
    // ---------------------------------------------------------

    @FXML
    private void handleAdd() {

        if (!validateBasicFields()) {
            return;
        }

        String name = nameField.getText();

        Double price = getDouble(
            priceField.getText()
        );

        Integer stock = getInteger(
            stockField.getText()
        );

        if (price == null || stock == null) {
            return;
        }

        if (price < 0) {

            dialogueLabel.setText(
                "Price cannot be negative."
            );

            return;
        }

        if (stock < 0) {

            dialogueLabel.setText(
                "Stock cannot be negative."
            );

            return;
        }

        String productType =
            productTypeComboBox.getValue();

        if (currentStore.equals("Bookstore")) {

            String genre =
                genreField.getText();

            String creator =
                creatorField.getText();

            if (genre.isEmpty() || creator.isEmpty()) {

                dialogueLabel.setText(
                    "Please fill in the genre and creator."
                );

                return;
            }

            Integer release =
                getInteger(
                    releaseField.getText()
                );

            if (release == null) {
                return;
            }

            if (productType.equals("Book")) {

                Integer pages =
                    getInteger(
                        pageCountField.getText()
                    );

                if (pages == null) {
                    return;
                }

                if (pages <= 0) {

                    dialogueLabel.setText(
                        "Page count must be greater than 0."
                    );

                    return;
                }

                Book book = new Book(
                    name,
                    price,
                    stock,
                    genre,
                    release,
                    pages,
                    creator
                );

                bookstore.addBook(book);

                dialogueLabel.setText(
                    "The book has been added!"
                );

            } else if (productType.equals("Movie")) {

                Integer runtime =
                    getInteger(
                        runtimeField.getText()
                    );

                if (runtime == null) {
                    return;
                }

                if (runtime <= 0) {

                    dialogueLabel.setText(
                        "Runtime must be greater than 0."
                    );

                    return;
                }

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

                dialogueLabel.setText(
                    "The movie has been added!"
                );
            }

            showBookstore();

        } else {

            Consumables item =
                new Consumables(
                    name,
                    price,
                    stock
                );

            cafe.addConsumable(item);

            dialogueLabel.setText(
                "The item has been added to the cafe!"
            );

            showCafe();
        }

        clearFields();
    }

    // ---------------------------------------------------------
    // UPDATE PRODUCT
    // ---------------------------------------------------------

    @FXML
    private void handleUpdate() {

        if (selectedProduct == null) {

            dialogueLabel.setText(
                "Please select a product first."
            );

            return;
        }

        if (!validateBasicFields()) {
            return;
        }

        String name =
            nameField.getText();

        Double price =
            getDouble(
                priceField.getText()
            );

        Integer stock =
            getInteger(
                stockField.getText()
            );

        if (price == null || stock == null) {
            return;
        }

        if (price < 0) {

            dialogueLabel.setText(
                "Price cannot be negative."
            );

            return;
        }

        if (stock < 0) {

            dialogueLabel.setText(
                "Stock cannot be negative."
            );

            return;
        }

        if (selectedProduct instanceof Book) {

            Book book =
                (Book) selectedProduct;

            Integer release =
                getInteger(
                    releaseField.getText()
                );

            Integer pages =
                getInteger(
                    pageCountField.getText()
                );

            if (release == null || pages == null) {
                return;
            }

            if (pages <= 0) {

                dialogueLabel.setText(
                    "Page count must be greater than 0."
                );

                return;
            }

            bookstore.updateBook(
                book,
                name,
                price,
                stock,
                genreField.getText(),
                release,
                pages,
                creatorField.getText()
            );

            dialogueLabel.setText(
                "The book has been updated!"
            );

            showBookstore();

        } else if (selectedProduct instanceof Movie) {

            Movie movie =
                (Movie) selectedProduct;

            Integer release =
                getInteger(
                    releaseField.getText()
                );

            Integer runtime =
                getInteger(
                    runtimeField.getText()
                );

            if (release == null || runtime == null) {
                return;
            }

            if (runtime <= 0) {

                dialogueLabel.setText(
                    "Runtime must be greater than 0."
                );

                return;
            }

            bookstore.updateMovie(
                movie,
                name,
                price,
                stock,
                genreField.getText(),
                release,
                runtime,
                creatorField.getText()
            );

            dialogueLabel.setText(
                "The movie has been updated!"
            );

            showBookstore();

        } else if (selectedProduct instanceof Consumables) {

            cafe.updateConsumable(
                (Consumables) selectedProduct,
                name,
                price,
                stock
            );

            dialogueLabel.setText(
                "The cafe item has been updated!"
            );

            showCafe();
        }

        selectedProduct = null;

        clearFields();
    }

    // ---------------------------------------------------------
    // DELETE PRODUCT
    // ---------------------------------------------------------

    @FXML
    private void handleDelete() {

        if (selectedProduct == null) {

            dialogueLabel.setText(
                "Please select a product first."
            );

            return;
        }

        if (selectedProduct instanceof Book) {

            bookstore.deleteBook(
                (Book) selectedProduct
            );

            dialogueLabel.setText(
                "The book has been deleted."
            );

            showBookstore();

        } else if (selectedProduct instanceof Movie) {

            bookstore.deleteMovie(
                (Movie) selectedProduct
            );

            dialogueLabel.setText(
                "The movie has been deleted."
            );

            showBookstore();

        } else if (selectedProduct instanceof Consumables) {

            cafe.deleteConsumable(
                (Consumables) selectedProduct
            );

            dialogueLabel.setText(
                "The cafe item has been deleted."
            );

            showCafe();
        }

        selectedProduct = null;

        clearFields();
    }

    // ---------------------------------------------------------
    // BUY PRODUCT
    // ---------------------------------------------------------

    @FXML
    private void handleBuy() {

        if (selectedProduct == null) {

            dialogueLabel.setText(
                "Please select a product first."
            );

            return;
        }

        // Prevent purchase if stock is already 0
        if (selectedProduct.getStock() <= 0) {

            dialogueLabel.setText(
                "Sorry, this product is out of stock."
            );

            return;
        }

        if (selectedProduct instanceof Book
                || selectedProduct instanceof Movie) {

            bookstore.BuyProduct(
                selectedProduct
            );

            dialogueLabel.setText(
                "Thank you for your purchase!"
            );

            showBookstore();

        } else if (selectedProduct instanceof Consumables) {

            cafe.BuyProduct(
                selectedProduct
            );

            dialogueLabel.setText(
                "Thank you for your order!"
            );

            showCafe();
        }

        updateEarnings();
    }

    // ---------------------------------------------------------
    // FILL FIELDS WHEN SELECTING A PRODUCT
    // ---------------------------------------------------------

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

        genreField.clear();
        releaseField.clear();
        creatorField.clear();
        pageCountField.clear();
        runtimeField.clear();

        if (product instanceof Book) {

            Book book =
                (Book) product;

            productTypeComboBox.setValue(
                "Book"
            );

            genreField.setText(
                book.getGenre()
            );

            releaseField.setText(
                String.valueOf(
                    book.getRelease()
                )
            );

            creatorField.setText(
                book.getCreator()
            );

            pageCountField.setText(
                String.valueOf(
                    book.getPageCount()
                )
            );

        } else if (product instanceof Movie) {

            Movie movie =
                (Movie) product;

            productTypeComboBox.setValue(
                "Movie"
            );

            genreField.setText(
                movie.getGenre()
            );

            releaseField.setText(
                String.valueOf(
                    movie.getRelease()
                )
            );

            creatorField.setText(
                movie.getCreator()
            );

            runtimeField.setText(
                String.valueOf(
                    movie.getRuntime()
                )
            );

        } else if (product instanceof Consumables) {

            productTypeComboBox.setValue(
                "Consumable"
            );
        }

        updateProductFields();
    }

    // ---------------------------------------------------------
    // VALIDATION
    // ---------------------------------------------------------

    private boolean validateBasicFields() {

        if (nameField.getText().isEmpty()) {

            dialogueLabel.setText(
                "Please enter a product name."
            );

            return false;
        }

        if (priceField.getText().isEmpty()) {

            dialogueLabel.setText(
                "Please enter a price."
            );

            return false;
        }

        if (stockField.getText().isEmpty()) {

            dialogueLabel.setText(
                "Please enter the stock."
            );

            return false;
        }

        return true;
    }

    private Double getDouble(String text) {

        try {

            return Double.parseDouble(text);

        } catch (NumberFormatException e) {

            dialogueLabel.setText(
                "Please enter a valid number for the price."
            );

            return null;
        }
    }

    private Integer getInteger(String text) {

        try {

            return Integer.parseInt(text);

        } catch (NumberFormatException e) {

            dialogueLabel.setText(
                "Please enter a valid whole number."
            );

            return null;
        }
    }

    // ---------------------------------------------------------
    // EARNINGS
    // ---------------------------------------------------------

    private void updateEarnings() {

        if (currentStore.equals("Bookstore")) {

            earningsLabel.setText(
                String.format(
                    "Earnings: PHP %.2f",
                    bookstore.getEarnings()
                )
            );

        } else {

            earningsLabel.setText(
                String.format(
                    "Earnings: PHP %.2f",
                    cafe.getEarnings()
                )
            );
        }
    }

    // ---------------------------------------------------------
    // CLEAR FIELDS
    // ---------------------------------------------------------

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