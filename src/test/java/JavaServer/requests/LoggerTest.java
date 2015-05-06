package JavaServer.requests;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoggerTest {
    private Logger logger;

    @Test
    public void returnsStringThatLoggerHasLogged() {
        logger = new Logger();
        logger.logRequest("GET /log HTTP/1.1");

        assertEquals("GET /log HTTP1.1\r\n", logger.getLogs());
    }


}
