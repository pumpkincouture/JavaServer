package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.DataManager;
import JavaServer.responses.FileManager;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;

public class GetResponseTest {
    private Request request;
    private RequestParser requestParser;
    private Response requestHandler;
    private FileManager fileManager;
    private File path;
    private DataManager dataManager;

    @Test
    public void returns200ResponseIfRequestValid() {
        requestParser = new RequestParser("GET / HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/image.gif");
        fileManager = new FileManager(path);
        dataManager = new DataManager();

        requestHandler = new GetResponse(fileManager, dataManager);

        assertEquals("HTTP/1.1 200 OK", requestHandler.getCorrectStatus(request));
    }

    @Test
    public void returnsBlankHeaderIfPathIsNotForMethodOptions() {
        requestParser = new RequestParser("GET / HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/image.gif");
        fileManager = new FileManager(path);
        dataManager = new DataManager();

        requestHandler = new GetResponse(fileManager, dataManager);

        assertEquals("", requestHandler.getCorrectHeaders(request));
    }
}
