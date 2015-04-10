package JavaServer;

import JavaServer.ResponseHandlers.StatusCodes;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatusCodesTest {
    private StatusCodes statusCodes;

    @Test
    public void returns404StatusCode() {
        statusCodes = new StatusCodes();

        assertEquals("Not Found", statusCodes.getCodes().get("404"));
    }

    @Test
    public void returns302StatusCode() {
        statusCodes = new StatusCodes();

        assertEquals("Moved Temporarily", statusCodes.getCodes().get("302"));
    }

    @Test
    public void returns405StatusCode() {
        statusCodes = new StatusCodes();

        assertEquals("Method Not Allowed", statusCodes.getCodes().get("405"));
    }

    @Test
    public void returns200StatusCode() {
        statusCodes = new StatusCodes();

        assertEquals("OK", statusCodes.getCodes().get("200"));
    }
}
