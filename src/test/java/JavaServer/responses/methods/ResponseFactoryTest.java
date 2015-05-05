package JavaServer.responses.methods;

import JavaServer.requests.Logger;
import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.FileManager;
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
    private FileManager fileManager;
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
        fileManager = new FileManager(path, mockDataStream());
        logger = new Logger();
        methodFactory = new ResponseFactory(createRequestWithNoParams("GET", "/"), fileManager, logger);

        assertTrue(methodFactory.createResponse() instanceof GetResponse);
    }

    @Test
    public void returnsPostResponseIfRequestMethodIsPost() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/");
        fileManager = new FileManager(path, mockDataStream());
        logger = new Logger();
        methodFactory = new ResponseFactory(createRequestWithNoParams("POST", "/"), fileManager, logger);

        assertTrue(methodFactory.createResponse() instanceof PostResponse);
    }

    @Test
    public void returnsPutResponseIfRequestMethodIsPut() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/");
        fileManager = new FileManager(path, mockDataStream());
        logger = new Logger();
        methodFactory = new ResponseFactory(createRequestWithNoParams("PUT", "/"), fileManager, logger);

        assertTrue(methodFactory.createResponse() instanceof PutResponse);
    }

    @Test
    public void returnsOptionsResponseIfRequestMethodIsOptions() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/method_options");
        fileManager = new FileManager(path, mockDataStream());
        logger = new Logger();
        methodFactory = new ResponseFactory(createRequestWithNoParams("OPTIONS", "/method_options"), fileManager, logger);

        assertTrue(methodFactory.createResponse() instanceof OptionsResponse);
    }

    @Test
    public void returnsDeleteResponseIfRequestMethodIsDelete() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/form");
        fileManager = new FileManager(path, mockDataStream());
        logger = new Logger();
        methodFactory = new ResponseFactory(createRequestWithNoParams("DELETE", "/form"), fileManager, logger);

        assertTrue(methodFactory.createResponse() instanceof DeleteResponse);
    }

    @Test
    public void returnsContentResponseIfPathIsToAnExistingFile() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/image.gif");
        fileManager = new FileManager(path, mockDataStream());
        logger = new Logger();
        methodFactory = new ResponseFactory(createRequestWithNoParams("GET", "/image.gif"), fileManager, logger);

        assertTrue(methodFactory.createResponse() instanceof ContentResponse);
    }

    @Test
    public void returnsFourOhFourResponseIfMethodIsNotRecognized() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/patch-content.txt");
        fileManager = new FileManager(path, mockDataStream());
        logger = new Logger();
        methodFactory = new ResponseFactory(createRequestWithNoParams("STREAM", "/patch-content.txt"), fileManager, logger);

        assertTrue(methodFactory.createResponse() instanceof FourOhFourResponse);
    }


    @Test
    public void returnsRedirectResponseIfMethodIsRedirect() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/redirect");
        fileManager = new FileManager(path, mockDataStream());
        logger = new Logger();
        methodFactory = new ResponseFactory(createRequestWithNoParams("GET", "/redirect"), fileManager, logger);

        assertTrue(methodFactory.createResponse() instanceof RedirectResponse);
    }

    @Test
    public void returnsUnAuthorizedResponseIfMethodIsLogsButHasNoAuthorization() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/logs");
        fileManager = new FileManager(path, mockDataStream());
        logger = new Logger();
        methodFactory = new ResponseFactory(createRequestWithNoParams("GET", "/logs"), fileManager, logger);

        assertTrue(methodFactory.createResponse() instanceof UnauthorizedResponse);
    }

    @Test
    public void returnsLogsResponseIfMethodIsLogsAndHasAuthorization() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/logs");
        fileManager = new FileManager(path, mockDataStream());
        logger = new Logger();

        methodFactory = new ResponseFactory(createRequestWithParams("GET /logs HTTP/1.1\n" +
                                                                    "Authorization: Basic YWRtaW46aHVudGVyMg==\n" +
                                                                    "Host: localhost:5000\n" +
                                                                    "\n"), fileManager, logger);

        assertTrue(methodFactory.createResponse() instanceof LogsResponse);
    }

    @Test
    public void returnsFourOhFourResponseIfMethodIsPatchButDoesNotContainEtagAuthorization() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/patch-content.txt");
        fileManager = new FileManager(path, mockDataStream());
        logger = new Logger();

        methodFactory = new ResponseFactory(createRequestWithNoParams("PATCH", "/patch-content.txt"), fileManager, logger);

        assertTrue(methodFactory.createResponse() instanceof FourOhFourResponse);
    }

    @Test
    public void returnsPatchResponseIfMethodIsPatchAndContainsEtagAuthorization() throws UnsupportedEncodingException {
        path = new File("/Users/test/code/JavaServer/public/patch-content.txt");
        fileManager = new FileManager(path, mockDataStream());
        logger = new Logger();

        methodFactory = new ResponseFactory(createRequestWithParams("PATCH /logs HTTP/1.1\n" +
                                                                    "If-Match: dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec\n" +
                                                                    "Host: localhost:5000\n" +
                                                                    "\n"), fileManager, logger);

        assertTrue(methodFactory.createResponse() instanceof PatchResponse);
    }
}
