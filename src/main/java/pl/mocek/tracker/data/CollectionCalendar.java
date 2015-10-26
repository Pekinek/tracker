package pl.mocek.tracker.data;

import pl.mocek.tracker.utils.FileManagement;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class CollectionCalendar {
    private Map<LocalDate, ApplicationCollection> applicationCollectionMap;

    public CollectionCalendar(){
        applicationCollectionMap = new HashMap<>();
    }

    private void add(LocalDate date, ApplicationCollection applicationCollection) {
        applicationCollectionMap.put(date, applicationCollection);
    }

    public ApplicationCollection get() {
        return get(LocalDate.now());
    }

    public ApplicationCollection get(LocalDate date) {
        if (applicationCollectionMap.get(date) == null) {
            ApplicationCollection appCollection = FileManagement.loadFile(date);
            add(date, appCollection);
        }
        return applicationCollectionMap.get(date);
    }

}
