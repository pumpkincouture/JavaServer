package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OptionsResponseTest {
    private Request request;
    private RequestParser requestParser;
    private Response response;

    @Test
    public void returns200ResponseIfRequestPathValid() {
        requestParser = new RequestParser("OPTIONS /method_options HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        response = new OptionsResponse();

        assertEquals("HTTP/1.1 200 OK", response.getCorrectStatus());
    }
}
