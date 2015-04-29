package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.DataManager;
import org.junit.Test;


import static org.junit.Assert.assertEquals;

public class DeleteResponseTest {
    private Request request;
    private RequestParser requestParser;
    private Response requestHandler;
    private DataManager dataManager;

    @Test
    public void returns200ResponseAndUpdatesDataManagerDataToEmptyString() {
        requestParser = new RequestParser("DELETE /form HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        dataManager = new DataManager();

        requestHandler = new DeleteResponse(dataManager, request.getPath());

        assertEquals("HTTP/1.1 200 OK", requestHandler.getCorrectStatus());
        assertEquals("", dataManager.getRequestData());
    }
}
