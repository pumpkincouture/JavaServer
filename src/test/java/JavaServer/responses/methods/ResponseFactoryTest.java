package JavaServer.responses.methods;

import JavaServer.requests.Logger;
import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.FileAdmin;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertTrue;

public class ResponseFactoryTest {
    private ResponseFactory responseFactory;
    private Request request;
    private FileAdmin fileAdmin;
    private File path;
    private Logger logger;
    private String userDirectory = System.getProperty("user.dir") + "/public";

    private void createResponseFactory(String filePath, String requestLine) throws UnsupportedEncodingException {
        RequestParser parser = new RequestParser(requestLine);
        request = new Request(parser.getMethod(), parser.getPath(), parser.getHeaders(), parser.getData());
        path = new File(userDirectory + filePath);
        fileAdmin = new FileAdmin(path, mockDataStream());
        logger = new Logger();
        responseFactory = new ResponseFactory(request, fileAdmin, logger);
    }

    private DataOutputStream mockDataStream() throws UnsupportedEncodingException {
        ByteArrayOutputStream mockInputStream = new ByteArrayOutputStream();

        return new DataOutputStream(mockInputStream);
    }

    @Test
    public void returnsGetResponseIfRequestMethodIsGetAndIsADirectoryPath() throws UnsupportedEncodingException {
        createResponseFactory("/", "GET / HTTP/1.1");

        assertTrue(responseFactory.createResponse() instanceof GetResponse);
    }

    @Test
    public void returnsPostResponseIfRequestMethodIsPost() throws UnsupportedEncodingException {
        createResponseFactory("/form", "POST /form HTTP/1.1\n"+
                                       "Content-Type: application/x-www-form-url-encoded\n"+
                                       "Host: https://sylwiaolak.com\n"+
                                       "\n"+
                                       "data=example");

        assertTrue(responseFactory.createResponse() instanceof PostResponse);
    }

    @Test
    public void returnsPutResponseIfRequestMethodIsPut() throws UnsupportedEncodingException {
        createResponseFactory("/form", "PUT /form HTTP/1.1\n"+
                                       "Content-Type: application/x-www-form-url-encoded\n"+
                                       "\n"+
                                       "data=updated");

        assertTrue(responseFactory.createResponse() instanceof PutResponse);
    }

    @Test
    public void returnsOptionsResponseIfRequestMethodIsOptions() throws UnsupportedEncodingException {
        createResponseFactory("/method_options", "OPTIONS /method_options HTTP/1.1");

        assertTrue(responseFactory.createResponse() instanceof OptionsResponse);
    }

    @Test
    public void returnsDeleteResponseIfRequestMethodIsDelete() throws UnsupportedEncodingException {
        createResponseFactory("/form", "DELETE /form HTTP/1.1");

        assertTrue(responseFactory.createResponse() instanceof DeleteResponse);
    }

    @Test
    public void returnsContentResponseIfPathIsToAnExistingFile() throws UnsupportedEncodingException {
        createResponseFactory("/image.gif", "GET /image.gif HTTP/1.1");

        assertTrue(responseFactory.createResponse() instanceof ContentResponse);
    }

    @Test
    public void returnsFourOhFourResponseIfMethodIsNotRecognized() throws UnsupportedEncodingException {
        createResponseFactory("/patch-content.txt", "STREAM /patch-content.txt HTTP/1.1");

        assertTrue(responseFactory.createResponse() instanceof FourOhFourResponse);
    }

    @Test
    public void returnsRedirectResponseIfMethodIsRedirect() throws UnsupportedEncodingException {
        createResponseFactory("/redirect", "GET /redirect HTTP/1.1");

        assertTrue(responseFactory.createResponse() instanceof RedirectResponse);
    }

    @Test
    public void returnsUnAuthorizedResponseIfMethodIsLogsButHasNoAuthorization() throws UnsupportedEncodingException {
        createResponseFactory("/logs", "GET /logs HTTP/1.1");

        assertTrue(responseFactory.createResponse() instanceof UnauthorizedResponse);
    }

    @Test
    public void returnsLogsResponseIfMethodIsLogsAndHasAuthorization() throws UnsupportedEncodingException {
        createResponseFactory("/logs", "GET /logs HTTP/1.1\n" +
                                       "Authorization: Basic YWRtaW46aHVudGVyMg==\n" +
                                       "Host: localhost:5000\n" +
                                       "\n");

        assertTrue(responseFactory.createResponse() instanceof LogsResponse);
    }

    @Test
    public void returnsPatchResponseIfMethodIsPatchButDoesNotContainEtagAuthorization() throws UnsupportedEncodingException {
        createResponseFactory("/patch-content.txt", "PATCH /patch-content.txt HTTP/1.1");

        assertTrue(responseFactory.createResponse() instanceof PatchResponse);
    }

    @Test
    public void returnsPatchResponseIfMethodIsPatchAndContainsEtagAuthorization() throws UnsupportedEncodingException {
        createResponseFactory("/patch-content.txt", "PATCH /logs HTTP/1.1\n" +
                                                    "If-Match: dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec\n" +
                                                    "Host: localhost:5000\n" +
                                                    "\n");

        assertTrue(responseFactory.createResponse() instanceof PatchResponse);
    }

    @Test
    public void returnsParamResponseIfPathIsParameters() throws UnsupportedEncodingException {
        createResponseFactory("/parameters", "GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1");

        assertTrue(responseFactory.createResponse() instanceof ParamResponse);
    }

    @Test
    public void returnsGetResponseIfPathIsForm() throws UnsupportedEncodingException {
        createResponseFactory("/form", "GET /form HTTP/1.1");

        assertTrue(responseFactory.createResponse() instanceof GetResponse);
    }
}
