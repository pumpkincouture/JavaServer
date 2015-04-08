package JavaServer;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseCodeSenderTest {
    private Request request;
    private ResponseCodeSender responseCodeSender;

    @Test
    public void returns200ResponseIfGETRequest() {
        request = new Request("GET", "/", "HTTP/1.1");
        responseCodeSender = new ResponseCodeSender(request);

        assertEquals("200", responseCodeSender.returnResponseCode());
    }

    @Test
    public void returns302ResponseIfRedirectRequest() {
        request = new Request("GET", "/redirect", "HTTP/1.1");
        responseCodeSender = new ResponseCodeSender(request);

        assertEquals("302", responseCodeSender.returnResponseCode());
    }

    @Test
    public void returns404ResponseIfPathIsFoobar() {
        request = new Request("GET", "/foobar", "HTTP/1.1");
        responseCodeSender = new ResponseCodeSender(request);

        assertEquals("404", responseCodeSender.returnResponseCode());

    }

    @Test
    public void returns405ResponseIfMethodNotAllowed() {
        request = new Request("PUT", "/file1", "HTTP/1.1");
        responseCodeSender = new ResponseCodeSender(request);

        assertEquals("405", responseCodeSender.returnResponseCode());

    }


}
