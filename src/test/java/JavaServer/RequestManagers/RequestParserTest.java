package JavaServer.RequestManagers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {
    private RequestParser requestParser;

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
    public void getPostedDataFromLongRequest() {
        requestParser = new RequestParser("GET /form\n"+
                                          "Content-Type: application/x-www-form-url-encoded\n"+
                                          "Host: https://sylwiaolak.com\n"+
                                          "Content-Length: 43\n"+
                                          "\n"+
                                          "data=example");

        assertEquals("example", requestParser.getData().get("data"));
    }

    @Test
    public void getPostedDataFromShortRequest() {
        requestParser = new RequestParser("GET /form\n"+
                                          "\n"+
                                          "first_name=sylwia\n"+
                                          "last_name=olak");

        assertEquals("olak", requestParser.getData().get("last_name"));
    }

    @Test
    public void getContentTypeFromHeadersList() {
        requestParser = new RequestParser("GET /form\n"+
                                          "Content-Type: application/x-www-form-url-encoded\n"+
                                          "Host: https://sylwiaolak.com\n"+
                                          "\n"+
                                          "data=example");
        assertEquals("application/x-www-form-url-encoded", requestParser.getHeaders().get("Content-Type"));
    }

    @Test
    public void getHostFromHeadersList() {
        requestParser = new RequestParser("GET /form\n"+
                                          "Content-Type: application/x-www-form-url-encoded\n"+
                                          "Host: https://sylwiaolak.com\n"+
                                          "\n"+
                                          "data=example");
        assertEquals("https://sylwiaolak.com", requestParser.getHeaders().get("Host"));
    }
}
