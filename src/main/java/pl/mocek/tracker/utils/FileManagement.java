package pl.mocek.tracker.utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pl.mocek.tracker.data.Application;
import pl.mocek.tracker.data.ApplicationCollection;

import java.io.*;
import java.time.Duration;
import java.time.LocalDate;


public class FileManagement {

    public static void saveFile(LocalDate date, ApplicationCollection applicationCollection) {
        createDir();
        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter("data/" + date + ".txt"))
        ) {
            for (Application app : applicationCollection.getApplicationList()) {
                writer.write(app.getTimeOnString());
                writer.write("\t");
                writer.write(app.getName());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void createDir() {
        new File("data").mkdir();
    }

    public static ApplicationCollection loadFile(LocalDate date) {
        ObservableList<Application> applicationList;
        applicationList = FXCollections.observableArrayList();
        try (
                BufferedReader reader = new BufferedReader(new FileReader("data/" + date + ".txt"))
        ) {
            String tmp;
            String[] tmpSplit;
            while ((tmp = reader.readLine()) != null) {
                tmpSplit = tmp.split("\t");
                if (tmpSplit.length == 2) {
                    applicationList.add(new Application(tmpSplit[1], stringToDuration(tmpSplit[0])));
                }
            }
            return new ApplicationCollection(applicationList);
        } catch (IOException e) {
            System.err.println("Nie odnaleziono pliku");
            return new ApplicationCollection(null);
        }
    }


    private static Duration stringToDuration(String duration) {
        String[] tmp = duration.split(":");

        int hours = Integer.parseInt(tmp[0]);
        int minutes = Integer.parseInt(tmp[1]);
        int seconds = Integer.parseInt(tmp[2]);

        return Duration.ofSeconds((3600 * hours) + (60 * minutes) + seconds);
    }
}


