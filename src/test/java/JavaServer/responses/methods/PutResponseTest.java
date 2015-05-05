package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.FileManager;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class PutResponseTest {
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
    public void returns200ResponseIfPathIsRecognized() throws FileNotFoundException, UnsupportedEncodingException {
        requestParser = new RequestParser("PUT /form HTTP/1.1");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/form");
        fileManager = new FileManager(path, mockDataStream());
        requestHandler= new PutResponse(fileManager, request.getData(), request.getPath());

        assertEquals("HTTP/1.1 200 OK", requestHandler.getCorrectStatus());
    }

    @Test
    public void returns404ResponseIfPathIsNotRecognized() throws FileNotFoundException, UnsupportedEncodingException {
        requestParser = new RequestParser("PUT / HTTP/1.1");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/");
        fileManager = new FileManager(path, mockDataStream());
        requestHandler= new PutResponse(fileManager, request.getData(), request.getPath());

        assertEquals("HTTP/1.1 404 Not Found", requestHandler.getCorrectStatus());
    }

    @Test
    public void returns405ResponseIfMethodNotAllowed() throws FileNotFoundException, UnsupportedEncodingException {
        requestParser = new RequestParser("PUT /file1 HTTP/1.1");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/file1");
        fileManager = new FileManager(path, mockDataStream());
        requestHandler= new PutResponse(fileManager, request.getData(), request.getPath());

        assertEquals("HTTP/1.1 405 Method Not Allowed", requestHandler.getCorrectStatus());
    }
}

