package JavaServer.ResponseHandlers;

import JavaServer.RequestHandlers.Request;
import JavaServer.RequestHandlers.RequestParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseCodeBuilderTest {
    private Request request;
    private RequestParser requestParser;
    private ResponseCodeBuilder responseCodeBuilder;

    @Test
    public void returns200ResponseIfRequestValid() {
        requestParser = new RequestParser("GET / HTTP/1.1");
        request = new Request(requestParser.getAllRequestAttributes());
        responseCodeBuilder = new ResponseCodeBuilder(request);
        assertEquals("HTTP/1.1 200 OK", responseCodeBuilder.createValidResponse());
    }

    @Test
    public void returns404ResponseIfPathIsFoobar() {
        requestParser = new RequestParser("GET /foobar HTTP/1.1");
        request = new Request(requestParser.getAllRequestAttributes());
        responseCodeBuilder = new ResponseCodeBuilder(request);
        assertEquals("HTTP/1.1 404 Not Found", responseCodeBuilder.returnFourOhFour());
    }

    @Test
    public void returnsOptionsResponseIfPathIsMethodOptions() {
        requestParser = new RequestParser("OPTIONS /method_options HTTP/1.1");
        request = new Request(requestParser.getAllRequestAttributes());
        responseCodeBuilder = new ResponseCodeBuilder(request);
        assertEquals("GET,HEAD,POST,OPTIONS,PUT", responseCodeBuilder.returnOptionsString());
    }
}
