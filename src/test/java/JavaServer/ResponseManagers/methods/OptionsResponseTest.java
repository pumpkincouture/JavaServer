package JavaServer.ResponseManagers.methods;

import JavaServer.RequestManagers.Request;
import JavaServer.RequestManagers.RequestParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OptionsResponseTest {
    private Request request;
    private RequestParser requestParser;
    private Response requestHandler;

    @Test
    public void returns200ResponseIfRequestPathValid() {
        requestParser = new RequestParser("OPTIONS /method_options HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        requestHandler= new OptionsResponse();

        assertEquals("HTTP/1.1 200 OK", requestHandler.manage(request));
    }

    @Test
    public void returns404IfPathNotValid() {
        requestParser = new RequestParser("OPTIONS / HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        requestHandler= new OptionsResponse();

        assertEquals("HTTP/1.1 404 Not Found", requestHandler.manage(request));
    }
}
