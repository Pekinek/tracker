package pl.mocek.tracker.data;

import java.io.Serializable;
import java.time.Duration;

/**
 * Created by Michał on 2015-08-07.
 */
public class Application implements Serializable {
    private static final long serialVersionUID = 422L;
    private String name;
    private Duration timeOn;

    public Application(String name){
        this.name = name.replaceAll(".exe", "");
        timeOn = Duration.ofSeconds(0);
    }

    public Application(String name, Duration timeOn){
        this(name);
        this.timeOn = timeOn;
    }

    public String getName(){
        return this.name;
    }

    public String getTimeOnString(){
        return durationToString(timeOn);
    }

    public Duration getTimeOn(){
        return timeOn;
    }

    private String durationToString(Duration duration){
        StringBuilder sb = new StringBuilder();
        sb.append(duration.toHours());
        sb.append(":");
        if((duration.toMinutes() - duration.toHours()*60) < 10) sb.append("0");
        sb.append((duration.toMinutes()) - duration.toHours()*60);
        sb.append(":");
        if(((duration.toMillis() / 1000) - (duration.toMinutes() * 60)) < 10) sb.append("0");
        sb.append(duration.toMillis()/1000  - duration.toMinutes()*60);
        return sb.toString();
    }

    public void incrementTimeOn(){
        timeOn = timeOn.plusSeconds(1);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Application)) return false;

        Application that = (Application) o;

        if (!name.equals(that.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
