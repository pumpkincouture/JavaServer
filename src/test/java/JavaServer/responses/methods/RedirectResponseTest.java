package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class RedirectResponseTest {
    private Request request;
    private RequestParser requestParser;
    private Response response;

    private void createRequestAndFilePath(String requestLine) throws UnsupportedEncodingException {
        requestParser = new RequestParser(requestLine);
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        response = new RedirectResponse();
    }

    @Test
    public void returns302IfPathIsRedirect() throws IOException {
        createRequestAndFilePath("GET /redirect HTTP/1.1");

        assertEquals("HTTP/1.1 302 Found", response.getCorrectStatus());
    }

    @Test
    public void returnLocationHeaderAsHeader() throws UnsupportedEncodingException {
        createRequestAndFilePath("GET /redirect HTTP/1.1");

        assertEquals("Location: http://localhost:5000/", response.getCorrectHeaders());
    }

    @Test
    public void returnEmptyStringAsBody() throws IOException {
        createRequestAndFilePath("GET /redirect HTTP/1.1");

        assertEquals("", response.getCorrectBody());
    }
}
