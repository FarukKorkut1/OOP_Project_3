package application.cashierscreens;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;

import application.LoginController;

public class ChairController {

    @FXML
    private Label titleLabel;

    @FXML
    private GridPane seatGrid;

    private Stage stage;
    private String movieTitle;

    public void setStage(Stage stage, String movieTitle) {
        this.stage = stage;
        this.movieTitle = movieTitle;

        titleLabel.setText("Movie: " + movieTitle + " | Select Your Seat");

        // Dynamically create seat grid
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                Button seat = new Button("R" + i + "C" + j);
                seat.setStyle("-fx-background-color: green;");
                seat.setOnAction(e -> {
                    if (seat.getStyle().contains("green")) {
                        seat.setStyle("-fx-background-color: red;");
                    } else {
                        seat.setStyle("-fx-background-color: green;");
                    }
                });
                seatGrid.add(seat, j, i);
            }
        }
    }

    @FXML
    private void proceed() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
            Parent root = loader.load();

            CartController controller = loader.getController();
            controller.setStage(stage, movieTitle); 

            stage.setScene(new Scene(root, 1000, 800));
        } catch (IOException e) {
            e.printStackTrace();
            showError("Failed to load the cart scene.");
        }
    }

    @FXML
    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("detail_screen.fxml"));
            Parent root = loader.load();

            DetailController controller = loader.getController();
            controller.setStage(stage, movieTitle);

            stage.setScene(new Scene(root, 1000, 800));
        } catch (IOException e) {
            e.printStackTrace();
            showError("Failed to load the detail screen.");
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

    private void showError(String message) {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        errorAlert.showAndWait();
    }
}
