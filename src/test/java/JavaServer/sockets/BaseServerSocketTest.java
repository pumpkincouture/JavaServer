package JavaServer.sockets;

import JavaServer.mocks.MockSocket;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.ServerSocket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BaseServerSocketTest {
    private BaseServerSocket baseServerSocket;
    private ServerSocket serverSocket;
    private MockSocket mockSocket;

    @Test
    public void testIfPortIsSetCorrectlyAndThenClosed() throws IOException {
        serverSocket = new ServerSocket(50045);
        baseServerSocket = new BaseServerSocket(serverSocket);
        assertTrue(baseServerSocket.isConnected());
        baseServerSocket.close();
        assertTrue(baseServerSocket.isClosed());
        serverSocket.close();
        assertTrue(serverSocket.isClosed());
        assertEquals(50045, baseServerSocket.getPort());
    }

    @Test
    public void acceptReturnsInstanceOfBaseSocket() throws IOException {
        serverSocket = new ServerSocket(50023);
        baseServerSocket = new BaseServerSocket(serverSocket);
        mockSocket = new MockSocket();

        assertTrue(baseServerSocket.accept(mockSocket) instanceof BaseSocket);
        baseServerSocket.close();
        serverSocket.close();
    }
}
