package JavaServer.sockets;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BaseSocketTest {
    private BaseSocket baseSocket;
    private Socket socket;

    @Before
    public void setUp() throws Exception {
        baseSocket = new BaseSocket(socket);
    }

    @Test
    public void testThatSocketHasOutPutStream() throws IOException {
        assertTrue(baseSocket.getOutputStream() instanceof OutputStream);
    }

}
