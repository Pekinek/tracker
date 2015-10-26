package pl.mocek.tracker.utils;

import org.junit.Test;
import pl.mocek.tracker.data.Application;
import pl.mocek.tracker.data.ApplicationCollection;

import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

public class FileManagementTest {

    @Test
    public void testSaveLoadFile() throws Exception {
        ApplicationCollection applicationCollection = new ApplicationCollection();
        applicationCollection.checkAplication(new Application("idea.exe"));
        applicationCollection.checkAplication(new Application("idea"));
        FileManagement.saveFile(LocalDate.of(2015, 10, 15), applicationCollection);

        ApplicationCollection applicationCollection1 = FileManagement.loadFile(LocalDate.of(2015, 10, 15));
        assertTrue(applicationCollection1.getApplicationList().get(0).getName().equals("idea"));
    }


}