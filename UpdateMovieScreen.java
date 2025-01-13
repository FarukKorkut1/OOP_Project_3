package application_adminscreens;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UpdateMovieScreen {
    private Scene updateMovieScene;

    public UpdateMovieScreen(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("update_movie_screen.fxml"));
            Parent root = loader.load();

            UpdateMovieController controller = loader.getController();
            controller.setStage(stage);

            updateMovieScene = new Scene(root, 1000, 800);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Scene getUpdateMovieScene() {
        return updateMovieScene;
    }
}
