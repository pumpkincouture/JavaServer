package JavaServer.sockets;

import JavaServer.mocks.MockSocket;
import org.junit.Before;
import org.junit.Test;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;

public class BaseSocketTest {
    private BaseSocket baseSocket;
    private MockSocket mocksocket = new MockSocket();

    @Before
    public void setUp() throws Exception {
        baseSocket = new BaseSocket(mocksocket);
    }

    @Test
    public void testThatSocketHasInPutStream() throws IOException {
        assertTrue(baseSocket.getInputStream() instanceof InputStream);
    }

    @Test
    public void testThatSocketHasOutPutStream() throws IOException {
        assertTrue(baseSocket.getOutputStream() instanceof DataOutputStream);
    }

    @Test
    public void testThatSocketIsClosed() throws IOException {
        baseSocket.close();

        assertTrue(baseSocket.isClosed());
    }
}
