package pl.mocek.tracker.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class WindowsDetectorTest {

    @Test
    public void testGetWindowTitle() throws Exception {

    }

    @Test
    public void testGetProcessName() throws Exception {
        assertTrue(WindowsDetector.getProcessName().equals("idea.exe"));
    }
}