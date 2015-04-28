package JavaServer.responses.methods;


import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FourOhFourResponseTest {
    private Request request;
    private RequestParser requestParser;
    private Response requestHandler;

    @Test
    public void returns404ResponseIfInvalidPath() {
        requestParser = new RequestParser("GET /whatever HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());

        requestHandler = new FourOhFourResponse();

        assertEquals("HTTP/1.1 404 Not Found", requestHandler.getCorrectStatus());
    }
}
