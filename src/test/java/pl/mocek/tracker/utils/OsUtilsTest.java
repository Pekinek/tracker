package pl.mocek.tracker.utils;

import org.junit.Test;
import pl.mocek.tracker.utils.OsUtils;

import static org.junit.Assert.*;

public class OsUtilsTest {

    @Test
    public void testGetOsName() throws Exception {
        assertTrue(OsUtils.getOsName().startsWith("Windows"));
        System.out.println(OsUtils.getOsName());
    }

    @Test
    public void testIsWindows() throws Exception {
        assertTrue(OsUtils.isWindows());
    }

    @Test
    public void testIsLinux() throws Exception {
        assertFalse(OsUtils.isLinux());
    }
}