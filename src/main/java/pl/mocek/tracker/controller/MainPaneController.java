package pl.mocek.tracker.controller;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import pl.mocek.tracker.data.Application;
import pl.mocek.tracker.utils.Tracker;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class MainPaneController implements Initializable{
    public static final String NAME_COLUMN = "Proces";
    public static final String DURATION_COLUMN = "Czas";
    private Tracker tracker;

    @FXML
    private TableView<Application> contentTable;

    @FXML
    private DatePicker datePicker;

    @FXML
    private MenuItem closeButton;

    @FXML
    private MenuItem aboutButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tracker = new Tracker();
        configureMenuButtons();
        configureDatePicker();
        configureTable();

        Thread thread = new Thread(tracker);
        thread.setDaemon(true);
        thread.start();

    }

    private void configureMenuButtons() {
        closeButton.setOnAction(x -> Platform.exit());

        aboutButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                Parent parent = null;
                try {
                    parent = FXMLLoader.load(getClass().getResource("/fxml/AboutPane.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene = new Scene(parent);
                Stage stage = new Stage();
                stage.setTitle("Tracker v1.0 - about");
                stage.setScene(scene);
                stage.show();
            }
        });
    }


    private void configureTable() {
        contentTable.getColumns().clear();

        TableColumn<Application, String> nameColumn = new TableColumn<>(NAME_COLUMN);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Application, String> durationColumn = new TableColumn<>(DURATION_COLUMN);
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("timeOnString"));

        contentTable.getColumns().add(nameColumn);
        contentTable.getColumns().add(durationColumn);

        contentTable.setItems(tracker.getCollectionCalendar().get(LocalDate.now()).getApplicationList());
    }

    private void configureDatePicker() {
        datePicker.setValue(LocalDate.now());

        datePicker.setOnAction(event -> contentTable.setItems(tracker.getCollectionCalendar().get(datePicker.getValue()).getApplicationList()));
    }

}
