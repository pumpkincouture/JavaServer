package JavaServer.RequestManagers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestTest {
    private RequestParser requestParser;
    private Request request;

    @Test
    public void returnsMethodForRequest() {
        requestParser = new RequestParser("GET /path/to/file/index.html HTTP/1.0");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());

        assertEquals("GET", request.getMethod());
    }

    @Test
    public void returnsPathToResource() {
        requestParser = new RequestParser("GET /path/to/file/index.html HTTP/1.0");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());

        assertEquals("/path/to/file/index.html", request.getPath());
    }

    @Test
    public void returnsDataFromRequest() {
        requestParser = new RequestParser("GET /path/to/file/index.html HTTP/1.0\n" +
                                          "data=hello");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());

        assertEquals("hello", request.getData().get("data"));
    }
}
