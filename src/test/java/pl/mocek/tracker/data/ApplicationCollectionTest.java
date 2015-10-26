package pl.mocek.tracker.data;

import org.junit.Test;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class ApplicationCollectionTest {

    @Test
    public void testCheckAplication() throws Exception {
        ApplicationCollection ac = new ApplicationCollection();

        ac.checkAplication(new Application("idea.exe"));
        ac.checkAplication(new Application("idea"));
        assertEquals(ac.getApplicationList().get(0).getTimeOn(), Duration.ofSeconds(1));

        ac.checkAplication(new Application("idea"));
        assertEquals(ac.getApplicationList().get(0).getTimeOn(), Duration.ofSeconds(2));

        ac.checkAplication(new Application("chrome.exe"));
        assertEquals(ac.getApplicationList().get(1).getTimeOn(), Duration.ofSeconds(0));
        assertEquals(ac.getApplicationList().get(0).getTimeOn(), Duration.ofSeconds(2));
    }

}