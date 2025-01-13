package application.cashierscreens;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ChairScreen {
    private Scene chairScene;

    public ChairScreen(Stage stage, String movieTitle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("chair_screen.fxml"));
            Parent root = loader.load();

            ChairController controller = loader.getController();
            controller.setStage(stage, movieTitle);

            chairScene = new Scene(root, 1000, 800);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Scene getChairScene() {
        return chairScene;
    }
}
