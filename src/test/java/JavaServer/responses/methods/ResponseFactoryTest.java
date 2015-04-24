package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.DataManager;
import JavaServer.responses.FileManager;
import org.junit.Test;

import java.io.File;
import java.util.HashMap;

import static org.junit.Assert.assertTrue;

public class ResponseFactoryTest {
    private ResponseFactory methodFactory;
    private Request request;
    private FileManager fileManager;
    private File path;
    private DataManager dataManager;


    private Request createRequest(String method, String path) {
        request = new Request(method, path, new HashMap<>(), new HashMap<>());

        return request;
    }

    @Test
    public void returnsGetHandlerIfRequestMethodIsGetAndIsADirectoryPath() {
        path = new File("/Users/test/code/JavaServer/public/");
        fileManager = new FileManager(path);
        dataManager = new DataManager();
        methodFactory = new ResponseFactory(createRequest("GET", "/"), fileManager, dataManager);

        assertTrue(methodFactory.createResponse() instanceof GetResponse);
    }

    @Test
    public void returnsPostHandlerIfRequestMethodIsPost() {
        path = new File("/Users/test/code/JavaServer/public/");
        fileManager = new FileManager(path);
        dataManager = new DataManager();
        methodFactory = new ResponseFactory(createRequest("POST", "/"), fileManager, dataManager);

        assertTrue(methodFactory.createResponse() instanceof PostResponse);
    }

    @Test
    public void returnsPutHandlerIfRequestMethodIsPut() {
        path = new File("/Users/test/code/JavaServer/public/");
        fileManager = new FileManager(path);
        dataManager = new DataManager();
        methodFactory = new ResponseFactory(createRequest("PUT", "/"), fileManager, dataManager);

        assertTrue(methodFactory.createResponse() instanceof PutResponse);
    }

    @Test
    public void returnsOptionsHandlerIfRequestMethodIsOptions() {
        path = new File("/Users/test/code/JavaServer/public/method_options");
        fileManager = new FileManager(path);
        dataManager = new DataManager();
        methodFactory = new ResponseFactory(createRequest("OPTIONS", "/method_options"), fileManager, dataManager);

        assertTrue(methodFactory.createResponse() instanceof OptionsResponse);
    }
}
