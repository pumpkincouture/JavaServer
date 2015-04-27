package JavaServer.responses;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FileManagerTest {
    private FileManager fileManager;
    private File path;

    private DataOutputStream mockDataStream() throws UnsupportedEncodingException {
        ByteArrayOutputStream mockInputStream = new ByteArrayOutputStream();

        DataOutputStream out = new DataOutputStream(mockInputStream);
        return out;
    }

    @Test
    public void returnsTrueIfFileExistsInTheSpecifiedDirectory() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/image.gif");
        fileManager = new FileManager(path, mockDataStream());

        assertTrue(fileManager.doesFileExist());
    }

    @Test
    public void returnsFalseIfFileDoesNotExistInTheSpecifiedDirectory() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/best_doge.gif");
        fileManager = new FileManager(path, mockDataStream());

        assertFalse(fileManager.doesFileExist());
    }

    @Test
    public void returnsHTMLinksForFilesInDirectory() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/");
        fileManager = new FileManager(path, mockDataStream());

        assertEquals("<a href='/file1'>file1</a></br>\r\n" +
                     "<a href='/file2'>file2</a></br>\r\n" +
                     "<a href='/image.gif'>image.gif</a></br>\r\n" +
                     "<a href='/image.jpeg'>image.jpeg</a></br>\r\n" +
                     "<a href='/image.png'>image.png</a></br>\r\n" +
                     "<a href='/partial_content.txt'>partial_content.txt</a></br>\r\n" +
                     "<a href='/patch-content.txt'>patch-content.txt</a></br>\r\n" +
                     "<a href='/text-file.txt'>text-file.txt</a></br>\r\n",
                     fileManager.getDirectoryLinks());
    }

    @Test
    public void returnsStringArrayWithAllFilesInDirectory() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/text-file.txt");
        fileManager = new FileManager(path, mockDataStream());

        int lastIndex = fileManager.getDirectoryFiles().size() - 1;

        assertEquals("file1", fileManager.getDirectoryFiles().get(0));
        assertEquals("text-file.txt", fileManager.getDirectoryFiles().get(lastIndex));
        assertTrue(fileManager.getDirectoryFiles().contains("file1"));
    }

    @Test
    public void convertsFilesIntoPaths() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/image.gif");
        fileManager = new FileManager(path, mockDataStream());
        fileManager.convertFilesToPaths();

        int lastIndex = fileManager.convertFilesToPaths().size() - 1;

        assertEquals("/file1", fileManager.convertFilesToPaths().get(0));
        assertEquals("/text-file.txt", fileManager.convertFilesToPaths().get(lastIndex));
        assertTrue(fileManager.convertFilesToPaths().contains("/file1"));
    }

    @Test
    public void returnsTrueIfFileIsGIF() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/image.gif");
        fileManager = new FileManager(path, mockDataStream());

        assertTrue(fileManager.isFileImage());
    }

    @Test
    public void returnsTrueIfFileIsPNG() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/image.png");
        fileManager = new FileManager(path, mockDataStream());

        assertTrue(fileManager.isFileImage());
    }

    @Test
    public void returnsTrueIfFileIsJPEG() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/image.jpeg");
        fileManager = new FileManager(path, mockDataStream());

        assertTrue(fileManager.isFileImage());
    }

    @Test
    public void returnsTrueIfFileIsJPG() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/image.jpg");
        fileManager = new FileManager(path, mockDataStream());

        assertTrue(fileManager.isFileImage());
    }

    @Test
    public void returnsFalseIfFileIsNotImage() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/image.txt");
        fileManager = new FileManager(path, mockDataStream());

        assertFalse(fileManager.isFileImage());
    }

    @Test
    public void returnsFalseIfThereIsNoSpecifiedFilePath() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/");
        fileManager = new FileManager(path, mockDataStream());

        assertFalse(fileManager.isFileImage());
    }

    @Test
    public void returnsFalseIfPathIsNotAFileNorIsItAnImage() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/method_options");
        fileManager = new FileManager(path, mockDataStream());

        assertFalse(fileManager.isFileImage());
        assertFalse(fileManager.doesFileExist());
    }
}
