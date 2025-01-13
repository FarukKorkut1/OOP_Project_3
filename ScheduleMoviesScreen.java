package application_adminscreens;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ScheduleMoviesScreen {
    private Scene scheduleMoviesScene;

    public ScheduleMoviesScreen(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("schedule_movies_screen.fxml"));
            Parent root = loader.load();

            ScheduleMoviesController controller = loader.getController();
            controller.setStage(stage);

            scheduleMoviesScene = new Scene(root, 1000, 800);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Scene getScheduleMoviesScene() {
        return scheduleMoviesScene;
    }
}
