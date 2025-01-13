package application_adminscreens;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AdminScreen {
    private Scene adminScene;

    public AdminScreen(Stage stage) {
        this(stage, ""); 
    }

    public AdminScreen(Stage stage, String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("admin_screen.fxml"));
            Parent root = loader.load();

            AdminController controller = loader.getController();
            controller.setStage(stage);

            System.out.println("AdminScreen loaded for user: " + username);

            adminScene = new Scene(root, 1000, 800);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Scene getAdminScene() {
        return adminScene;
    }
}
