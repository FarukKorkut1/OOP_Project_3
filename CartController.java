package application.cashierscreens;

import java.io.IOException;

import application.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class CartController {
    private Stage stage;
    private String movieTitle;
    private ObservableList<String> cartItems = FXCollections.observableArrayList();

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private ComboBox<String> beveragesComboBox;

    @FXML
    private ComboBox<String> biscuitsComboBox;

    @FXML
    private ComboBox<String> toysComboBox;

    @FXML
    private ListView<String> shoppingCartList;

    @FXML
    private Label totalPriceLabel;

    private double totalPrice = 0.0;

    public void setStage(Stage stage, String movieTitle) {
        this.stage = stage;
        this.movieTitle = movieTitle;

        // Initialize shopping cart
        shoppingCartList.setItems(cartItems);

        // Populate ComboBoxes
        beveragesComboBox.setItems(FXCollections.observableArrayList("Coke - $2.5", "Pepsi - $2.5", "Water - $1.0"));
        biscuitsComboBox.setItems(FXCollections.observableArrayList("Oreo - $3.0", "Digestive - $2.0", "Bourbon - $2.5"));
        toysComboBox.setItems(FXCollections.observableArrayList("Car - $5.0", "Doll - $7.0", "Puzzle - $4.0"));

        updateTotalPrice();
    }

    @FXML
    private void addToCart() {
        if (!firstNameField.getText().isEmpty() && !lastNameField.getText().isEmpty()) {
            String selectedBeverage = beveragesComboBox.getValue();
            String selectedBiscuit = biscuitsComboBox.getValue();
            String selectedToy = toysComboBox.getValue();

            if (selectedBeverage != null) {
                addItemToCart(selectedBeverage, parsePrice(selectedBeverage));
            }
            if (selectedBiscuit != null) {
                addItemToCart(selectedBiscuit, parsePrice(selectedBiscuit));
            }
            if (selectedToy != null) {
                addItemToCart(selectedToy, parsePrice(selectedToy));
            }

            // Clear selections
            beveragesComboBox.setValue(null);
            biscuitsComboBox.setValue(null);
            toysComboBox.setValue(null);
        } else {
            showAlert(Alert.AlertType.ERROR, "Missing Information", "Please enter the customer's first and last name.");
        }
    }

    private void addItemToCart(String itemName, double price) {
        cartItems.add(itemName);
        totalPrice += price;
        updateTotalPrice();
    }

    private double parsePrice(String item) {
        String[] parts = item.split(" - \\$");
        return Double.parseDouble(parts[1]);
    }

    private void updateTotalPrice() {
        totalPriceLabel.setText("Total: $" + String.format("%.2f", totalPrice));
    }

    @FXML
    private void proceed() {
        if (cartItems.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Cart Empty", "Please add items to the cart before proceeding.");
        } else {
            // Proceed to the next stage
            System.out.println("Proceeding to the final stage...");
        }
    }

    @FXML
    private void goBack() {
        ChairScreen chairScreen = new ChairScreen(stage, movieTitle);
        stage.setScene(chairScreen.getChairScene());
    }

    @FXML
    private void logout() {
        Alert confirmLogout = new Alert(Alert.AlertType.CONFIRMATION);
        confirmLogout.setTitle("Logout Confirmation");
        confirmLogout.setHeaderText("Are you sure you want to logout?");
        confirmLogout.setContentText("Unsaved changes will be lost.");

        confirmLogout.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/application/LoginScreen.fxml"));
                    Parent root = loader.load();

                    LoginController loginController = loader.getController();
                    loginController.setStage(stage); // Pass the stage to LoginController

                    stage.setScene(new Scene(root, 1000, 800));
                    stage.setTitle("Login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
