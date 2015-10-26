package pl.mocek.tracker.data;

import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class ApplicationTest {

    @Test
    public void testGetTimeOnString() throws Exception {
        Application app = new Application("idea", Duration.ofSeconds(5400));
        assertEquals("01:30:00", app.getTimeOnString());

        app = new Application("idea", Duration.ofSeconds(3660));
        assertEquals("01:01:00", app.getTimeOnString());

        app = new Application("idea", Duration.ofSeconds(36000));
        assertEquals("10:00:00", app.getTimeOnString());
    }

    @Test
    public void testConstructor() throws Exception {
        Application app1 = new Application("EXPLORER.EXE");
        assertEquals(app1.getName(), "explorer");
        Application app2 = new Application("Explorer.ExE");
        assertEquals(app2.getName(), "explorer");
    }
}