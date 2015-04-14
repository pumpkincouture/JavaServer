package JavaServer.MethodHandlers;

import JavaServer.RequestHandlers.Request;
import JavaServer.RequestHandlers.RequestParser;
import JavaServer.ResponseHandlers.ResponseCodeBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetManagerTest {
    private Request request;
    private RequestParser requestParser;
    private RequestManager requestHandler;
    private ResponseCodeBuilder responseCodeBuilder;

    @Test
    public void returns200ResponseIfRequestValid() {
        requestParser = new RequestParser("GET / HTTP/1.1");
        request = new Request(requestParser.getAllRequestAttributes());
        responseCodeBuilder = new ResponseCodeBuilder(request);
        requestHandler = new GetManager(responseCodeBuilder);

        assertEquals("HTTP/1.1 200 OK", requestHandler.handle(request));
    }

    @Test
    public void returns404ResponseIfInvalidPath() {
        requestParser = new RequestParser("GET /whatever HTTP/1.1");
        request = new Request(requestParser.getAllRequestAttributes());
        responseCodeBuilder = new ResponseCodeBuilder(request);
        requestHandler = new GetManager(responseCodeBuilder);

        assertEquals("HTTP/1.1 404 Not Found", requestHandler.handle(request));
    }
}
