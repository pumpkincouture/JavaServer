package JavaServer.MethodManagers;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ManagerFactoryTest {
    private ManagerFactory methodFactory;


    @Test
    public void returnsGetHandlerIfRequestMethodIsGet() {
        methodFactory = new ManagerFactory("GET");

        assertTrue(methodFactory.createMethodHandler() instanceof GetManager);
    }

    @Test
    public void returnsPostHandlerIfRequestMethodIsPost() {
        methodFactory = new ManagerFactory("POST");

        assertTrue(methodFactory.createMethodHandler() instanceof PostManager);
    }

    @Test
    public void returnsPutHandlerIfRequestMethodIsPut() {
        methodFactory = new ManagerFactory("PUT");

        assertTrue(methodFactory.createMethodHandler() instanceof PutManager);
    }

    @Test
    public void returnsOptionsHandlerIfRequestMethodIsOptions() {
        methodFactory = new ManagerFactory("OPTIONS");

        assertTrue(methodFactory.createMethodHandler() instanceof OptionsManager);
    }
}
