package JavaServer.ResponseManagers.methods;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ResponseFactoryTest {
    private ResponseFactory methodFactory;


    @Test
    public void returnsGetHandlerIfRequestMethodIsGet() {
        methodFactory = new ResponseFactory("GET");

        assertTrue(methodFactory.createMethodManager() instanceof GetResponse);
    }

    @Test
    public void returnsPostHandlerIfRequestMethodIsPost() {
        methodFactory = new ResponseFactory("POST");

        assertTrue(methodFactory.createMethodManager() instanceof PostResponse);
    }

    @Test
    public void returnsPutHandlerIfRequestMethodIsPut() {
        methodFactory = new ResponseFactory("PUT");

        assertTrue(methodFactory.createMethodManager() instanceof PutResponse);
    }

    @Test
    public void returnsOptionsHandlerIfRequestMethodIsOptions() {
        methodFactory = new ResponseFactory("OPTIONS");

        assertTrue(methodFactory.createMethodManager() instanceof OptionsResponse);
    }
}
