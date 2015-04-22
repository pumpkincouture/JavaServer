package JavaServer.connections;

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
}
