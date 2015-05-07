package JavaServer.responses;

import JavaServer.helpers.RangeFinder;
import org.junit.Test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FileAdminTest {
    private FileAdmin fileAdmin;
    private File path;
    private DataOutputStream dataOutputStream;
    private RangeFinder rangeFinder;

    private void mockDataStream() throws UnsupportedEncodingException {
        ByteArrayOutputStream mockInputStream = new ByteArrayOutputStream();

        dataOutputStream = new DataOutputStream(mockInputStream);
    }

    private String readFromFile(File path) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String fileLines = "";

        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            fileLines += line;
        }
        bufferedReader.close();
        return fileLines;
    }

    private Map<String, String> createRequestHeaders() {
        Map<String, String> params = new HashMap<>();

        params.put("Range", "bytes=0-4");
        return params;
    }

    @Test
    public void returnsTrueIfFileExistsInTheSpecifiedDirectory() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/image.gif");
        fileAdmin = new FileAdmin(path, dataOutputStream);

        assertTrue(fileAdmin.doesFileExist());
    }

    @Test
    public void returnsFalseIfFileDoesNotExistInTheSpecifiedDirectory() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/best_doge.gif");
        fileAdmin = new FileAdmin(path, dataOutputStream);

        assertFalse(fileAdmin.doesFileExist());
    }

    @Test
    public void returnsHTMLinksForFilesInDirectory() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/");
        fileAdmin = new FileAdmin(path, dataOutputStream);

        assertEquals("<a href='/file1'>file1</a></br>\r\n" +
                "<a href='/file2'>file2</a></br>\r\n" +
                "<a href='/image.gif'>image.gif</a></br>\r\n" +
                "<a href='/image.jpeg'>image.jpeg</a></br>\r\n" +
                "<a href='/image.png'>image.png</a></br>\r\n" +
                "<a href='/partial_content.txt'>partial_content.txt</a></br>\r\n" +
                "<a href='/patch-content.txt'>patch-content.txt</a></br>\r\n" +
                "<a href='/text-file.txt'>text-file.txt</a></br>\r\n", fileAdmin.getDirectoryLinks());
    }

    @Test
    public void returnsStringArrayWithAllFilesInDirectory() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/text-file.txt");
        fileAdmin = new FileAdmin(path, dataOutputStream);

        int lastIndex = fileAdmin.getDirectoryFiles().size() - 1;

        assertEquals("file1", fileAdmin.getDirectoryFiles().get(0));
        assertEquals("text-file.txt", fileAdmin.getDirectoryFiles().get(lastIndex));
        assertTrue(fileAdmin.getDirectoryFiles().contains("file1"));
    }

    @Test
    public void convertsFilesIntoPaths() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/image.gif");
        fileAdmin = new FileAdmin(path, dataOutputStream);
        fileAdmin.convertFilesToPaths();

        int lastIndex = fileAdmin.convertFilesToPaths().size() - 1;

        assertEquals("/file1", fileAdmin.convertFilesToPaths().get(0));
        assertEquals("/text-file.txt", fileAdmin.convertFilesToPaths().get(lastIndex));
        assertTrue(fileAdmin.convertFilesToPaths().contains("/file1"));
    }

    @Test
    public void patchesFileWithNewData() throws IOException {
        path = new File("/Users/test/code/JavaServer/public/patch-content.txt");
        fileAdmin = new FileAdmin(path, dataOutputStream);
        fileAdmin.patchFileWithNewData("patched-content");

        assertEquals("patched-content", readFromFile(path));

        fileAdmin.patchFileWithNewData("default content");
    }

    @Test
    public void writesPartialContentToFileDependingOnRange() throws IOException {
        path = new File("/Users/test/code/JavaServer/public/patch-content.txt");
        fileAdmin = new FileAdmin(path, dataOutputStream);
        rangeFinder = new RangeFinder(createRequestHeaders());
        mockDataStream();
        fileAdmin = new FileAdmin(path, dataOutputStream);
        fileAdmin.getPartialFileContents(rangeFinder);

//        assertEquals("hello", dataOutputStream..trim());
    }
}
