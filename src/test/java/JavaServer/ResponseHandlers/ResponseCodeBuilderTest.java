package JavaServer.ResponseHandlers;

import JavaServer.RequestHandlers.Request;
import JavaServer.RequestHandlers.RequestParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseCodeBuilderTest {
    private Request request;
    private RequestParser requestParser;
    private ResponseCodeBuilder responseCodeSender;

    @Test
    public void returns200ResponseIfRequestValid() {
        requestParser = new RequestParser("GET / HTTP/1.1");
        request = new Request(requestParser.getAllRequestAttributes());
        responseCodeSender = new ResponseCodeBuilder(request);
        assertEquals("HTTP/1.1 200 OK", responseCodeSender.createValidResponse());
    }

    @Test
    public void returns404ResponseIfPathIsFoobar() {
        requestParser = new RequestParser("GET /foobar HTTP/1.1");
        request = new Request(requestParser.getAllRequestAttributes());
        responseCodeSender = new ResponseCodeBuilder(request);
        assertEquals("HTTP/1.1 404 Not Found", responseCodeSender.returnFourOhFour());
    }

    @Test
    public void returnsOptionsResponseIfPathIsMethodOptions() {
        requestParser = new RequestParser("OPTIONS /method_options HTTP/1.1");
        request = new Request(requestParser.getAllRequestAttributes());
        responseCodeSender = new ResponseCodeBuilder(request);
        assertEquals("GET,HEAD,POST,OPTIONS,PUT", responseCodeSender.returnOptionsString());
    }
}
