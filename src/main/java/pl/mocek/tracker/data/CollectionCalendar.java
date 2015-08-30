package pl.mocek.tracker.data;

import java.io.Serializable;

/**
 * Created by Michał on 2015-08-13.
 */
public class CollectionCalendar implements Serializable{
    ApplicationCollection applicationCollection;
    private static final long serialVersionUID = 420L;

    public CollectionCalendar(){
        applicationCollection = new ApplicationCollection();
    }
}
