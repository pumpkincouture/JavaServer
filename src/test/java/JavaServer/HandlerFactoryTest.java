package JavaServer;

import JavaServer.RequestHandlers.Request;
import JavaServer.ResponseHandlers.HandlerFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HandlerFactoryTest {
    private HandlerFactory methodFactory;
    private Request request;

    @Test
    public void returnsGetHandlerIfRequestMethodIsGet() {
        request = new Request("GET", "/", "HTTP/1.1");
        methodFactory = new HandlerFactory(request);

        assertEquals("GetMethodHandler", methodFactory.createMethodHandler());
    }

    @Test
    public void returnsPostHandlerIfRequestMethodIsPost() {
        request = new Request("POST", "/", "HTTP/1.1");
        methodFactory = new HandlerFactory(request);

        assertEquals("PostMethodHandler", methodFactory.createMethodHandler());
    }

    @Test
    public void returnsPutHandlerIfRequestMethodIsPut() {
        request = new Request("PUT", "/", "HTTP/1.1");
        methodFactory = new HandlerFactory(request);

        assertEquals("PutMethodHandler", methodFactory.createMethodHandler());
    }

}
