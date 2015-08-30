package pl.mocek.tracker.utils;

import pl.mocek.tracker.data.Application;
import pl.mocek.tracker.data.ApplicationCollection;

/**
 * Created by Michał on 2015-08-11.
 */
public class Tracker implements Runnable{
    ApplicationCollection applicationCollection;

    public Tracker(){
        applicationCollection = new ApplicationCollection();
    }

    public ApplicationCollection getApplicationCollection() {
        return applicationCollection;
    }

    @Override
    public void run() {
        if(OsUtils.isWindows()) {
            while(true){
                try {
                    applicationCollection.checkAplication(new Application(WindowsDetector.getProcessName()));
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (Exception E) {
                    E.printStackTrace();
                }
            }
        }
    }


}
