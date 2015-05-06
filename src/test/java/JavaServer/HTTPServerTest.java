package JavaServer;

import JavaServer.connections.ConnectionManager;
import JavaServer.connections.MockServerSocket;
import JavaServer.connections.MockSocket;
import JavaServer.requests.Logger;
import JavaServer.runner.HTTPServer;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertTrue;

public class HTTPServerTest {

    private HTTPServer httpServer;
    private PrintWriter out;
    private BufferedReader in;

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
    public void itRespondsToRequest() throws IOException {
        MockSocket mockSocket = new MockSocket();
        String input = String.valueOf(mockSocket.createMockInput());
        String directory = "Users/code/JavaServer/public/";
        Logger logger = new Logger();
        MockServerSocket socket = new MockServerSocket(5000);
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        httpServer = new HTTPServer(socket, directory, logger, executorService);
        httpServer.run();

        assertTrue(socket.isBound());

    }
}
