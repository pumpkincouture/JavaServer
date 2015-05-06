package JavaServer.helpers;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RangeFinderTest {
    private Request request;
    private RangeFinder rangeFinder;

    private void createRequestWithHeaders(String requestString) {
        RequestParser parser = new RequestParser(requestString);

        request = new Request(parser.getMethod(), parser.getPath(), parser.getHeaders(), parser.getData());
    }

    @Test
    public void rangeHasAStartAndFinish() {
        createRequestWithHeaders("GET /partial_content.txt HTTP/1.1\n" +
                                 "Range: bytes=0-4 \n" +
                                 "Host: localhost:5000\n" +
                                 "\n");

        rangeFinder = new RangeFinder(request.getHeaders());
        assertTrue(rangeFinder.hasEnd());
        assertEquals(0, rangeFinder.getStart());
        assertEquals(4, rangeFinder.getEnd());
    }

    @Test
    public void rangeHasNoStartButHasFinish() {
        createRequestWithHeaders("GET /partial_content.txt HTTP/1.1\n" +
                                 "Range: bytes=-6 \n" +
                                 "Host: localhost:5000\n" +
                                 "\n");

        rangeFinder = new RangeFinder(request.getHeaders());
        assertFalse(rangeFinder.hasStart());
        assertTrue(rangeFinder.hasEnd());
    }

    @Test
    public void rangeHasAStartButHasNoEnd() {
        createRequestWithHeaders("GET /partial_content.txt HTTP/1.1\n" +
                                 "Range: bytes=4- \n" +
                                 "Host: localhost:5000\n" +
                                 "\n");

        rangeFinder = new RangeFinder(request.getHeaders());
        assertFalse(rangeFinder.hasEnd());
        assertTrue(rangeFinder.hasStart());
    }
}
