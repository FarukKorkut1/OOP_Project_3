package application.cashierscreens;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DetailScreen {
    private Scene detailScene;

    public DetailScreen(Stage stage, String movieTitle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("detail_screen.fxml"));
            Parent root = loader.load();

            DetailController controller = loader.getController();
            controller.setStage(stage, movieTitle);

            detailScene = new Scene(root, 1000, 800);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Scene getDetailScene() {
        return detailScene;
    }
}
