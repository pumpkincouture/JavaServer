package JavaServer.responses;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FileWriterTest {
    private FileWriter fileWriter;
    private File path;

    private DataOutputStream mockDataStream() throws UnsupportedEncodingException {
        ByteArrayOutputStream mockInputStream = new ByteArrayOutputStream();

        DataOutputStream out = new DataOutputStream(mockInputStream);
        return out;
    }

    @Test
    public void returnsTrueIfFileExistsInTheSpecifiedDirectory() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/image.gif");
        fileWriter = new FileWriter(path, mockDataStream());

        assertTrue(fileWriter.doesFileExist());
    }

    @Test
    public void returnsFalseIfFileDoesNotExistInTheSpecifiedDirectory() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/best_doge.gif");
        fileWriter = new FileWriter(path, mockDataStream());

        assertFalse(fileWriter.doesFileExist());
    }

    @Test
    public void returnsHTMLinksForFilesInDirectory() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/");
        fileWriter = new FileWriter(path, mockDataStream());

        assertEquals("<a href='/file1'>file1</a></br>\r\n" +
                     "<a href='/file2'>file2</a></br>\r\n" +
                     "<a href='/image.gif'>image.gif</a></br>\r\n" +
                     "<a href='/image.jpeg'>image.jpeg</a></br>\r\n" +
                     "<a href='/image.png'>image.png</a></br>\r\n" +
                     "<a href='/partial_content.txt'>partial_content.txt</a></br>\r\n" +
                     "<a href='/patch-content.txt'>patch-content.txt</a></br>\r\n" +
                     "<a href='/text-file.txt'>text-file.txt</a></br>\r\n",
                     fileWriter.getDirectoryLinks());
    }

    @Test
    public void returnsStringArrayWithAllFilesInDirectory() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/text-file.txt");
        fileWriter = new FileWriter(path, mockDataStream());

        int lastIndex = fileWriter.getDirectoryFiles().size() - 1;

        assertEquals("file1", fileWriter.getDirectoryFiles().get(0));
        assertEquals("text-file.txt", fileWriter.getDirectoryFiles().get(lastIndex));
        assertTrue(fileWriter.getDirectoryFiles().contains("file1"));
    }

    @Test
    public void convertsFilesIntoPaths() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/image.gif");
        fileWriter = new FileWriter(path, mockDataStream());
        fileWriter.convertFilesToPaths();

        int lastIndex = fileWriter.convertFilesToPaths().size() - 1;

        assertEquals("/file1", fileWriter.convertFilesToPaths().get(0));
        assertEquals("/text-file.txt", fileWriter.convertFilesToPaths().get(lastIndex));
        assertTrue(fileWriter.convertFilesToPaths().contains("/file1"));
    }
}
