package JavaServer.runner;

import JavaServer.mocks.MockServerSocket;
import JavaServer.requests.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertTrue;

public class HTTPServerTest {

    private HTTPServer httpServer;
    private int port;
    private MockServerSocket mockServerSocket;
    private Logger logger;
    private String directory = System.getProperty("user.dir") + "/public";

    @Before
    public void setUp() throws IOException {
        port = 5000;
        mockServerSocket = new MockServerSocket(port);
    }

    @Test
    public void testServerStarting() throws IOException {
        mockServerSocket.isClosed = true;
        httpServer = new HTTPServer(mockServerSocket, port, directory);
        logger = new Logger();
        httpServer.run(logger);

        assertTrue(mockServerSocket.isConnected());
    }
}
