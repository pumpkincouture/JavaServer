package JavaServer.mocksockets;

import JavaServer.sockets.WireServerSocket;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WireServerSocketTest {
    private WireServerSocket wireServerSocket;
    private ServerSocket serverSocket;

    @Before
    public void setUp() throws Exception {
        serverSocket = new ServerSocket(50034);
    }

    @Test
    public void testIfPortIsSetCorrectlyAndThenClosed() throws IOException {
        wireServerSocket = new WireServerSocket(serverSocket);
        wireServerSocket.close();
        assertTrue(wireServerSocket.isClosed());
        serverSocket.close();
        assertTrue(serverSocket.isClosed());
        assertEquals(50034, wireServerSocket.getPort());
    }
}
