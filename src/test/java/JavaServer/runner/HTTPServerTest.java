package JavaServer.runner;

import JavaServer.mocks.MockServerSocket;
import JavaServer.requests.Logger;
import JavaServer.runner.HTTPServer;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.concurrent.ExecutorService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HTTPServerTest {

    private HTTPServer httpServer;
    private int port;
    private String directory;
    private MockServerSocket mockServerSocket;
    private ExecutorService executorService;
    private Logger logger;


    @Before
    public void setUp() throws IOException {
        port = 5000;
        directory = "/Users/test/code/JavaServer/public";
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
