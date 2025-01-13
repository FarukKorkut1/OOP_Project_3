package application.cashierscreens;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class DetailController {

    @FXML
    private Label titleLabel;

    @FXML
    private ComboBox<String> dayComboBox;

    @FXML
    private ComboBox<String> sessionComboBox;

    private Stage stage;
    private String movieTitle;

    public void setStage(Stage stage, String movieTitle) {
        this.stage = stage;
        this.movieTitle = movieTitle;
        titleLabel.setText("Movie: " + movieTitle);

        dayComboBox.setItems(FXCollections.observableArrayList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"));
        sessionComboBox.setItems(FXCollections.observableArrayList("Morning", "Afternoon", "Evening"));
    }

    @FXML
    private void confirm() {
        String selectedDay = dayComboBox.getValue();
        String selectedSession = sessionComboBox.getValue();

        if (selectedDay != null && selectedSession != null) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("chair_screen.fxml"));
                Parent root = loader.load();

                ChairController chairController = loader.getController();
                chairController.setStage(stage, movieTitle);

                stage.setScene(new Scene(root, 1000, 800));
            } catch (IOException e) {
                e.printStackTrace();
                showError("Failed to load the chair selection screen.");
            }
        } else {
            Alert warning = new Alert(Alert.AlertType.WARNING, "Please select day and session.", ButtonType.OK);
            warning.showAndWait();
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
                    stage.setScene(new Scene(root, 1000, 800));
                } catch (IOException e) {
                    e.printStackTrace();
                    showError("Failed to load the login screen.");
                }
            }
        });
    }

    private void showError(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        errorAlert.showAndWait();
    }
}
