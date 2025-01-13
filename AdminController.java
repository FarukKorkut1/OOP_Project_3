package application_adminscreens;

import java.io.IOException;

import application.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AdminController {

    @FXML
    private Button addMovieButton;

    @FXML
    private Button updateMovieButton;

    @FXML
    private Button scheduleMoviesButton;

    @FXML
    private Button processRefundsButton;

    @FXML
    private Button logoutButton;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    private void addMovie() {
        stage.setScene(new AddMovieScreen(stage).getAddMovieScene());
    }

    @FXML
    private void updateMovie() {
        stage.setScene(new UpdateMovieScreen(stage).getUpdateMovieScene());
    }

    @FXML
    private void scheduleMovies() {
        stage.setScene(new ScheduleMoviesScreen(stage).getScheduleMoviesScene());
    }

    @FXML
    private void processRefunds() {
        stage.setScene(new ProcessRefundsScreen(stage).getProcessRefundsScene());
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
