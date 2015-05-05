package JavaServer.requests;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RouteValidatorTest {
    private Request request;
    private RouteValidator routeValidator;

    private Request createRequestWithNoParams(String method, String path) {
        request = new Request(method, path, new HashMap<>(), new HashMap<>());

        return request;
    }

    private Request createRequestWithParams(String requestString) {
        RequestParser parser = new RequestParser(requestString);

        request = new Request(parser.getMethod(), parser.getPath(), parser.getHeaders(), parser.getData());

        return request;
    }

    @Test
    public void returnsTrueIfMethodIsValid() {
        routeValidator= new RouteValidator(createRequestWithNoParams("GET", "/"));

        assertTrue(routeValidator.isMethodValid());
    }

    @Test
    public void returnsFalseIfMethodIsInValid() {
        routeValidator= new RouteValidator(createRequestWithNoParams("GERUND", "/"));

        assertFalse(routeValidator.isMethodValid());
    }

    @Test
    public void returnsTrueIfPathIsForRedirectionButMethodIsInvalid() {
        routeValidator= new RouteValidator(createRequestWithNoParams("GERUND", "/redirect"));

        assertTrue(routeValidator.isInvalidPath());
    }

    @Test
    public void returnsTrueIfMethodIsValidButPathIsInvalid() {
        routeValidator= new RouteValidator(createRequestWithNoParams("GET", "/get_unicorns"));

        assertTrue(routeValidator.isInvalidPath());
    }

    @Test
    public void returnsTrueIfThereIsAuthorization() {
        request = createRequestWithParams("POST /form HTTP/1.1\n" +
                                          "Authorization: Basic YWRtaW46aHVudGVyMg==\n" +
                                          "Host: https://sylwiaolak.com\n" +
                                          "\n");
        routeValidator = new RouteValidator(request);

        assertTrue(routeValidator.requestHasCorrectAuthorization());
    }

    @Test
    public void returnsFalseIfThereIsNoAuthorization() {
        request = createRequestWithParams("GET /logs HTTP/1.1\n" +
                                          "Host: https://sylwiaolak.com\n" +
                                          "\n");
        routeValidator = new RouteValidator(request);

        assertFalse(routeValidator.requestHasCorrectAuthorization());
    }
}
