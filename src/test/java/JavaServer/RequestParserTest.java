package JavaServer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {
    private RequestParser requestParser;

    @Test
    public void getFirstCharacterOfRequestString() {
        requestParser = new RequestParser("/");

        assertEquals("/", requestParser.getMethod());
    }

    @Test
    public void returnGETAsMethodInRequestString() {
        requestParser = new RequestParser("GET /");

        assertEquals("GET", requestParser.getMethod());
    }

    @Test
    public void returnPOSTAsMethodInRequestString() {
        requestParser = new RequestParser("POST /");

        assertEquals("POST", requestParser.getMethod());
    }

    @Test
    public void returnPUTAsMethodInRequestString() {
        requestParser = new RequestParser("PUT /");

        assertEquals("PUT", requestParser.getMethod());

    }

    @Test
    public void returnOPTIONSAsMethodInRequestString() {
        requestParser = new RequestParser("OPTIONS /");

        assertEquals("OPTIONS", requestParser.getMethod());

    }

    @Test
    public void returnMethodOptionsAsPathInRequestString() {
        requestParser = new RequestParser("GET /method_options");

        assertEquals("/method_options", requestParser.getPath());
    }

    @Test
    public void returnFormAsPathInRequestString() {
        requestParser = new RequestParser("POST /form");

        assertEquals("/form", requestParser.getPath());
    }

    @Test
    public void returnRedirectAsPathInRequestString() {
        requestParser = new RequestParser("GET /redirect");

        assertEquals("/redirect", requestParser.getPath());
    }

    @Test
    public void returnHTMLVersionInRequestString() {
        requestParser = new RequestParser("GET /localhost:5000/index/form HTTP/1.0");

        assertEquals("HTTP/1.0", requestParser.getHTTPVersion());
    }
}
