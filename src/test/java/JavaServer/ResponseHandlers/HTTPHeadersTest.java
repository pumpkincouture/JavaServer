package JavaServer.ResponseHandlers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HTTPHeadersTest {
    private HTTPHeaders headers;

    @Test
    public void returnsOptionsHeader() {
        headers = new HTTPHeaders();

        assertEquals("GET,HEAD,POST,OPTIONS,PUT", headers.getHeaders().get("Allow"));
    }

    @Test
    public void returnsAcceptHeader() {
        headers = new HTTPHeaders();

        assertEquals("text/plain", headers.getHeaders().get("Accept"));
    }

    @Test
    public void returnsHostHeader() {
        headers = new HTTPHeaders();

        assertEquals("http://localhost:5000/", headers.getHeaders().get("Host"));
    }

    @Test
    public void returnsRangesHeader() {
        headers = new HTTPHeaders();

        assertEquals("bytes", headers.getHeaders().get("Accept-Ranges"));
    }
}
