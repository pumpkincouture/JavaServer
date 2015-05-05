package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.FileManager;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class GetResponseTest {
    private Request request;
    private RequestParser requestParser;
    private Response requestHandler;
    private FileManager fileManager;
    private File path;

    private DataOutputStream mockDataStream() throws UnsupportedEncodingException {
        ByteArrayOutputStream mockInputStream = new ByteArrayOutputStream();

        DataOutputStream out = new DataOutputStream(mockInputStream);
        return out;
    }

    @Test
    public void returns200ResponseIfRequestValid() throws UnsupportedEncodingException {
        requestParser = new RequestParser("GET / HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/image.gif");
        fileManager = new FileManager(path, mockDataStream());

        requestHandler = new GetResponse(fileManager, request);

        assertEquals("HTTP/1.1 200 OK", requestHandler.getCorrectStatus());
    }

    @Test
    public void returnsBlankHeaderIfPathIsNotForMethodOptions() throws UnsupportedEncodingException {
        requestParser = new RequestParser("GET / HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/image.gif");
        fileManager = new FileManager(path, mockDataStream());

        requestHandler = new GetResponse(fileManager, request);

        assertEquals("", requestHandler.getCorrectHeaders());
    }
}
