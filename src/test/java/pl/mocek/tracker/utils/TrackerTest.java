package pl.mocek.tracker.utils;

import org.junit.Test;
import pl.mocek.tracker.data.Application;
import pl.mocek.tracker.data.ApplicationCollection;

import java.time.Duration;
import java.time.Instant;

import static org.junit.Assert.*;

public class TrackerTest {


    @Test
    public void testRun() throws Exception {
        Tracker tracker = new Tracker();
        Thread thread = new Thread(tracker);
        thread.setDaemon(true);
        thread.start();

        Thread.sleep(1000);
        assertTrue(tracker.getApplicationCollection().getApplicationList().contains(new Application("idea")));

        Duration d1 = tracker.getApplicationCollection().getApplicationList().get(0).getTimeOn();
        Duration d2 = Duration.ofMillis(d1.toMillis());

        Instant instant1 = Instant.now();
        Thread.sleep(10500);
        Instant instant2 = Instant.now();

        d1 = tracker.getApplicationCollection().getApplicationList().get(0).getTimeOn();
        d2 = d2.plusMillis(Duration.between(instant1, instant2).toMillis());

        System.out.println("Aplikacja: " + d1.toMillis() + ", Rzeczywistość: " + d2.toMillis());
        assertEquals(d1.toMillis(), d2.toMillis(), 1000);
    }
}