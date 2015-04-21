package JavaServer.responses.methods;

import JavaServer.requests.Request;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class ResponseFactoryTest {
    private ResponseFactory methodFactory;
    private Request request;


    private Request createRequest(String method, String path) {
        request = new Request(method, path, new HashMap<>(), new HashMap<>());

        return request;
    }

    @Test
    public void returnsGetHandlerIfRequestMethodIsGet() {
        methodFactory = new ResponseFactory(createRequest("GET", "/"));

        assertTrue(methodFactory.createResponse() instanceof GetResponse);
    }

    @Test
    public void returnsPostHandlerIfRequestMethodIsPost() {
        methodFactory = new ResponseFactory(createRequest("POST", "/"));

        assertTrue(methodFactory.createResponse() instanceof PostResponse);
    }

    @Test
    public void returnsPutHandlerIfRequestMethodIsPut() {
        methodFactory = new ResponseFactory(createRequest("PUT", "/"));

        assertTrue(methodFactory.createResponse() instanceof PutResponse);
    }

    @Test
    public void returnsOptionsHandlerIfRequestMethodIsOptions() {
        methodFactory = new ResponseFactory(createRequest("OPTIONS", "/"));

        assertTrue(methodFactory.createResponse() instanceof OptionsResponse);
    }
}
