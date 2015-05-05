package JavaServer.connections;

import JavaServer.requests.Logger;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

public class ConnectionManagerTest {
    private ConnectionManager connectionManager;
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
        MockSocket socket = new MockSocket();
        String input = String.valueOf(socket.createMockInput());
        String directory = "Users/code/JavaServer/public/";
        Logger logger = new Logger();

        new ConnectionManager(socket, directory, logger);
        String output = String.valueOf(createDataOutPut());
    }

}
