package application_adminscreens;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

import application.LoginController;

public class ProcessRefundsController {

    @FXML
    private ComboBox<String> refundTypeComboBox;

    @FXML
    private TextField customerNameField;

    @FXML
    private ComboBox<String> itemComboBox;

    @FXML
    private TextArea refundSummaryArea;

    @FXML
    private Button processRefundButton;

    @FXML
    private Button backButton;

    @FXML
    private Button logoutButton;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
        initializeComponents();
    }

    private void initializeComponents() {
        ObservableList<String> refundTypes = FXCollections.observableArrayList("Bilet", "Ürün");
        refundTypeComboBox.setItems(refundTypes);

        refundTypeComboBox.setOnAction(e -> loadItems());
    }

    private void loadItems() {
        String selectedType = refundTypeComboBox.getValue();
        itemComboBox.getItems().clear();

        if ("Bilet".equals(selectedType)) {
            itemComboBox.getItems().addAll("Bilet 1", "Bilet 2", "Bilet 3"); 
        } else if ("Ürün".equals(selectedType)) {
            itemComboBox.getItems().addAll("Kola", "Patlamış Mısır", "Su"); 
        }
    }

    @FXML
    private void processRefund() {
        String refundType = refundTypeComboBox.getValue();
        String customerName = customerNameField.getText();
        String selectedItem = itemComboBox.getValue();

        if (refundType == null || customerName.isEmpty() || selectedItem == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "All fields are mandatory!", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        refundSummaryArea.setText("Refund processed for " + refundType + ":\n" +
                "Customer: " + customerName + "\n" +
                "Item: " + selectedItem + "\n" +
                "Total Refund: X TL"); 

        Alert success = new Alert(Alert.AlertType.INFORMATION, "Refund processed successfully!", ButtonType.OK);
        success.showAndWait();
    }

    @FXML
    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_screen.fxml")); 
            Parent root = loader.load();

            AdminController adminController = loader.getController();
            adminController.setStage(stage);

            stage.setScene(new Scene(root, 1000, 800));
        } catch (IOException e) {
            e.printStackTrace();
            showError("Failed to load the Admin Screen.");
        }
    }

    private void showError(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        errorAlert.showAndWait();
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
}
