package application.cashierscreens;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Cart {
    private Scene cartScene;

    public Cart(Stage stage, String movieTitle) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("cart.fxml"));
            Parent root = loader.load();

            CartController controller = loader.getController();
            controller.setStage(stage, movieTitle); 

            cartScene = new Scene(root);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Scene getCartScene() {
        return cartScene;
    }
}
