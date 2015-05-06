package JavaServer.responses.methods;

import JavaServer.requests.Logger;
import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.FileWriter;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class ResponseFactoryTest {
    private ResponseFactory methodFactory;
    private Request request;
    private FileWriter fileWriter;
    private File path;
    private Logger logger;


    private Request createRequestWithNoParams(String method, String path) {
        request = new Request(method, path, new HashMap<>(), new HashMap<>());

        return request;
    }

    private Request createRequestWithParams(String requestString) {
        RequestParser parser = new RequestParser(requestString);

        request = new Request(parser.getMethod(), parser.getPath(), parser.getHeaders(), parser.getData());

        return request;
    }

    private DataOutputStream mockDataStream() throws UnsupportedEncodingException {
        ByteArrayOutputStream mockInputStream = new ByteArrayOutputStream();

        DataOutputStream out = new DataOutputStream(mockInputStream);
        return out;
    }

    @Test
    public void returnsGetResponseIfRequestMethodIsGetAndIsADirectoryPath() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/");
        fileWriter = new FileWriter(path, mockDataStream());
        logger = new Logger();
        methodFactory = new ResponseFactory(createRequestWithNoParams("GET", "/"), fileWriter, logger);

        assertTrue(methodFactory.createResponse() instanceof GetResponse);
    }

    @Test
    public void returnsPostResponseIfRequestMethodIsPost() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/");
        fileWriter = new FileWriter(path, mockDataStream());
        logger = new Logger();
        methodFactory = new ResponseFactory(createRequestWithNoParams("POST", "/"), fileWriter, logger);

        assertTrue(methodFactory.createResponse() instanceof PostResponse);
    }

    @Test
    public void returnsPutResponseIfRequestMethodIsPut() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/");
        fileWriter = new FileWriter(path, mockDataStream());
        logger = new Logger();
        methodFactory = new ResponseFactory(createRequestWithNoParams("PUT", "/"), fileWriter, logger);

        assertTrue(methodFactory.createResponse() instanceof PutResponse);
    }

    @Test
    public void returnsOptionsResponseIfRequestMethodIsOptions() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/method_options");
        fileWriter = new FileWriter(path, mockDataStream());
        logger = new Logger();
        methodFactory = new ResponseFactory(createRequestWithNoParams("OPTIONS", "/method_options"), fileWriter, logger);

        assertTrue(methodFactory.createResponse() instanceof OptionsResponse);
    }

    @Test
    public void returnsDeleteResponseIfRequestMethodIsDelete() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/form");
        fileWriter = new FileWriter(path, mockDataStream());
        logger = new Logger();
        methodFactory = new ResponseFactory(createRequestWithNoParams("DELETE", "/form"), fileWriter, logger);

        assertTrue(methodFactory.createResponse() instanceof DeleteResponse);
    }

    @Test
    public void returnsContentResponseIfPathIsToAnExistingFile() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/image.gif");
        fileWriter = new FileWriter(path, mockDataStream());
        logger = new Logger();
        methodFactory = new ResponseFactory(createRequestWithNoParams("GET", "/image.gif"), fileWriter, logger);

        assertTrue(methodFactory.createResponse() instanceof ContentResponse);
    }

    @Test
    public void returnsFourOhFourResponseIfMethodIsNotRecognized() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/patch-content.txt");
        fileWriter = new FileWriter(path, mockDataStream());
        logger = new Logger();
        methodFactory = new ResponseFactory(createRequestWithNoParams("STREAM", "/patch-content.txt"), fileWriter, logger);

        assertTrue(methodFactory.createResponse() instanceof FourOhFourResponse);
    }

    @Test
    public void returnsRedirectResponseIfMethodIsRedirect() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/redirect");
        fileWriter = new FileWriter(path, mockDataStream());
        logger = new Logger();
        methodFactory = new ResponseFactory(createRequestWithNoParams("GET", "/redirect"), fileWriter, logger);

        assertTrue(methodFactory.createResponse() instanceof RedirectResponse);
    }

    @Test
    public void returnsUnAuthorizedResponseIfMethodIsLogsButHasNoAuthorization() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/logs");
        fileWriter = new FileWriter(path, mockDataStream());
        logger = new Logger();
        methodFactory = new ResponseFactory(createRequestWithNoParams("GET", "/logs"), fileWriter, logger);

        assertTrue(methodFactory.createResponse() instanceof UnauthorizedResponse);
    }

    @Test
    public void returnsLogsResponseIfMethodIsLogsAndHasAuthorization() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/logs");
        fileWriter = new FileWriter(path, mockDataStream());
        logger = new Logger();

        methodFactory = new ResponseFactory(createRequestWithParams("GET /logs HTTP/1.1\n" +
                                                                    "Authorization: Basic YWRtaW46aHVudGVyMg==\n" +
                                                                    "Host: localhost:5000\n" +
                                                                    "\n"), fileWriter, logger);

        assertTrue(methodFactory.createResponse() instanceof LogsResponse);
    }

    @Test
    public void returnsPatchResponseIfMethodIsPatchButDoesNotContainEtagAuthorization() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/patch-content.txt");
        fileWriter = new FileWriter(path, mockDataStream());
        logger = new Logger();

        methodFactory = new ResponseFactory(createRequestWithNoParams("PATCH", "/patch-content.txt"), fileWriter, logger);

        assertTrue(methodFactory.createResponse() instanceof PatchResponse);
    }

    @Test
    public void returnsPatchResponseIfMethodIsPatchAndContainsEtagAuthorization() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/patch-content.txt");
        fileWriter = new FileWriter(path, mockDataStream());
        logger = new Logger();

        methodFactory = new ResponseFactory(createRequestWithParams("PATCH /logs HTTP/1.1\n" +
                                                                    "If-Match: dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec\n" +
                                                                    "Host: localhost:5000\n" +
                                                                    "\n"), fileWriter, logger);

        assertTrue(methodFactory.createResponse() instanceof PatchResponse);
    }

    @Test
    public void returnsParamResponseIfPathIsParameters() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/parameters");
        fileWriter = new FileWriter(path, mockDataStream());
        logger = new Logger();

        methodFactory = new ResponseFactory(createRequestWithParams("GET /parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1"), fileWriter, logger);

        assertTrue(methodFactory.createResponse() instanceof ParamResponse);
    }
}
