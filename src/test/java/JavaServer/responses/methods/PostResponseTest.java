package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.FileManager;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class PostResponseTest {

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
    public void returns200ResponseIfPathIsRecognized() throws FileNotFoundException, UnsupportedEncodingException {
        requestParser = new RequestParser("POST /form HTTP/1.1");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/form");
        fileManager = new FileManager(path, mockDataStream());
        requestHandler= new PostResponse(fileManager, request.getPath(), request.getData());

        assertEquals("HTTP/1.1 200 OK", requestHandler.getCorrectStatus());
    }

    @Test
    public void returns404ResponseIfPathIsNotRecognized() throws FileNotFoundException, UnsupportedEncodingException {
        requestParser = new RequestParser("POST / HTTP/1.1");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/form");
        fileManager = new FileManager(path, mockDataStream());
        requestHandler= new PostResponse(fileManager, request.getPath(), request.getData());

        assertEquals("HTTP/1.1 404 Not Found", requestHandler.getCorrectStatus());
    }

    @Test
    public void returns405ResponseIfMethodNotAllowed() throws FileNotFoundException, UnsupportedEncodingException {
        requestParser = new RequestParser("POST /text-file.txt HTTP/1.1");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/form");
        fileManager = new FileManager(path, mockDataStream());
        requestHandler= new PostResponse(fileManager, request.getPath(), request.getData());

        assertEquals("HTTP/1.1 405 Method Not Allowed", requestHandler.getCorrectStatus());
    }
}
