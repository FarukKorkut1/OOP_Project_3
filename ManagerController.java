package application.managerscreens;

import java.io.IOException;

import application.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class ManagerController {

    private Stage stage;
    
    private Scene managerScene;

    public void ManagerScreen(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("manager_screen.fxml"));
            Parent root = loader.load();

            ManagerController controller = loader.getController();
            controller.setStage(stage); 

            managerScene = new Scene(root, 1000, 800);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void manageInventory() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("inventory_screen.fxml"));
            Parent root = loader.load();

            InventoryController controller = loader.getController();
            controller.setStage(stage); 

            stage.setScene(new Scene(root, 1000, 800));
            stage.setTitle("Manage Inventory");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void managePersonnel() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("personnel_screen.fxml"));
            Parent root = loader.load();

            PersonnelController controller = loader.getController();
            controller.setStage(stage);

            stage.setScene(new Scene(root, 1000, 800));
            stage.setTitle("Manage Personnel");
        } catch (IOException e) {
            showErrorDialog("Error", "Failed to load personnel management screen.");
            e.printStackTrace();
        }
    }

    @FXML
    private void managePricing() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("pricing_screen.fxml"));
            Parent root = loader.load();

            PricingController controller = loader.getController();
            controller.setStage(stage); 

            stage.setScene(new Scene(root, 1000, 800));
            stage.setTitle("Manage Pricing");
        } catch (IOException e) {
            showErrorDialog("Error", "Failed to load pricing management screen.");
            e.printStackTrace();
        }
    }

    @FXML
    private void reviewRevenue() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("refund_screen.fxml"));
            Parent root = loader.load();

            RefundController controller = loader.getController();
            controller.setStage(stage); 

            stage.setScene(new Scene(root, 1000, 800));
            stage.setTitle("Review Revenue");
        } catch (IOException e) {
            showErrorDialog("Error", "Failed to load revenue review screen.");
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
                    showErrorDialog("Error", "Failed to load login screen.");
                    e.printStackTrace();
                }
            }
        });
    }

    private void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
