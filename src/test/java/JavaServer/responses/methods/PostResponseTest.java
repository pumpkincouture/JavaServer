package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.DataManager;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class PostResponseTest {

    private Request request;
    private RequestParser requestParser;
    private Response requestHandler;
    private DataManager dataManager;

    @Test
    public void returns200ResponseIfPathIsRecognized() throws FileNotFoundException {
        requestParser = new RequestParser("POST /form HTTP/1.1");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        dataManager = new DataManager();
        requestHandler= new PostResponse(dataManager);

        assertEquals("HTTP/1.1 200 OK", requestHandler.getCorrectStatus());
    }

    @Test
    public void returns404ResponseIfPathIsNotRecognized() throws FileNotFoundException {
        requestParser = new RequestParser("POST / HTTP/1.1");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        dataManager = new DataManager();
        requestHandler= new PostResponse(dataManager);

        assertEquals("HTTP/1.1 404 Not Found", requestHandler.getCorrectStatus());
    }

    @Test
    public void returns405ResponseIfMethodNotAllowed() throws FileNotFoundException {
        requestParser = new RequestParser("POST /text-file.txt HTTP/1.1");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        dataManager = new DataManager();
        requestHandler= new PostResponse(dataManager);

        assertEquals("HTTP/1.1 405 Method Not Allowed", requestHandler.getCorrectStatus());
    }
}
