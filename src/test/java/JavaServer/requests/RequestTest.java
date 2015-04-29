package JavaServer.requests;

import org.junit.Test;

import java.util.HashMap;

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

    @Test
    public void returnsMethodForRequestIfMethodIsPost() {
        requestParser = new RequestParser("POST /form HTTP/1.1\n"+
                                          "\n"+
                                          "My=Data");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());

        assertEquals("POST", request.getMethod());
        assertEquals("/form", request.getPath());
        assertEquals(new HashMap<String, String>(), request.getHeaders());
        assertEquals("Data", request.getData().get("My"));
    }

    @Test
    public void returnsFirstLineOfRequest() {
        requestParser = new RequestParser("POST /form HTTP/1.1\n"+
                                          "\n"+
                                          "My=Data");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());

        assertEquals("POST /form HTTP/1.1", request.getRequestLine());
    }
}
