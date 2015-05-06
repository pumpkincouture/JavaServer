package JavaServer;

import JavaServer.mocksockets.MockServerSocket;
import JavaServer.runner.HTTPServer;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HTTPServerTest {

    private HTTPServer httpServer;
    private PrintWriter out;
    private BufferedReader in;
    private int port;
    private String directory;
    private MockServerSocket mockServerSocket;

    @Before
    public void setUp() throws IOException {
        port = 5000;
        directory = "/Users/test/code/JavaServer/public";
        mockServerSocket = new MockServerSocket(port);
    }

    private BufferedReader createFakeInput(String input) throws UnsupportedEncodingException {
        InputStream mockInputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));

        BufferedReader reader = new BufferedReader(new InputStreamReader(mockInputStream));
        return reader;
    }

    private DataOutputStream createDataOutPut() throws UnsupportedEncodingException {
        ByteArrayOutputStream mockInputStream = new ByteArrayOutputStream();

        DataOutputStream out = new DataOutputStream(mockInputStream);
        return out;
    }

    @Test
    public void testServerPortAndDirectory() {
        httpServer = new HTTPServer(mockServerSocket, port, directory);
        assertEquals(5000, port);
        assertEquals("/Users/test/code/JavaServer/public", directory);
    }
}
