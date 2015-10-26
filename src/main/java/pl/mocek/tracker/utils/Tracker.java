package pl.mocek.tracker.utils;

import pl.mocek.tracker.data.Application;
import pl.mocek.tracker.data.CollectionCalendar;

import java.time.LocalDate;

public class Tracker implements Runnable {
    private int i;
    private CollectionCalendar collectionCalendar;

    public Tracker() {
        collectionCalendar = new CollectionCalendar();
    }

    public CollectionCalendar getCollectionCalendar() {
        return collectionCalendar;
    }

    @Override
    public void run() {
        if (OsUtils.isWindows()) {
            while (true) {
                try {
                    collectionCalendar.get().checkAplication(new Application(WindowsDetector.getProcessName()));
                    saveToFile();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void saveToFile() {
        if (i == 30) {
            FileManagement.saveFile(LocalDate.now(), collectionCalendar.get(LocalDate.now()));
            i = 0;
        }
        i++;
    }


}
