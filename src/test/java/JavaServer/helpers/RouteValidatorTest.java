package JavaServer.helpers;

import JavaServer.helpers.RouteValidator;
import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
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

    @Test
    public void returnsTrueIfRequestContainsParameterPath() {
        request = createRequestWithParams("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1");
        routeValidator = new RouteValidator(request);

        assertTrue(routeValidator.isParameterPath());
    }


}
