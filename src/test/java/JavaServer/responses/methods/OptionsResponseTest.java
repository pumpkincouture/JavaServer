package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class OptionsResponseTest {
    private Request request;
    private RequestParser requestParser;
    private Response response;

    private void createRequestAndResponse(String requestLine) {
        requestParser = new RequestParser(requestLine);
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        response = new OptionsResponse();
    }

    @Test
    public void returns200ResponseStatus() {
        createRequestAndResponse("OPTIONS /method_options HTTP/1.1");

        assertEquals("HTTP/1.1 200 OK", response.getCorrectStatus());
    }

    @Test
    public void returnsOptionsHeader() {
        createRequestAndResponse("OPTIONS /method_options HTTP/1.1");

        assertEquals("Allow: GET,HEAD,POST,OPTIONS,PUT", response.getCorrectHeaders());
    }

    @Test
    public void returnsEmptyStringAsBody() throws IOException {
        createRequestAndResponse("OPTIONS /method_options HTTP/1.1");

        assertEquals("", response.getCorrectBody());
    }
}
