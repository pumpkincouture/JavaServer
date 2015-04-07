package JavaServer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestTest {
    private RequestParser requestParser;
    private Request request;

    @Test
    public void returnsMethodForRequest() {
        requestParser = new RequestParser("GET /path/to/file/index.html HTTP/1.0");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHTTPVersion());

        assertEquals("GET", request.getMethod());
    }

    @Test
    public void returnsPathToResource() {
        requestParser = new RequestParser("GET /path/to/file/index.html HTTP/1.0");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHTTPVersion());

        assertEquals("/path/to/file/index.html", request.getPath());
    }

    @Test
    public void returnsHTTPVersion() {
        requestParser = new RequestParser("GET /path/to/file/index.html HTTP/1.0");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHTTPVersion());

        assertEquals("HTTP/1.0", request.getHTTP());
    }
}
