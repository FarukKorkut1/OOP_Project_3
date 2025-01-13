package application_adminscreens;

import java.io.File;
import java.io.IOException;

import application.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class UpdateMovieController {

    @FXML
    private TableView<Movie> movieTable;

    @FXML
    private TableColumn<Movie, String> titleColumn;

    @FXML
    private TableColumn<Movie, String> genreColumn;

    @FXML
    private TableColumn<Movie, String> summaryColumn;

    @FXML
    private TextField titleField;

    @FXML
    private ComboBox<String> genreComboBox;

    @FXML
    private TextArea summaryArea;

    @FXML
    private ImageView posterPreview;

    @FXML
    private Button updatePosterButton;

    @FXML
    private Button updateButton;

    @FXML
    private Button backButton;

    @FXML
    private Button logoutButton;

    private Stage stage;

    private final ObservableList<Movie> movies = FXCollections.observableArrayList();

    public void setStage(Stage stage) {
        this.stage = stage;
        initializeTable();
        initializeComboBox();
    }

    private void initializeTable() {
        titleColumn.setCellValueFactory(data -> data.getValue().titleProperty());
        genreColumn.setCellValueFactory(data -> data.getValue().genreProperty());
        summaryColumn.setCellValueFactory(data -> data.getValue().summaryProperty());

        movies.add(new Movie("Inception", "Sci-Fi", "A mind-bending thriller about dreams."));
        movies.add(new Movie("Titanic", "Romance", "A tragic love story on the Titanic."));
        movieTable.setItems(movies);

        movieTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                populateForm(newSelection);
            }
        });
    }

    private void initializeComboBox() {
        genreComboBox.getItems().addAll("Action", "Comedy", "Drama", "Horror", "Romance", "Sci-Fi", "Thriller");
    }

    private void populateForm(Movie movie) {
        titleField.setText(movie.getTitle());
        genreComboBox.setValue(movie.getGenre());
        summaryArea.setText(movie.getSummary());
        posterPreview.setImage(movie.getPoster());
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

            Movie selectedMovie = movieTable.getSelectionModel().getSelectedItem();
            if (selectedMovie != null) {
                selectedMovie.setPoster(posterImage);
                movieTable.refresh();
            }
        }
    }

    @FXML
    private void updateMovie() {
        Movie selectedMovie = movieTable.getSelectionModel().getSelectedItem();
        if (selectedMovie == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select a movie to update!", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        selectedMovie.setTitle(titleField.getText());
        selectedMovie.setGenre(genreComboBox.getValue());
        selectedMovie.setSummary(summaryArea.getText());
        movieTable.refresh();

        Alert success = new Alert(Alert.AlertType.INFORMATION, "Movie updated successfully!", ButtonType.OK);
        success.showAndWait();
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
