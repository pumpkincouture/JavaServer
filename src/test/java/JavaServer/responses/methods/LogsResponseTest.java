package JavaServer.responses.methods;

import JavaServer.requests.Logger;
import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class LogsResponseTest {
    private Response response;
    private Logger logger;
    private RequestParser requestParser;
    private Request request;

    private void createRequestAndResponse(String requestLine) {
        requestParser = new RequestParser(requestLine);
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        logger = new Logger();
        logger.logRequest(request.getRequestLine());
        response = new LogsResponse(logger);
    }

    @Test
    public void returns200AsStatus() throws IOException {
        createRequestAndResponse("GET /logs HTTP/1.1\n" +
                                 "Authorization: Basic YWRtaW46aHVudGVyMg==\n" +
                                 "Host: localhost:5000\n" +
                                 "\n");

        assertEquals("HTTP/1.1 200 OK", response.getCorrectStatus());
    }

    @Test
    public void returnsEmptyStringAsHeader() {
        createRequestAndResponse("GET /logs HTTP/1.1\n" +
                                 "Authorization: Basic YWRtaW46aHVudGVyMg==\n" +
                                 "Host: localhost:5000\n" +
                                 "\n");

        assertEquals("", response.getCorrectHeaders());
    }

    @Test
    public void returnsLoggingDataAsBody() throws IOException {
        createRequestAndResponse("GET /logs HTTP/1.1\n" +
                                 "Authorization: Basic YWRtaW46aHVudGVyMg==\n" +
                                 "Host: localhost:5000\n" +
                                 "\n");

        assertEquals("GET /logs HTTP/1.1\r\n", response.getCorrectBody());
    }
}
