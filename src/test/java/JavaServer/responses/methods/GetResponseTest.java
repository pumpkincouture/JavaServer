package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.FileManager;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class GetResponseTest {
    private Request request;
    private RequestParser requestParser;
    private Response requestHandler;
    private FileManager fileManager;
    private File path;

    @Test
    public void returns200ResponseIfRequestValid() {
        requestParser = new RequestParser("GET / HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/image.gif");
        fileManager = new FileManager(path);

        requestHandler = new GetResponse(fileManager);

        assertEquals("HTTP/1.1 200 OK", requestHandler.getCorrectStatus(request));
    }

    @Test
    public void returns404ResponseIfInvalidPath() {
        requestParser = new RequestParser("GET /whatever HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/image.gif");
        fileManager = new FileManager(path);

        requestHandler = new GetResponse(fileManager);

        assertEquals("HTTP/1.1 404 Not Found", requestHandler.getCorrectStatus(request));
    }

    @Test
    public void returns302IfPathIsRedirect() {
        requestParser = new RequestParser("GET /redirect HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/image.gif");
        fileManager = new FileManager(path);

        requestHandler = new GetResponse(fileManager);

        assertEquals("HTTP/1.1 302 Found", requestHandler.getCorrectStatus(request));
    }

    @Test
    public void returnLocationHeaderIfRequestIsForRedirect() {
        requestParser = new RequestParser("GET /redirect HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/image.gif");
        fileManager = new FileManager(path);

        requestHandler = new GetResponse(fileManager);

        assertEquals("Location: http://localhost:5000/", requestHandler.getCorrectHeaders(request));
    }

    @Test
    public void returnsBlankHeaderIfPathIsNotForMethodOptions() {
        requestParser = new RequestParser("GET / HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/image.gif");
        fileManager = new FileManager(path);

        requestHandler = new GetResponse(fileManager);

        assertEquals("", requestHandler.getCorrectHeaders(request));
    }
}