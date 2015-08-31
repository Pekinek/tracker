package pl.mocek.tracker.controller;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import pl.mocek.tracker.data.Application;
import pl.mocek.tracker.utils.Tracker;

import java.net.URL;
import java.util.ResourceBundle;

public class MainPaneController implements Initializable{
    public static final String NAME_COLUMN = "Proces";
    public static final String DURATION_COLUMN = "Czas";
    private Tracker tracker;

    @FXML
    private TableView<Application> contentTable;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tracker = new Tracker();
        configureTable();
        Thread thread = new Thread(tracker);
        thread.setDaemon(true);
        thread.start();
    }


    private void configureTable() {
        contentTable.getColumns().clear();

        TableColumn<Application, String> nameColumn = new TableColumn<>(NAME_COLUMN);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Application, String> durationColumn = new TableColumn<>(DURATION_COLUMN);
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("timeOnString"));

        contentTable.getColumns().add(nameColumn);
        contentTable.getColumns().add(durationColumn);

        contentTable.setItems(tracker.getApplicationCollection().getApplicationList());
    }
}
