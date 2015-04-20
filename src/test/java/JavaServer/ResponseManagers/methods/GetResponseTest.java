package JavaServer.ResponseManagers.methods;

import JavaServer.RequestManagers.Request;
import JavaServer.RequestManagers.RequestParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetResponseTest {
    private Request request;
    private RequestParser requestParser;
    private Response requestHandler;

    @Test
    public void returns200ResponseIfRequestValid() {
        requestParser = new RequestParser("GET / HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());

        requestHandler = new GetResponse();

        assertEquals("HTTP/1.1 200 OK", requestHandler.manage(request));
    }

    @Test
    public void returns404ResponseIfInvalidPath() {
        requestParser = new RequestParser("GET /whatever HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());

        requestHandler = new GetResponse();

        assertEquals("HTTP/1.1 404 Not Found", requestHandler.manage(request));
    }

    @Test
    public void returns302IfPathIsRedirect() {
        requestParser = new RequestParser("GET /redirect HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());

        requestHandler = new GetResponse();

        assertEquals("HTTP/1.1 302 Found", requestHandler.manage(request));
    }

    @Test
    public void returnLocationHeaderIfRequestIsForRedirect() {
        requestParser = new RequestParser("GET /redirect HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());

        requestHandler = new GetResponse();

        assertEquals("Location: http://localhost:5000/", requestHandler.getCorrectHeaders(request));
    }

    @Test
    public void returnOptionsHeaderIfRequestIsNotForRedirect() {
        requestParser = new RequestParser("GET / HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());

        requestHandler = new GetResponse();

        assertEquals("Allow: GET,HEAD,POST,OPTIONS,PUT", requestHandler.getCorrectHeaders(request));
    }
}
