package JavaServer.MethodManagers;

import JavaServer.RequestManagers.Request;
import JavaServer.RequestManagers.RequestParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OptionsManagerTest {
    private Request request;
    private RequestParser requestParser;
    private RequestManager requestHandler;

    @Test
    public void returns200ResponseIfRequestPathValid() {
        requestParser = new RequestParser("OPTIONS /method_options HTTP/1.1");
        request = new Request(requestParser.getAllRequestAttributes());
        requestHandler= new OptionsManager();

        assertEquals("HTTP/1.1 200 OK", requestHandler.manage(request));
    }

    @Test
    public void returns404IfPathNotValid() {
        requestParser = new RequestParser("OPTIONS / HTTP/1.1");
        request = new Request(requestParser.getAllRequestAttributes());
        requestHandler= new OptionsManager();

        assertEquals("HTTP/1.1 404 Not Found", requestHandler.manage(request));
    }
}
