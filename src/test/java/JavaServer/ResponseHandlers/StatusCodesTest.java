package JavaServer.ResponseHandlers;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatusCodesTest {
    private StatusCodes statusCodes;

    @Test
    public void returns404StatusCode() {
        statusCodes = new StatusCodes();

        assertEquals("HTTP/1.1 404 Not Found", statusCodes.getCodes().get("404"));
    }

    @Test
    public void returns200StatusCode() {
        statusCodes = new StatusCodes();

        assertEquals("HTTP/1.1 200 OK", statusCodes.getCodes().get("200"));
    }
}
