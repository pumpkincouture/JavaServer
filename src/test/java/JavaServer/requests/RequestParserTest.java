package JavaServer.requests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {
    private RequestParser requestParser;

    @Test
    public void returnGETAsMethodInRequestString() {
        requestParser = new RequestParser("GET / HTTP/1.1");

        assertEquals("GET", requestParser.getMethod());
    }

    @Test
    public void returnPOSTAsMethodInRequestString() {
        requestParser = new RequestParser("POST / HTTP/1.1");

        assertEquals("POST", requestParser.getMethod());
    }

    @Test
    public void returnPUTAsMethodInRequestString() {
        requestParser = new RequestParser("PUT / HTTP/1.1");

        assertEquals("PUT", requestParser.getMethod());
    }

    @Test
    public void returnOPTIONSAsMethodInRequestString() {
        requestParser = new RequestParser("OPTIONS / HTTP/1.1");

        assertEquals("OPTIONS", requestParser.getMethod());
    }

    @Test
    public void returnMethodOptionsAsPathInRequestString() {
        requestParser = new RequestParser("GET /method_options HTTP/1.1");

        assertEquals("/method_options", requestParser.getPath());
    }

    @Test
    public void returnFormAsPathInRequestString() {
        requestParser = new RequestParser("POST /form HTTP/1.1");

        assertEquals("/form", requestParser.getPath());
    }

    @Test
    public void returnRedirectAsPathInRequestString() {
        requestParser = new RequestParser("GET /redirect HTTP/1.1");

        assertEquals("/redirect", requestParser.getPath());
    }

    @Test
    public void parseAndStoreMoreThanOnePostParam() {
        requestParser = new RequestParser("POST /form HTTP/1.1\n"+
                                          "\n"+
                                          "first_name=sylwia\n"+
                                          "last_name=olak\n"+
                                          "age=26");

        assertEquals("/form", requestParser.getPath());
        assertEquals("sylwia", requestParser.getData().get("first_name"));
        assertEquals("olak", requestParser.getData().get("last_name"));
        assertEquals("26", requestParser.getData().get("age"));
    }

    @Test
    public void returnsNullIfThereAreNoValuesInHeadersHashMap() {
        requestParser = new RequestParser("GET /form HTTP/1.1");

        assertEquals(null, requestParser.getHeaders().get("Content-Type"));
    }

    @Test
    public void returnsNullIfThereAreNoValuesInParamsHashMap() {
        requestParser = new RequestParser("POST /form HTTP/1.1\n"+
                                          "Content-Type: application/x-www-form-url-encoded\n"+
                                          "Host: https://sylwiaolak.com\n"+
                                          "\n");

        assertEquals(null, requestParser.getData().get("last_name"));
    }

    @Test
    public void getContentTypeFromHeadersList() {
        requestParser = new RequestParser("POST /form HTTP/1.1\n"+
                                          "Content-Type: application/x-www-form-url-encoded\n"+
                                          "Host: https://sylwiaolak.com\n"+
                                          "\n"+
                                          "data=example");

        assertEquals("application/x-www-form-url-encoded", requestParser.getHeaders().get("Content-Type"));
    }

    @Test
    public void getHostFromHeadersList() {
        requestParser = new RequestParser("POST /form HTTP/1.1\n"+
                                          "Content-Type: application/x-www-form-url-encoded\n"+
                                          "Host: https://sylwiaolak.com\n"+
                                          "\n"+
                                          "data=example");

        assertEquals("https://sylwiaolak.com", requestParser.getHeaders().get("Host"));
    }


    @Test
    public void getAuthorizationFromList() {
        requestParser = new RequestParser("GET /logs HTTP/1.1\n" +
                                          "Authorization: Basic YWRtaW46aHVudGVyMg==\n" +
                                          "Host: localhost:5000\n" +
                                          "\n");

        assertEquals("Basic YWRtaW46aHVudGVyMg==", requestParser.getHeaders().get("Authorization"));
    }
}
