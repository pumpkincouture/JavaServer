package JavaServer.ResponseManagers.MethodManagers;

import JavaServer.RequestManagers.Request;
import JavaServer.RequestManagers.RequestParser;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class PostManagerTest {

    private Request request;
    private RequestParser requestParser;
    private RequestManager requestHandler;

    @Test
    public void returns200ResponseIfPathIsRecognized() throws FileNotFoundException {
        requestParser = new RequestParser("POST /form HTTP/1.1");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        requestHandler= new PostManager();

        assertEquals("HTTP/1.1 200 OK", requestHandler.manage(request));
    }

    @Test
    public void returns404ResponseIfPathIsNotRecognized() throws FileNotFoundException {
        requestParser = new RequestParser("POST / HTTP/1.1");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        requestHandler= new PostManager();

        assertEquals("HTTP/1.1 404 Not Found", requestHandler.manage(request));
    }

    @Test
    public void returns405ResponseIfMethodNotAllowed() throws FileNotFoundException {
        requestParser = new RequestParser("POST /text-file.txt HTTP/1.1");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        requestHandler= new PostManager();

        assertEquals("HTTP/1.1 405 Method Not Allowed", requestHandler.manage(request));
    }
}
