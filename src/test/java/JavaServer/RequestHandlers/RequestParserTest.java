package JavaServer.RequestHandlers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RequestParserTest {
    private RequestParser requestParser;

    @Test
    public void returnGETAsMethodInRequestString() {
        requestParser = new RequestParser("GET /");

        assertEquals("GET", requestParser.getAllRequestAttributes().get("requestMethod"));
    }

    @Test
    public void returnPOSTAsMethodInRequestString() {
        requestParser = new RequestParser("POST /");

        assertEquals("POST", requestParser.getAllRequestAttributes().get("requestMethod"));
    }

    @Test
    public void returnPUTAsMethodInRequestString() {
        requestParser = new RequestParser("PUT /");

        assertEquals("PUT", requestParser.getAllRequestAttributes().get("requestMethod"));
    }

    @Test
    public void returnOPTIONSAsMethodInRequestString() {
        requestParser = new RequestParser("OPTIONS /");

        assertEquals("OPTIONS", requestParser.getAllRequestAttributes().get("requestMethod"));
    }

    @Test
    public void returnMethodOptionsAsPathInRequestString() {
        requestParser = new RequestParser("GET /method_options");

        assertEquals("/method_options", requestParser.getAllRequestAttributes().get("path"));
    }

    @Test
    public void returnFormAsPathInRequestString() {
        requestParser = new RequestParser("POST /form");

        assertEquals("/form", requestParser.getAllRequestAttributes().get("path"));
    }

    @Test
    public void returnRedirectAsPathInRequestString() {
        requestParser = new RequestParser("GET /redirect");

        assertEquals("/redirect", requestParser.getAllRequestAttributes().get("path"));
    }

    @Test
    public void getPostedDataFromLongRequest() {
        requestParser = new RequestParser("GET /localhost:5000/index/form\n"+
                                          "Content-Type : application/x-www-form-url-encoded\n"+
                                          "Host : https://sylwiaolak.com\n"+
                                          "Content-Length: 43\n"+
                                          "\n"+
                                          "data=example");

        assertEquals("data=example", requestParser.getAllRequestAttributes().get("data"));
    }

    @Test
    public void getPostedDataFromShortRequest() {
        requestParser = new RequestParser("GET /localhost:5000/index/form\n"+
                                          "name=sylwia");

        assertEquals("name=sylwia", requestParser.getAllRequestAttributes().get("data"));
    }
}
