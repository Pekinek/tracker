package pl.mocek.tracker;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        final String appName = "Tracker v1.1";
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainPane.fxml"));
            primaryStage.getIcons().add(new Image("/images/icon.png"));
            primaryStage.setTitle(appName);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

