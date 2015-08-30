package pl.mocek.tracker.data;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Created by Michał on 2015-08-11.
 */
public class ApplicationCollection implements Serializable {
    private static final long serialVersionUID = 421L;
    private ObservableList<Application> applicationList;


    private LocalDate localDate;

    public ObservableList<Application> getApplicationList() {
        return applicationList;
    }

    public ApplicationCollection() {
        applicationList = FXCollections.observableArrayList();
        localDate = LocalDate.now();
    }

    public void checkAplication(Application application){
        int index = applicationList.indexOf(application);
        if(index >=0) incrementTimer(index);
        else applicationList.add(application);
    }


    private void incrementTimer(int index) {
        Application changedApplication = applicationList.get(index);
        changedApplication.incrementTimeOn();
        refreshTable(index, changedApplication);
    }

    private void refreshTable(int index, Application changedApplication){
        if (Platform.isFxApplicationThread()) {
            Platform.runLater(() -> {
                applicationList.set(index, null);
                applicationList.set(index, changedApplication);
            });
        }
        else{
            applicationList.set(index, changedApplication);
        }
    }

    public void clear(){
        applicationList.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicationCollection)) return false;

        ApplicationCollection that = (ApplicationCollection) o;

        if (applicationList != null ? !applicationList.equals(that.applicationList) : that.applicationList != null)
            return false;
        if (localDate != null ? !localDate.equals(that.localDate) : that.localDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = applicationList != null ? applicationList.hashCode() : 0;
        result = 31 * result + (localDate != null ? localDate.hashCode() : 0);
        return result;
    }
}
