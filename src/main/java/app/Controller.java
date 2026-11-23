package app;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private ImageView librarianImage;

    @FXML
    private Label dialogueLabel;

    @FXML
    private ComboBox<String> productTypeComboBox;

    private Bookstore bookstore;
    private Cafe cafe;
    private Product selectedProduct;

    private String currentStore = "Bookstore";

    @FXML
    public void initialize() {

        bookstore = new Bookstore("BookBeans Bookstore", 0, 0, 0, 0);
        cafe = new Cafe("BookBeans Cafe", 0, 0, 0);

        setupTable();
        showBookstore();

        productTypeComboBox.setOnAction(event -> updateProductFields());
    }

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

        productTable.getSelectionModel().selectedItemProperty().addListener(
            (observable, oldValue, newValue) -> {

                selectedProduct = newValue;

                if (selectedProduct != null) {
                    fillFields(selectedProduct);
                }
            }
        );
    }
    private void updateProductFields() {

        String productType = productTypeComboBox.getValue();

        if (productType == null) {
            return;
        }

        if (productType.equals("Book")) {

            pageCountField.setDisable(false);
            runtimeField.setDisable(true);

            runtimeField.clear();

        } else if (productType.equals("Movie")) {

            pageCountField.setDisable(true);
            runtimeField.setDisable(false);

            pageCountField.clear();

        } else if (productType.equals("Consumable")) {

            pageCountField.setDisable(true);
            runtimeField.setDisable(true);

            pageCountField.clear();
            runtimeField.clear();
        }
    }

    @FXML
    private void handleBookstore() {
        showBookstore();
    }

    @FXML
    private void handleCafe() {
        showCafe();
    }

    private void showBookstore() {

        currentStore = "Bookstore";

        productTable.getItems().clear();

        productTable.getItems().addAll(bookstore.getBooks());
        productTable.getItems().addAll(bookstore.getMovies());

        productTypeComboBox.getItems().clear();
        productTypeComboBox.getItems().addAll("Book", "Movie");
        productTypeComboBox.setValue("Book");
        
        updateProductFields();

        dialogueLabel.setText("Welcome to the Bookstore!");
    }

    private void showCafe() {

        currentStore = "Cafe";

        productTable.getItems().clear();

        productTable.getItems().addAll(cafe.getMenu());

        productTypeComboBox.getItems().clear();
        productTypeComboBox.getItems().add("Consumable");
        productTypeComboBox.setValue("Consumable");

        updateProductFields();
        
        dialogueLabel.setText("Welcome to the Cafe!");
    }

    @FXML
    private void handleAdd() {

        String name = nameField.getText();

        double price = Double.parseDouble(
            priceField.getText()
        );

        int stock = Integer.parseInt(
            stockField.getText()
        );

        String productType = productTypeComboBox.getValue();

        if (name.isEmpty()) {

            dialogueLabel.setText(
                "Please enter a product name."
            );

            return;
        }

        if (currentStore.equals("Bookstore")) {

            String genre = genreField.getText();

            int release = Integer.parseInt(
                releaseField.getText()
            );

            String creator = creatorField.getText();

            if (productType.equals("Book")) {

                int pages = Integer.parseInt(
                    pageCountField.getText()
                );

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

                int runtime = Integer.parseInt(
                    runtimeField.getText()
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

                dialogueLabel.setText(
                    "The movie has been added!"
                );
            }

            showBookstore();

        } else {

            Consumables item = new Consumables(
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

    @FXML
    private void handleUpdate() {

        if (selectedProduct == null) {

            dialogueLabel.setText(
                "Please select a product first."
            );

            return;
        }

        String name = nameField.getText();

        double price = Double.parseDouble(
            priceField.getText()
        );

        int stock = Integer.parseInt(
            stockField.getText()
        );

        if (selectedProduct instanceof Book) {

            Book book = (Book) selectedProduct;

            bookstore.updateBook(
                book,
                name,
                price,
                stock,
                genreField.getText(),
                Integer.parseInt(releaseField.getText()),
                Integer.parseInt(pageCountField.getText()),
                creatorField.getText()
            );

            dialogueLabel.setText(
                "The book has been updated!"
            );

            showBookstore();

        } else if (selectedProduct instanceof Movie) {

            Movie movie = (Movie) selectedProduct;

            bookstore.updateMovie(
                movie,
                name,
                price,
                stock,
                genreField.getText(),
                Integer.parseInt(releaseField.getText()),
                Integer.parseInt(runtimeField.getText()),
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

        clearFields();

        selectedProduct = null;
    }

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

    @FXML
    private void handleBuy() {

        if (selectedProduct == null) {

            dialogueLabel.setText(
                "Please select a product first."
            );

            return;
        }

        if (selectedProduct instanceof Book
                || selectedProduct instanceof Movie) {

            bookstore.BuyProduct(selectedProduct);

            dialogueLabel.setText(
                "Thank you for your purchase!"
            );

            showBookstore();

        } else if (selectedProduct instanceof Consumables) {

            cafe.BuyProduct(selectedProduct);

            dialogueLabel.setText(
                "Thank you for your order!"
            );

            showCafe();
        }
    }

    private void fillFields(Product product) {

        nameField.setText(
            product.getName()
        );

        priceField.setText(
            String.valueOf(product.getPrice())
        );

        stockField.setText(
            String.valueOf(product.getStock())
        );

        genreField.clear();
        releaseField.clear();
        creatorField.clear();
        pageCountField.clear();
        runtimeField.clear();

        if (product instanceof Book) {

            Book book = (Book) product;

            genreField.setText(
                book.getGenre()
            );

            releaseField.setText(
                String.valueOf(book.getRelease())
            );

            creatorField.setText(
                book.getCreator()
            );

            pageCountField.setText(
                String.valueOf(book.getPageCount())
            );

        } else if (product instanceof Movie) {

            Movie movie = (Movie) product;

            genreField.setText(
                movie.getGenre()
            );

            releaseField.setText(
                String.valueOf(movie.getRelease())
            );

            creatorField.setText(
                movie.getCreator()
            );

            runtimeField.setText(
                String.valueOf(movie.getRuntime())
            );
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
}