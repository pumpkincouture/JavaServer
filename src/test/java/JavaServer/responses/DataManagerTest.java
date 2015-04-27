package JavaServer.responses;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataManagerTest {
    private RequestParser requestParser;
    private Request request;
    private DataManager dataManager;

    @Test
    public void dataIsTurnedIntoAString() {
        requestParser = new RequestParser("POST /form HTTP/1.1\n"+
                                          "Content-Type: application/x-www-form-url-encoded\n"+
                                          "Host: https://sylwiaolak.com\n"+
                                          "\n"+
                                          "first_name=sylwia\n"+
                                          "last_name=olak\n"+
                                          "age=26");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        dataManager = new DataManager();
        dataManager.updateRequestData(request.getData());

        assertEquals("first_name=sylwialast_name=olakage=26", dataManager.getRequestData());
    }

    @Test
    public void returnsEmptyStringIfNoDataExists() {
        requestParser = new RequestParser("POST /form HTTP/1.1\n"+
                                          "Content-Type: application/x-www-form-url-encoded\n"+
                                          "Host: https://sylwiaolak.com\n"+
                                          "\n");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        dataManager = new DataManager();
        dataManager.updateRequestData(request.getData());

        assertEquals("", dataManager.getRequestData());
    }
}
