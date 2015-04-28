package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.DataManager;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class PutResponseTest {
    private Request request;
    private RequestParser requestParser;
    private Response requestHandler;
    private DataManager dataManager;


    @Test
    public void returns200ResponseIfPathIsRecognized() throws FileNotFoundException {
        requestParser = new RequestParser("PUT /form HTTP/1.1");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        dataManager = new DataManager();
        requestHandler= new PutResponse(dataManager);

        assertEquals("HTTP/1.1 200 OK", requestHandler.getCorrectStatus());
    }

    @Test
    public void returns404ResponseIfPathIsNotRecognized() throws FileNotFoundException {
        requestParser = new RequestParser("PUT / HTTP/1.1");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        dataManager = new DataManager();
        requestHandler= new PutResponse(dataManager);

        assertEquals("HTTP/1.1 404 Not Found", requestHandler.getCorrectStatus());
    }

    @Test
    public void returns405ResponseIfMethodNotAllowed() throws FileNotFoundException {
        requestParser = new RequestParser("PUT /file1 HTTP/1.1");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        dataManager = new DataManager();
        requestHandler= new PutResponse(dataManager);

        assertEquals("HTTP/1.1 405 Method Not Allowed", requestHandler.getCorrectStatus());
    }
}

