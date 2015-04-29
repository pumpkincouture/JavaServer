package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.FileManager;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class ContentResponseTest {
    private Request request;
    private RequestParser requestParser;
    private Response requestHandler;
    private File path;
    private FileManager fileManager;

    private DataOutputStream mockDataStream() throws UnsupportedEncodingException {
        ByteArrayOutputStream mockInputStream = new ByteArrayOutputStream();

        DataOutputStream out = new DataOutputStream(mockInputStream);
        return out;
    }

    @Test
    public void returns200Response() throws UnsupportedEncodingException {
        requestParser = new RequestParser("GET /image.gif HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/image.gif");
        fileManager = new FileManager(path, mockDataStream());

        requestHandler = new ContentResponse(fileManager);

        assertEquals("HTTP/1.1 200 OK", requestHandler.getCorrectStatus());
    }

    @Test
    public void callsFileManagerAndReturnsAnEmptyString() throws IOException {
        requestParser = new RequestParser("GET /image.gif HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/image.gif");
        fileManager = new FileManager(path, mockDataStream());

        requestHandler = new ContentResponse(fileManager);

        assertEquals("", requestHandler.getCorrectBody());
    }
}
