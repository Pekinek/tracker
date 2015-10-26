package pl.mocek.tracker.data;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ApplicationCollection {
    private ObservableList<Application> applicationList;

    public ApplicationCollection() {
        applicationList = FXCollections.observableArrayList();
    }

    public ApplicationCollection(ObservableList<Application> list) {
        if (list == null) {
            applicationList = FXCollections.observableArrayList();
        } else {
            applicationList = list;
        }
    }

    public ObservableList<Application> getApplicationList() {
        return applicationList;
    }

    public void checkAplication(Application application) {
        int index = applicationList.indexOf(application);
        if (index >= 0) {
            incrementTimer(index);
        } else {
            applicationList.add(application);
        }
    }

    private void incrementTimer(int index) {
        Application changedApplication = applicationList.get(index);
        changedApplication.incrementTimeOn();
        refreshTable(index, changedApplication);
    }


    private void refreshTable(int index, Application changedApplication) {
        try {
            Platform.runLater(() -> {
                applicationList.set(index, null);
                applicationList.set(index, changedApplication);
            });
        } catch (IllegalStateException e) {
            applicationList.set(index, null);
            applicationList.set(index, changedApplication);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ApplicationCollection)) return false;

        ApplicationCollection that = (ApplicationCollection) o;

        if (applicationList != null ? !applicationList.equals(that.applicationList) : that.applicationList != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return applicationList != null ? applicationList.hashCode() : 0;
    }
}
