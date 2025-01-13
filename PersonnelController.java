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

public class PersonnelController {

    @FXML
    private TableView<Personnel> personnelTable;

    @FXML
    private TableColumn<Personnel, String> firstNameColumn;

    @FXML
    private TableColumn<Personnel, String> lastNameColumn;

    @FXML
    private TableColumn<Personnel, String> usernameColumn;

    @FXML
    private TableColumn<Personnel, String> roleColumn;

    private Stage stage;
    private ObservableList<Personnel> personnelData;

    public void setStage(Stage stage) {
        this.stage = stage;

        personnelData = FXCollections.observableArrayList(
                new Personnel("John", "Doe", "jdoe", "Cashier"),
                new Personnel("Jane", "Smith", "jsmith", "Admin"),
                new Personnel("Emily", "Johnson", "ejohnson", "Cashier")
        );

        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        roleColumn.setCellValueFactory(new PropertyValueFactory<>("role"));

        personnelTable.setItems(personnelData);
    }

    @FXML
    private void addPersonnel() {
        Alert addAlert = new Alert(Alert.AlertType.INFORMATION, "Add Personnel - Coming Soon!", ButtonType.OK);
        addAlert.showAndWait();
    }

    @FXML
    private void editPersonnel() {
        Personnel selectedPersonnel = personnelTable.getSelectionModel().getSelectedItem();
        if (selectedPersonnel != null) {
            Alert editAlert = new Alert(Alert.AlertType.INFORMATION, "Edit Personnel - Coming Soon!", ButtonType.OK);
            editAlert.showAndWait();
        } else {
            Alert warning = new Alert(Alert.AlertType.WARNING, "Please select a personnel to edit.", ButtonType.OK);
            warning.showAndWait();
        }
    }

    @FXML
    private void firePersonnel() {
        Personnel selectedPersonnel = personnelTable.getSelectionModel().getSelectedItem();
        if (selectedPersonnel != null) {
            personnelData.remove(selectedPersonnel);
            Alert success = new Alert(Alert.AlertType.INFORMATION, "Personnel fired successfully.", ButtonType.OK);
            success.showAndWait();
        } else {
            Alert warning = new Alert(Alert.AlertType.WARNING, "Please select a personnel to fire.", ButtonType.OK);
            warning.showAndWait();
        }
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
        } catch (Exception e) {
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
}
