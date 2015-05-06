package JavaServer.responses.methods;


import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class FourOhFourResponseTest {
    private Request request;
    private RequestParser requestParser;
    private Response response;

    private void createRequestAndResponse(String requestLine) {
        requestParser = new RequestParser(requestLine);
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        response = new FourOhFourResponse();
    }

    @Test
    public void returns404ResponseIfInvalidPath() throws IOException {
        createRequestAndResponse("GET /whatever HTTP/1.1");

        assertEquals("HTTP/1.1 404 Not Found", response.getCorrectStatus());
    }

    @Test
    public void returnsEmptyStringAsHeader() {
        createRequestAndResponse("GET /snuggles HTTP/1.1");

        assertEquals("", response.getCorrectHeaders());
    }

    @Test
    public void returnsEmptyStringAsBody() throws IOException {
        createRequestAndResponse("GET /more_snuggles HTTP/1.1");

        assertEquals("", response.getCorrectBody());
    }


}
