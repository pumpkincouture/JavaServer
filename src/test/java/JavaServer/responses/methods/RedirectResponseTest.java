package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RedirectResponseTest {
    private Request request;
    private RequestParser requestParser;
    private Response response;

    @Test
    public void returns302IfPathIsRedirect() {
        requestParser = new RequestParser("GET /redirect HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());

        response = new RedirectResponse();

        assertEquals("HTTP/1.1 302 Found", response.getCorrectStatus());
    }
}
