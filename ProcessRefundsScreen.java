package application_adminscreens;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ProcessRefundsScreen {
    private Scene processRefundsScene;

    public ProcessRefundsScreen(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("process_refunds_screen.fxml"));
            Parent root = loader.load();

            ProcessRefundsController controller = loader.getController();
            controller.setStage(stage);

            processRefundsScene = new Scene(root, 1000, 800);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Scene getProcessRefundsScene() {
        return processRefundsScene;
    }
}
