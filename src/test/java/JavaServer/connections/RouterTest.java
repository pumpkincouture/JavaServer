package JavaServer.connections;

import JavaServer.requests.Logger;
import JavaServer.responses.DataManager;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class RouterTest {
    private Router router;
    private String directory;
    private DataManager dataManager;
    private Logger logger;

    private DataOutputStream createFakeInput(String input) throws UnsupportedEncodingException {
        ByteArrayOutputStream mockInputStream = new ByteArrayOutputStream();

        DataOutputStream out = new DataOutputStream(mockInputStream);
        return out;
    }

    private void createRouter(String request)  {
        logger = new Logger();
        dataManager = new DataManager();
        directory = "/Users/test/code/JavaServer/public";
        try {
            router = new Router(request, directory, createFakeInput(request), dataManager, logger);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        router.createHandlers();
    }

    @Test
    public void returnsAResponseCodeOf200IfMethodIsGetAndPathIsValidWithNoHeaders() {
        createRouter("GET / HTTP/1.1");

        assertEquals("HTTP/1.1 200 OK\n" + "\r\n", router.getResponse());
    }

    @Test
    public void returnsAResponseCodeOf200WithHeadersIfMethodIsOptionsAndPathIsValid() {
        createRouter("OPTIONS /method_options HTTP/1.1");

        assertEquals("HTTP/1.1 200 OK\n" + "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n", router.getResponse());
    }

    @Test
    public void returnsAResponseCodeOf404IfThisOptionsPathIsNotValid() {
        createRouter("OPTIONS / HTTP/1.1");

        assertEquals("HTTP/1.1 404 Not Found\n" + "\r\n", router.getResponse());
    }

    @Test
    public void returnsAResponseCodeOf200WithOptionsHeadersIfMethodIsGet() {
        createRouter("GET /method_options HTTP/1.1");

        assertEquals("HTTP/1.1 200 OK\n" + "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n", router.getResponse());
    }

    @Test
    public void returnsAResponseCodeOf200WithOptionsHeadersIfMethodIsPut() {
        createRouter("PUT /method_options HTTP/1.1");

        assertEquals("HTTP/1.1 200 OK\n" + "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n", router.getResponse());
    }

    @Test
    public void returnsAResponseCodeOf200WithOptionsHeadersIfMethodIsPost() {
        createRouter("POST /method_options HTTP/1.1");

        assertEquals("HTTP/1.1 200 OK\n" + "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n", router.getResponse());
    }

    @Test
    public void returnsAResponseCodeOf200WithOptionsHeadersIfMethodIsHead() {
        createRouter("HEAD /method_options");

        assertEquals("HTTP/1.1 200 OK\n" + "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n", router.getResponse());
    }

    @Test
    public void returns404ResponseWithNoHeadersIfPathDoesNotExist() {
        createRouter("GET /foobar HTTP/1.1");

        assertEquals("HTTP/1.1 404 Not Found\n" + "\r\n", router.getResponse());
    }

    @Test
    public void returns404IfFileDoesNotExist() {
        createRouter("GET /octopus.jpg HTTP/1.1");

        assertEquals("HTTP/1.1 404 Not Found\n" +  "\r\n", router.getResponse());
    }

    @Test
    public void returns405IfMethodIsPutButPathIsInvalid() {
        createRouter("PUT /file1 HTTP/1.1");

        assertEquals("HTTP/1.1 405 Method Not Allowed\n" +  "\r\n", router.getResponse());
    }

    @Test
    public void returns405IfMethodIsPostButPathIsInvalid() {
        createRouter("POST /text-file.txt HTTP/1.1");

        assertEquals("HTTP/1.1 405 Method Not Allowed\n" + "\r\n", router.getResponse());
    }

    @Test
    public void returns302IfRequestIsForRedirect() {
        createRouter("GET /redirect HTTP/1.1");

        assertEquals("HTTP/1.1 302 Found\n" + "Location: http://localhost:5000/\r\n", router.getResponse());
    }

    @Test
    public void returns200AndHandlesDataParamsIfRequestForPostMethod() throws IOException {
        createRouter("POST /form HTTP/1.1\n"+
                     "\n"+
                     "My=Data");

        assertEquals("HTTP/1.1 200 OK\n" + "\r\n", router.getResponse());
        assertEquals("", router.getBody());
    }

    @Test
    public void returns200AndHandlesDataParamsIfRequestForPutMethod() {
        createRouter("PUT /form HTTP/1.1\n"+
                     "\n"+
                     "My=Data");

        assertEquals("HTTP/1.1 200 OK\n" + "\r\n", router.getResponse());
    }
}
