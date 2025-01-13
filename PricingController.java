package application.managerscreens;

import java.io.IOException;

import application.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class PricingController {

    @FXML
    private TextField hallAPriceField;

    @FXML
    private TextField hallBPriceField;

    @FXML
    private TextField beveragesPriceField;

    @FXML
    private TextField biscuitsPriceField;

    @FXML
    private TextField toysPriceField;

    @FXML
    private TextField discountRateField;

    @FXML
    private TextField ticketTaxField;

    @FXML
    private TextField productTaxField;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
        initializeFields();
    }

    private void initializeFields() {
        hallAPriceField.setText("10.0"); 
        hallBPriceField.setText("15.0"); 
        beveragesPriceField.setText("2.0");
        biscuitsPriceField.setText("1.5");
        toysPriceField.setText("5.0");
        discountRateField.setText("50"); 
        ticketTaxField.setText("20"); 
        productTaxField.setText("10"); 
    }

    @FXML
    private void saveChanges() {
        try {
            double hallAPrice = Double.parseDouble(hallAPriceField.getText());
            double hallBPrice = Double.parseDouble(hallBPriceField.getText());
            double beveragesPrice = Double.parseDouble(beveragesPriceField.getText());
            double biscuitsPrice = Double.parseDouble(biscuitsPriceField.getText());
            double toysPrice = Double.parseDouble(toysPriceField.getText());
            int discountRate = Integer.parseInt(discountRateField.getText());
            int ticketTax = Integer.parseInt(ticketTaxField.getText());
            int productTax = Integer.parseInt(productTaxField.getText());

            Alert successAlert = new Alert(Alert.AlertType.INFORMATION, "Changes saved successfully!", ButtonType.OK);
            successAlert.showAndWait();
        } catch (NumberFormatException e) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR, "Invalid input! Please enter valid numbers.", ButtonType.OK);
            errorAlert.showAndWait();
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
                    loginController.setStage(stage); 

                    stage.setScene(new Scene(root, 1000, 800));
                    stage.setTitle("Login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
