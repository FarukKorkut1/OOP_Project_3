package application.managerscreens;

import java.io.IOException;

import application.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class RefundController {

	@FXML
	private ComboBox<String> refundTypeComboBox;

	@FXML
	private TextField customerNameField;

	@FXML
	private TableView<RefundItem> refundItemsTable;

	@FXML
	private TableColumn<RefundItem, String> itemColumn;

	@FXML
	private TableColumn<RefundItem, Double> priceColumn;

	@FXML
	private Label refundSummaryLabel;
	
    private Stage stage;

    private ObservableList<RefundItem> itemsList = FXCollections.observableArrayList();


    public void setStage(Stage stage) {
        this.stage = stage;
        loadRefundScreen();
    }

    private void loadRefundScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("refund_screen.fxml"));
            Parent root = loader.load();

            stage.setScene(new Scene(root, 1000, 800));
            stage.setTitle("Refund Management");

            initializeRefundPage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializeRefundPage() {
        refundTypeComboBox.setItems(FXCollections.observableArrayList("Ticket", "Product"));

        itemColumn.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        itemsList.addAll(
            new RefundItem("Ticket: Hall_A, Seat 1", 15.00),
            new RefundItem("Popcorn", 5.00),
            new RefundItem("Coke", 3.50)
        );

        refundItemsTable.setItems(itemsList);
    }

    @FXML
    private void processRefund() {
        String refundType = refundTypeComboBox.getValue();
        String customerName = customerNameField.getText();
        RefundItem selectedItem = refundItemsTable.getSelectionModel().getSelectedItem();

        if (refundType == null || customerName.isEmpty() || selectedItem == null) {
            showAlert(Alert.AlertType.WARNING, "Refund Failed", "Please fill all fields and select an item.");
            return;
        }

        itemsList.remove(selectedItem);
        refundSummaryLabel.setText("Refund processed for: " + selectedItem.getItemName());

        showAlert(Alert.AlertType.INFORMATION, "Refund Successful", "Refund processed successfully!");
    }

    @FXML
    private void goBack() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("manager_screen.fxml"));
            Parent root = loader.load();

            ManagerController managerController = loader.getController();
            managerController.setStage(stage);

            stage.setScene(new Scene(root, 1000, 800));
            stage.setTitle("Manager Dashboard");
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
