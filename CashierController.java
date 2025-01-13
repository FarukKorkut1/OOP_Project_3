package application.cashierscreens;

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

public class CashierController {

    @FXML
    private Label userInfoLabel;

    @FXML
    private ComboBox<String> searchOptions;

    @FXML
    private TextField searchField;

    @FXML
    private ListView<String> searchResults;

    @FXML
    private Button logoutButton;

    private Stage stage;

    public void setStage(Stage stage, String username) {
        this.stage = stage;
        userInfoLabel.setText("Logged in as: " + username + " (Cashier)");

        ObservableList<String> options = FXCollections.observableArrayList(
                "By Genre",
                "By Partial Name",
                "By Full Name"
        );
        searchOptions.setItems(options);
    }

    @FXML
    private void searchMovies() {
        String searchType = searchOptions.getValue();
        String query = searchField.getText();

        if (searchType == null || query.isEmpty()) {
            searchResults.getItems().clear();
            searchResults.getItems().add("Please select a search type and enter a query.");
            return;
        }

        searchResults.setItems(FXCollections.observableArrayList("Movie 1", "Movie 2", "Movie 3"));
    }

    @FXML
    private void selectMovie() {
        String selectedMovie = searchResults.getSelectionModel().getSelectedItem();
        if (selectedMovie != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("detail_screen.fxml")); 
                Parent root = loader.load();

                DetailController detailController = loader.getController();
                detailController.setStage(stage, selectedMovie);

                stage.setScene(new Scene(root, 1000, 800));
            } catch (IOException e) {
                e.printStackTrace();
                showError("Failed to load the detail screen.");
            }
        } else {
            Alert warning = new Alert(Alert.AlertType.WARNING, "Please select a movie from the list.", ButtonType.OK);
            warning.showAndWait();
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
