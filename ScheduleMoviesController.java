package application_adminscreens;

import java.io.IOException;

import application.LoginController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ScheduleMoviesController {

    @FXML
    private ComboBox<String> movieComboBox;

    @FXML
    private ComboBox<String> hallComboBox;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> sessionComboBox;

    @FXML
    private TableView<Schedule> scheduleTable;

    @FXML
    private TableColumn<Schedule, String> movieColumn;

    @FXML
    private TableColumn<Schedule, String> hallColumn;

    @FXML
    private TableColumn<Schedule, String> dateColumn;

    @FXML
    private TableColumn<Schedule, String> sessionColumn;
    
    @FXML
    private Button logoutButton;

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
                    loginController.setStage(stage); // Pass the stage to LoginController

                    stage.setScene(new Scene(root, 1000, 800));
                    stage.setTitle("Login");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private Stage stage;

    private final ObservableList<Schedule> schedules = FXCollections.observableArrayList();

    public void setStage(Stage stage) {
        this.stage = stage;
        initializeComponents();
    }

    private void initializeComponents() {
        movieComboBox.getItems().addAll("Inception", "Titanic", "Avatar", "The Matrix");

        hallComboBox.getItems().addAll("Hall_A", "Hall_B");

        sessionComboBox.getItems().addAll("Morning", "Afternoon", "Evening");

        movieColumn.setCellValueFactory(data -> data.getValue().movieProperty());
        hallColumn.setCellValueFactory(data -> data.getValue().hallProperty());
        dateColumn.setCellValueFactory(data -> data.getValue().dateProperty());
        sessionColumn.setCellValueFactory(data -> data.getValue().sessionProperty());

        scheduleTable.setItems(schedules);
    }

    @FXML
    private void addSchedule() {
        String movie = movieComboBox.getValue();
        String hall = hallComboBox.getValue();
        String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : null;
        String session = sessionComboBox.getValue();

        if (movie == null || hall == null || date == null || session == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "All fields are mandatory!", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        schedules.add(new Schedule(movie, hall, date, session));
        Alert success = new Alert(Alert.AlertType.INFORMATION, "Movie scheduled successfully!", ButtonType.OK);
        success.showAndWait();

        movieComboBox.setValue(null);
        hallComboBox.setValue(null);
        datePicker.setValue(null);
        sessionComboBox.setValue(null);
    }

    @FXML
    private void goBack() {
        stage.setScene(new AdminScreen(stage).getAdminScene());
    }
}
