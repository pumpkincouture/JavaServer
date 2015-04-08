package JavaServer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseCodeBuilderTest {
    private Request request;
    private ResponseCodeBuilder responseCodeSender;

    @Test
    public void returns200ResponseIfRequestValid() {
        request = new Request("GET", "/", "HTTP/1.1");
        responseCodeSender = new ResponseCodeBuilder(request);

        assertEquals("HTTP/1.1 200 OK", responseCodeSender.returnResponseCode());
    }

    @Test
    public void returns302ResponseIfRedirectRequest() {
        request = new Request("GET", "/redirect", "HTTP/1.1");
        responseCodeSender = new ResponseCodeBuilder(request);

        assertEquals("HTTP/1.1 302 Moved Temporarily", responseCodeSender.returnResponseCode());
    }

    @Test
    public void returns404ResponseIfPathIsFoobar() {
        request = new Request("GET", "/foobar", "HTTP/1.1");
        responseCodeSender = new ResponseCodeBuilder(request);

        assertEquals("HTTP/1.1 404 Not Found", responseCodeSender.returnResponseCode());

    }

    @Test
    public void returns405ResponseAsPUTMethodNotAllowed() {
        request = new Request("PUT", "/file1", "HTTP/1.1");
        responseCodeSender = new ResponseCodeBuilder(request);

        assertEquals("HTTP/1.1 405 Method Not Allowed", responseCodeSender.returnResponseCode());
    }

    @Test
    public void returns200ResponseWithOptionsHeader() {
        request = new Request("OPTIONS", "/method_options", "HTTP/1.1");
        responseCodeSender = new ResponseCodeBuilder(request);

        assertEquals("HTTP/1.1 200 OK", responseCodeSender.returnResponseCode());
    }

    @Test
    public void returns405ResponseAsPOSTMethodNotAllowed() {
        request = new Request("POST", "/text-file.txt", "HTTP/1.1");
        responseCodeSender = new ResponseCodeBuilder(request);

        assertEquals("HTTP/1.1 405 Method Not Allowed", responseCodeSender.returnResponseCode());

    }

    @Test
    public void returnsHeadersTable() {
        request = new Request("POST", "/text-file.txt", "HTTP/1.1");
        responseCodeSender = new ResponseCodeBuilder(request);

        assertEquals("GET,HEAD,POST,OPTIONS,PUT", responseCodeSender.getHeaders().get("Allow"));

    }
}
