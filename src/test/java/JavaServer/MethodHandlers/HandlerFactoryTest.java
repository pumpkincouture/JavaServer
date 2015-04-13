package JavaServer.MethodHandlers;

import JavaServer.MethodHandlers.HandlerFactory;
import JavaServer.RequestHandlers.Request;
import JavaServer.RequestHandlers.RequestParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HandlerFactoryTest {
    private HandlerFactory methodFactory;
    private Request request;
    private RequestParser requestParser;


    @Test
    public void returnsGetHandlerIfRequestMethodIsGet() {
        requestParser = new RequestParser("GET / HTTP/1.1");
        request = new Request(requestParser.getAllRequestAttributes());
        methodFactory = new HandlerFactory(request);

        assertEquals("GetHandler", methodFactory.createMethodHandler().getClass().getSimpleName());
    }

    @Test
    public void returnsPostHandlerIfRequestMethodIsPost() {
        requestParser = new RequestParser("POST / HTTP/1.1");
        request = new Request(requestParser.getAllRequestAttributes());
        methodFactory = new HandlerFactory(request);

        assertEquals("PostHandler", methodFactory.createMethodHandler().getClass().getSimpleName());
    }

    @Test
    public void returnsPutHandlerIfRequestMethodIsPut() {
        requestParser = new RequestParser("PUT / HTTP/1.1");
        request = new Request(requestParser.getAllRequestAttributes());
        methodFactory = new HandlerFactory(request);

        assertEquals("PutHandler", methodFactory.createMethodHandler().getClass().getSimpleName());
    }

    @Test
    public void returnsDeleteHandlerIfRequestMethodIsPut() {
        requestParser = new RequestParser("OPTIONS / HTTP/1.1");
        request = new Request(requestParser.getAllRequestAttributes());
        methodFactory = new HandlerFactory(request);

        assertEquals("OptionsHandler", methodFactory.createMethodHandler().getClass().getSimpleName());
    }
}
