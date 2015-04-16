package JavaServer.ResponseManagers.MethodManagers;

import JavaServer.RequestManagers.Request;
import JavaServer.RequestManagers.RequestParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetManagerTest {
    private Request request;
    private RequestParser requestParser;
    private RequestManager requestHandler;

    @Test
    public void returns200ResponseIfRequestValid() {
        requestParser = new RequestParser("GET / HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());

        requestHandler = new GetManager();

        assertEquals("HTTP/1.1 200 OK", requestHandler.manage(request));
    }

    @Test
    public void returns404ResponseIfInvalidPath() {
        requestParser = new RequestParser("GET /whatever HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());

        requestHandler = new GetManager();

        assertEquals("HTTP/1.1 404 Not Found", requestHandler.manage(request));
    }
}
