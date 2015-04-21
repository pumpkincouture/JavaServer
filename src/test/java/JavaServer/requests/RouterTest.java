package JavaServer.requests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RouterTest {
    private Router router;

    private void createRouter(String request) {
        router = new Router(request);
        router.createHandlers();
    }

    @Test
    public void returnsAResponseCodeOf200IfMethodIsGetAndPathIsValidWithNoHeaders() {
        createRouter("GET / HTTP/1.1");

        assertEquals("HTTP/1.1 200 OK\n" + "\n" + "Content-Type: text/html\r\n", router.getResponse());
    }

    @Test
    public void returnsAResponseCodeOf200WithHeadersIfMethodIsOptionsAndPathIsValid() {
        createRouter("OPTIONS /method_options HTTP/1.1");

        assertEquals("HTTP/1.1 200 OK\n" + "Allow: GET,HEAD,POST,OPTIONS,PUT\n" + "\r\n", router.getResponse());
    }

    @Test
    public void returnsAResponseCodeOf200WithOptionsHeadersIfMethodIsGet() {
        createRouter("GET /method_options HTTP/1.1");

        assertEquals("HTTP/1.1 200 OK\n" + "Allow: GET,HEAD,POST,OPTIONS,PUT\n" + "\r\n", router.getResponse());
    }

    @Test
    public void returnsAResponseCodeOf200WithOptionsHeadersIfMethodIsPut() {
        createRouter("PUT /method_options HTTP/1.1");

        assertEquals("HTTP/1.1 200 OK\n" + "Allow: GET,HEAD,POST,OPTIONS,PUT\n" + "\r\n", router.getResponse());
    }

    @Test
    public void returnsAResponseCodeOf200WithOptionsHeadersIfMethodIsPost() {
        createRouter("POST /method_options HTTP/1.1");

        assertEquals("HTTP/1.1 200 OK\n" + "Allow: GET,HEAD,POST,OPTIONS,PUT\n" + "\r\n", router.getResponse());
    }

    @Test
    public void returnsAResponseCodeOf200WithOptionsHeadersIfMethodIsHead() {
        createRouter("HEAD /method_options");

        assertEquals("HTTP/1.1 200 OK\n" + "Allow: GET,HEAD,POST,OPTIONS,PUT\n" + "\r\n", router.getResponse());
    }

    @Test
    public void returns404ResponseWithNoHeadersIfPathDoesNotExist() {
        createRouter("GET /foobar HTTP/1.1");

        assertEquals("HTTP/1.1 404 Not Found\n" + "\n" + "\r\n", router.getResponse());
    }

    @Test
    public void returns405IfMethodIsPutButPathIsInvalid() {
        createRouter("PUT /file1 HTTP/1.1");

        assertEquals("HTTP/1.1 405 Method Not Allowed\n" + "\n" + "\r\n", router.getResponse());
    }

    @Test
    public void returns405IfMethodIsPostButPathIsInvalid() {
        createRouter("POST /text-file.txt HTTP/1.1");

        assertEquals("HTTP/1.1 405 Method Not Allowed\n" + "\n" + "\r\n", router.getResponse());
    }

    @Test
    public void returns302IfRequestIsForRedirect() {
        createRouter("GET /redirect HTTP/1.1");

        assertEquals("HTTP/1.1 302 Found\n" + "Location: http://localhost:5000/\n" + "\r\n", router.getResponse());
    }

    @Test
    public void returns200AndHandlesDataParamsIfRequestForPostMethod() {
        createRouter("POST /form HTTP/1.1\n"+
                     "\n"+
                     "My=Data");

        assertEquals("HTTP/1.1 200 OK\n" + "\n" + "\r\n", router.getResponse());
    }

    @Test
    public void returns200AndHandlesDataParamsIfRequestForPutMethod() {
        createRouter("PUT /form HTTP/1.1\n"+
                     "\n"+
                     "My=Data");

        assertEquals("HTTP/1.1 200 OK\n" + "\n" + "\r\n", router.getResponse());

    }
}
