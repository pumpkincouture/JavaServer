package JavaServer.requests;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RouteValidatorTest {
    private Request request;
    private RouteValidator routeValidator;

    private Request createRequest(String method, String path) {
        request = new Request(method, path, new HashMap<>(), new HashMap<>());

        return request;
    }

    @Test
    public void returnsTrueIfMethodIsValid() {
        routeValidator= new RouteValidator(createRequest("GET", "/"));

        assertTrue(routeValidator.isMethodValid());
    }

    @Test
    public void returnsFalseIfMethodIsInValid() {
        routeValidator= new RouteValidator(createRequest("GERUND", "/"));

        assertFalse(routeValidator.isMethodValid());
    }

    @Test
    public void returnsTrueIfPathIsForRedirectionButMethodIsInvalid() {
        routeValidator= new RouteValidator(createRequest("GERUND", "/redirect"));

        assertTrue(routeValidator.isInvalidPath());
    }

    @Test
    public void returnsTrueIfMethodIsValidButPathIsInvalid() {
        routeValidator= new RouteValidator(createRequest("GET", "/get_unicorns"));

        assertTrue(routeValidator.isInvalidPath());
    }
}
