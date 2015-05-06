package JavaServer.helpers;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringMakerTest {
    private Request request;
    private StringMaker stringMaker;

    private void createRequestWithParams(String requestString) {
        RequestParser parser = new RequestParser(requestString);

        request = new Request(parser.getMethod(), parser.getPath(), parser.getHeaders(), parser.getData());
    }

    @Test
    public void paramsAreUpdatedIntoAString() {
        createRequestWithParams("POST /form HTTP/1.1\n"+
                                "\n"+
                                "data=fatcat");

        stringMaker = new StringMaker();

        assertEquals("data=fatcat", stringMaker.turnDataIntoString(request.getData(), "="));
    }

    @Test
    public void paramsAreUpdatedIntoAStringWithASpace() {
        createRequestWithParams("PATCH /form HTTP/1.1\n"+
                                "\n"+
                                "patched content");
        System.out.println(request.getData());

        stringMaker = new StringMaker();

        assertEquals("patched content ", stringMaker.turnDataIntoString(request.getData()));
    }
}
