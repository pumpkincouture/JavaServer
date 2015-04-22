package JavaServer.responses;

import org.junit.Test;

import java.io.File;
import java.util.List;

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

    @Test
    public void returnsHTMLinksForFilesInDirectory() {
        path = new File("/Users/test/code/JavaServer/public/");
        fileManager = new FileManager(path);

        assertEquals("<a href='/file1'>file1</a>\r\n" +
                     "<a href='/file2'>file2</a>\r\n" +
                     "<a href='/image.gif'>image.gif</a>\r\n" +
                     "<a href='/image.jpeg'>image.jpeg</a>\r\n" +
                     "<a href='/image.png'>image.png</a>\r\n" +
                     "<a href='/partial_content.txt'>partial_content.txt</a>\r\n" +
                     "<a href='/patch-content.txt'>patch-content.txt</a>\r\n" +
                     "<a href='/text-file.txt'>text-file.txt</a>\r\n",
                     fileManager.getDirectoryLinks());
    }

    @Test
    public void returnsStringArrayWithAllFilesInDirectory() {
        path = new File("/Users/test/code/JavaServer/public/");
        fileManager = new FileManager(path);

        int lastIndex = fileManager.getDirectoryFiles().size() - 1;

        assertEquals("file1", fileManager.getDirectoryFiles().get(0));
        assertEquals("text-file.txt", fileManager.getDirectoryFiles().get(lastIndex));
    }
}
