package pl.mocek.tracker.data;

import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.*;

public class ApplicationTest {

    @Test
    public void testGetTimeOnString() throws Exception {
        Application app;
        app = new Application("idea", Duration.ofSeconds(5400));
        assertEquals("1:30:00", app.getTimeOnString());

        app = new Application("idea", Duration.ofSeconds(3660));
        assertEquals("1:01:00", app.getTimeOnString());

        app = new Application("idea", Duration.ofSeconds(36000));
        assertEquals("10:00:00", app.getTimeOnString());
    }
}