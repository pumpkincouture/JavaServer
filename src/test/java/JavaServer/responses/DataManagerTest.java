package JavaServer.responses;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DataManagerTest {
    private Request request;
    private DataManager dataManager;

    private Request createRequestWithParams(String requestString) {
        RequestParser parser = new RequestParser(requestString);

        request = new Request(parser.getMethod(), parser.getPath(), parser.getHeaders(), parser.getData());

        return request;
    }

    @Test
    public void dataIsTurnedIntoAString() {
        dataManager = new DataManager();
        request = createRequestWithParams("POST /form HTTP/1.1\n"+
                                          "Content-Type: application/x-www-form-url-encoded\n"+
                                          "Host: https://sylwiaolak.com\n"+
                                          "\n"+
                                          "first_name=sylwia\n"+
                                          "last_name=olak\n"+
                                          "age=26");

        dataManager.updateRequestData(request.getData(), "=");

        assertEquals("first_name=sylwialast_name=olakage=26", dataManager.getRequestData());
    }

    @Test
    public void returnsEmptyStringIfNoDataExists() {
        request = createRequestWithParams("POST /form HTTP/1.1\n"+
                                          "Content-Type: application/x-www-form-url-encoded\n"+
                                          "Host: https://sylwiaolak.com\n"+
                                          "\n");
        dataManager = new DataManager();
        dataManager.updateRequestData(request.getData(), "=");

        assertEquals("", dataManager.getRequestData());
    }

    @Test
    public void returnsStringWithParamsInBody() {
        request = createRequestWithParams("GET /parameters/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1");
        dataManager = new DataManager();
        dataManager.updateRequestData(request.getData(), "=");

        assertEquals("variable_1=Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?variable_2=stuff", dataManager.getRequestData());
    }
}
