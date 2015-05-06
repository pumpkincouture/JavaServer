package JavaServer.mocksockets;

import JavaServer.sockets.BaseServerSocket;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BaseServerSocketTest {
    private BaseServerSocket baseServerSocket;
    private ServerSocket serverSocket;

    @Before
    public void setUp() throws Exception {
        serverSocket = new ServerSocket(50034);
    }

    @Test
    public void testIfPortIsSetCorrectlyAndThenClosed() throws IOException {
        baseServerSocket = new BaseServerSocket(serverSocket);
        baseServerSocket.close();
        assertTrue(baseServerSocket.isClosed());
        serverSocket.close();
        assertTrue(serverSocket.isClosed());
        assertEquals(50034, baseServerSocket.getPort());
    }
}
