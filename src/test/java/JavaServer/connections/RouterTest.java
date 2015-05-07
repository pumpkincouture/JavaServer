package JavaServer.connections;

import JavaServer.requests.Logger;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class RouterTest {
    private Router router;
    private String directory;
    private Logger logger;

    private DataOutputStream mockOutput() throws UnsupportedEncodingException {
        ByteArrayOutputStream mockInputStream = new ByteArrayOutputStream();

        DataOutputStream out = new DataOutputStream(mockInputStream);
        return out;
    }

    private void createRouter(String request)  {
        logger = new Logger();
        directory = "/Users/test/code/JavaServer/public";
        try {
            router = new Router(request, directory, mockOutput(), logger);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        router.createHandlers();
    }

    @Test
    public void returnsAResponseCodeOf200IfMethodIsGetAndPathIsValidWithNoHeaders() throws IOException {
        createRouter("GET / HTTP/1.1");

        assertEquals("HTTP/1.1 200 OK\n" + "\r\n", router.getResponse());
    }

    @Test
    public void returnsAResponseCodeOf200WithHeadersIfMethodIsOptionsAndPathIsValid() throws IOException {
        createRouter("OPTIONS /method_options HTTP/1.1");

        assertEquals("HTTP/1.1 200 OK\n" + "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n", router.getResponse());
    }

    @Test
    public void returnsAResponseCodeOf404IfThisOptionsPathIsNotValid() throws IOException {
        createRouter("OPTIONS / HTTP/1.1");

        assertEquals("HTTP/1.1 404 Not Found\n" + "\r\n", router.getResponse());
    }

    @Test
    public void returnsAResponseCodeOf200WithOptionsHeadersIfMethodIsGet() throws IOException {
        createRouter("GET /method_options HTTP/1.1");

        assertEquals("HTTP/1.1 200 OK\n" + "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n", router.getResponse());
    }

    @Test
    public void returnsAResponseCodeOf200WithOptionsHeadersIfMethodIsPut() throws IOException {
        createRouter("PUT /method_options HTTP/1.1");

        assertEquals("HTTP/1.1 200 OK\n" + "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n", router.getResponse());
    }

    @Test
    public void returnsAResponseCodeOf200WithOptionsHeadersIfMethodIsPost() throws IOException {
        createRouter("POST /method_options HTTP/1.1");

        assertEquals("HTTP/1.1 200 OK\n" + "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n", router.getResponse());
    }

    @Test
    public void returnsAResponseCodeOf200WithOptionsHeadersIfMethodIsHead() throws IOException {
        createRouter("HEAD /method_options");

        assertEquals("HTTP/1.1 200 OK\n" + "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n", router.getResponse());
    }

    @Test
    public void returns404ResponseWithNoHeadersIfPathDoesNotExist() throws IOException {
        createRouter("GET /foobar HTTP/1.1");

        assertEquals("HTTP/1.1 404 Not Found\n" + "\r\n", router.getResponse());
    }

    @Test
    public void returns404IfFileDoesNotExist() throws IOException {
        createRouter("GET /octopus.jpg HTTP/1.1");

        assertEquals("HTTP/1.1 404 Not Found\n" +  "\r\n", router.getResponse());
    }

    @Test
    public void returns405IfMethodIsPutButPathIsInvalid() throws IOException {
        createRouter("PUT /file1 HTTP/1.1");

        assertEquals("HTTP/1.1 405 Method Not Allowed\n" +  "\r\n", router.getResponse());
    }

    @Test
    public void returns405IfMethodIsPostButPathIsInvalid() throws IOException {
        createRouter("POST /text-file.txt HTTP/1.1");

        assertEquals("HTTP/1.1 405 Method Not Allowed\n" + "\r\n", router.getResponse());
    }

    @Test
    public void returns302IfRequestIsForRedirect() throws IOException {
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
    public void returns200AndHandlesDataParamsIfRequestForPutMethod() throws IOException {
        createRouter("PUT /form HTTP/1.1\n"+
                     "\n"+
                     "My=Data");

        assertEquals("HTTP/1.1 200 OK\n" + "\r\n", router.getResponse());
    }

    @Test
    public void returnsUnauthorizedResponseIfMethodIsLogsWithNoAuthorization() throws IOException {
        createRouter("GET /logs");

        assertEquals("HTTP/1.1 401 Unauthorized\n" + "\r\n", router.getResponse());
        assertEquals("Authentication required", router.getBody());
    }
}
