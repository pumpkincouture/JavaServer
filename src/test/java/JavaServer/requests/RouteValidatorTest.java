package JavaServer.requests;

import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RouteValidatorTest {
    private Request request;
    private RouteValidator routeValidator;
    private RequestParser requestParser;

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

    @Test
    public void returnsTrueIfThereIsAuthorization() {
        requestParser = new RequestParser("POST /form HTTP/1.1\n" +
                                          "Authorization: Basic YWRtaW46aHVudGVyMg==\n" +
                                          "Host: https://sylwiaolak.com\n" +
                                          "\n");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        routeValidator = new RouteValidator(request);

        assertTrue(routeValidator.requestHasCorrectAuthorization());
    }

    @Test
    public void returnsFalseIfThereIsNoAuthorizationAndReturnsEmptyStringForAuthorizationCode() {
        requestParser = new RequestParser("GET /logs HTTP/1.1\n" +
                                          "Host: https://sylwiaolak.com\n" +
                                          "\n");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        routeValidator = new RouteValidator(request);

        assertFalse(routeValidator.requestHasCorrectAuthorization());
    }
}
