package JavaServer.responses;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FileManagerTest {
    private FileManager fileManager;
    private File path;

    @Test
    public void returnsTrueIfFileExistsInTheSpecifiedDirectory() {
        path = new File("/Users/test/code/JavaServer/public/image.gif");
        fileManager = new FileManager(path);

        assertTrue(fileManager.doesFileExist());
    }

    @Test
    public void returnsFalseIfFileDoesNotExistInTheSpecifiedDirectory() {
        path = new File("/Users/test/code/JavaServer/public/best_doge.gif");
        fileManager = new FileManager(path);

        assertFalse(fileManager.doesFileExist());
    }

    @Test
    public void returnsDirectoryListingForFile() {
        path = new File("/Users/test/code/JavaServer/public/best_doge.gif");
        fileManager = new FileManager(path);

        assertEquals("/Users/test/code/JavaServer/public/best_doge.gif", fileManager.getDirectoryListing());
    }

    @Test
    public void getsDirectoryInfoForPath() {
        path = new File("/Users/test/code/JavaServer/public/");
        fileManager = new FileManager(path);

        assertEquals("/Users/test/code/JavaServer", fileManager.getMainDirectory());


    }


}
