package application_adminscreens;

import java.io.File;
import java.io.IOException;

import application.LoginController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AddMovieController {

    @FXML
    private TextField titleField;

    @FXML
    private ComboBox<String> genreComboBox;

    @FXML
    private TextArea summaryArea;

    @FXML
    private ImageView posterPreview;

    @FXML
    private Button addButton;

    @FXML
    private Button backButton;

    @FXML
    private Button logoutButton;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
        initializeComboBox();
    }

    private void initializeComboBox() {
        genreComboBox.getItems().addAll(
            "Action",
            "Comedy",
            "Drama",
            "Horror",
            "Romance",
            "Sci-Fi",
            "Thriller"
        );
    }

    @FXML
    private void selectPoster() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Movie Poster");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );

        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            Image posterImage = new Image(selectedFile.toURI().toString());
            posterPreview.setImage(posterImage);
        }
    }

    @FXML
    private void addMovie() {
        if (titleField.getText().isEmpty() || genreComboBox.getValue() == null || summaryArea.getText().isEmpty()) {
            Alert error = new Alert(Alert.AlertType.ERROR, "All fields (Movie Title, Genre, Summary) are mandatory!", ButtonType.OK);
            error.showAndWait();
        } else {
            Alert success = new Alert(Alert.AlertType.INFORMATION, "Movie added successfully!", ButtonType.OK);
            success.showAndWait();

            stage.setScene(new AdminScreen(stage).getAdminScene());
        }
    }

    @FXML
    private void goBack() {
        stage.setScene(new AdminScreen(stage).getAdminScene());
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
