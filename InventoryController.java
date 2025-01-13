package application.managerscreens;

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

public class InventoryController {

    @FXML
    private TableView<Object> inventoryTable;

    @FXML
    private TableColumn<Object, String> productColumn;

    @FXML
    private TableColumn<Object, Integer> stockColumn;

    @FXML
    private TableColumn<Object, String> updateColumn;

    @FXML
    private ComboBox<String> productComboBox;

    @FXML
    private TextField newStockField;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;

        ObservableList<Object> inventoryData = FXCollections.observableArrayList(
                new InventoryItem("Beverage", 20),
                new InventoryItem("Biscuit", 15),
                new InventoryItem("Toy", 10)
        );

        inventoryTable.setItems(inventoryData);
    }

    @FXML
    private void updateStock() {
        String selectedProduct = productComboBox.getValue();
        String newStockValue = newStockField.getText();

        if (selectedProduct == null || newStockValue.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Missing Information", "Please select a product and enter a valid stock quantity.");
            return;
        }

        try {
            int updatedStock = Integer.parseInt(newStockValue);
            // Update the inventory logic here (example: apply to database or local data)

            showAlert(Alert.AlertType.INFORMATION, "Stock Updated", "The stock for " + selectedProduct + " has been updated to " + updatedStock);
            newStockField.clear();
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Invalid Input", "Please enter a valid number for stock quantity.");
        }
    }

    @FXML
    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("manager_screen.fxml"));
            Parent root = loader.load();

            ManagerController managerController = loader.getController();
            managerController.setStage(stage); 

            stage.setScene(new Scene(root, 1000, 800));
            stage.setTitle("Manager Dashboard");
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Example data class for inventory items
    public static class InventoryItem {
        private final String productName;
        private final Integer stockQuantity;

        public InventoryItem(String productName, Integer stockQuantity) {
            this.productName = productName;
            this.stockQuantity = stockQuantity;
        }

        public String getProductName() {
            return productName;
        }

        public Integer getStockQuantity() {
            return stockQuantity;
        }
    }
}
