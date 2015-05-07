package JavaServer.connections;

import JavaServer.mocks.MockSocket;
import JavaServer.requests.Logger;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class ConnectionManagerTest {

    private ConnectionManager connectionManager;
    private MockSocket mockSocket;
    private String directory;
    private Logger logger;

    @Before
    public void setUp() throws IOException {
        mockSocket = new MockSocket();
        directory = "/Users/test/code/JavaServer/public/";
        logger = new Logger();
        connectionManager = new ConnectionManager(mockSocket, directory, logger);
    }

    @Test
    public void loopRunsAndSendsOutputThenClosesSocket() {
        connectionManager.run();

        assertTrue(mockSocket.isClosed());
    }
}
