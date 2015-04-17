package JavaServer.ResponseManagers;

import JavaServer.RequestManagers.Request;
import JavaServer.RequestManagers.RequestParser;
import JavaServer.ResponseManagers.MethodManagers.ManagerFactory;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ResponseCodeBuilderTest {
    private Request request;
    private RequestParser requestParser;
    private ResponseCodeBuilder responseCodeBuilder;
    private ManagerFactory managerFactory;

    @Test
    public void parseAndStoreMoreThanOnePostParam() {
        requestParser = new RequestParser("GET /form\n"+
                                          "Content-Type: application/x-www-form-url-encoded\n"+
                                          "Host: https://sylwiaolak.com\n"+
                                          "\n"+
                                          "first_name=sylwia\n"+
                                          "last_name=olak\n"+
                                          "age=26");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        managerFactory = new ManagerFactory(request.getMethod());

        responseCodeBuilder = new ResponseCodeBuilder(managerFactory.createMethodManager());

        assertEquals("HTTP/1.1 404 Not Found\r\n" + "Allow: GET,HEAD,POST,OPTIONS,PUT\r\n", responseCodeBuilder.getResponseHeaders(request));
    }
}
