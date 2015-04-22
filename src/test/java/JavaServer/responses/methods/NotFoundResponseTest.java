package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NotFoundResponseTest {
    private Request request;
    private RequestParser requestParser;
    private Response requestHandler;

    @Test
    public void returns302IfPathIsRedirect() {
        requestParser = new RequestParser("GET /redirect HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());

        requestHandler = new NotFoundResponse();

        assertEquals("HTTP/1.1 302 Found", requestHandler.getCorrectStatus(request));
    }
}
