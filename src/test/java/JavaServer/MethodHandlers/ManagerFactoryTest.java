package JavaServer.MethodHandlers;

import JavaServer.RequestHandlers.Request;
import JavaServer.RequestHandlers.RequestParser;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ManagerFactoryTest {
    private ManagerFactory methodFactory;
    private Request request;
    private RequestParser requestParser;


    @Test
    public void returnsGetHandlerIfRequestMethodIsGet() {
        requestParser = new RequestParser("GET / HTTP/1.1");
        request = new Request(requestParser.getAllRequestAttributes());
        methodFactory = new ManagerFactory(request);

        assertTrue(methodFactory.createMethodHandler() instanceof GetManager);
    }

    @Test
    public void returnsPostHandlerIfRequestMethodIsPost() {
        requestParser = new RequestParser("POST / HTTP/1.1");
        request = new Request(requestParser.getAllRequestAttributes());
        methodFactory = new ManagerFactory(request);

        assertTrue(methodFactory.createMethodHandler() instanceof PostManager);
    }

    @Test
    public void returnsPutHandlerIfRequestMethodIsPut() {
        requestParser = new RequestParser("PUT / HTTP/1.1");
        request = new Request(requestParser.getAllRequestAttributes());
        methodFactory = new ManagerFactory(request);

        assertTrue(methodFactory.createMethodHandler() instanceof PutManager);
    }

    @Test
    public void returnsOptionsHandlerIfRequestMethodIsOptions() {
        requestParser = new RequestParser("OPTIONS / HTTP/1.1");
        request = new Request(requestParser.getAllRequestAttributes());
        methodFactory = new ManagerFactory(request);

        assertTrue(methodFactory.createMethodHandler() instanceof OptionsManager);
    }
}
