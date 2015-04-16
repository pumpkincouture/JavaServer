package JavaServer.ResponseManagers.MethodManagers;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ManagerFactoryTest {
    private ManagerFactory methodFactory;


    @Test
    public void returnsGetHandlerIfRequestMethodIsGet() {
        methodFactory = new ManagerFactory("GET");

        assertTrue(methodFactory.createMethodManager() instanceof GetManager);
    }

    @Test
    public void returnsPostHandlerIfRequestMethodIsPost() {
        methodFactory = new ManagerFactory("POST");

        assertTrue(methodFactory.createMethodManager() instanceof PostManager);
    }

    @Test
    public void returnsPutHandlerIfRequestMethodIsPut() {
        methodFactory = new ManagerFactory("PUT");

        assertTrue(methodFactory.createMethodManager() instanceof PutManager);
    }

    @Test
    public void returnsOptionsHandlerIfRequestMethodIsOptions() {
        methodFactory = new ManagerFactory("OPTIONS");

        assertTrue(methodFactory.createMethodManager() instanceof OptionsManager);
    }
}
