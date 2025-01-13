package application.cashierscreens;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CashierScreen {
    private Scene cashierScene;

    public CashierScreen(Stage stage, String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cashier_screen.fxml"));
            Parent root = loader.load();

            CashierController controller = loader.getController();
            controller.setStage(stage, username);

            cashierScene = new Scene(root, 1000, 800);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Scene getCashierScene() {
        return cashierScene;
    }
}
