package JavaServer.helpers;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class HTMLBuilderTest {
    private HTMLBuilder htmlBuilder;
    private File path;

    @Test
    public void returnFilePathDirectoryAsHTMLString() {
        path = new File("/Users/test/code/JavaServer/public/");
        htmlBuilder = new HTMLBuilder(path);

        assertEquals("<a href='/file1'>file1</a></br>\r\n" +
                     "<a href='/file2'>file2</a></br>\r\n" +
                     "<a href='/image.gif'>image.gif</a></br>\r\n" +
                     "<a href='/image.jpeg'>image.jpeg</a></br>\r\n" +
                     "<a href='/image.png'>image.png</a></br>\r\n" +
                     "<a href='/partial_content.txt'>partial_content.txt</a></br>\r\n" +
                     "<a href='/patch-content.txt'>patch-content.txt</a></br>\r\n" +
                     "<a href='/text-file.txt'>text-file.txt</a></br>\r\n", htmlBuilder.getDirectoryLinks());
    }
}
