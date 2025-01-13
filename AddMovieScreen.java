package application_adminscreens;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddMovieScreen {
    private Scene addMovieScene;

    public AddMovieScreen(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("add_movie_screen.fxml"));
            Parent root = loader.load();

            AddMovieController controller = loader.getController();
            controller.setStage(stage);

            addMovieScene = new Scene(root, 1000, 800);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Scene getAddMovieScene() {
        return addMovieScene;
    }
}
