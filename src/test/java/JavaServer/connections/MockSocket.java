package JavaServer.connections;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MockSocket extends Socket {
    private InputStream mockIn;
    private OutputStream mockOut;

    public MockSocket() {
        mockIn = createMockInput();
    }

    public InputStream createMockInput() {
        String exampleInput = "GET / HTTP/1.1";
        InputStream mockInputStream = new ByteArrayInputStream(exampleInput.getBytes(StandardCharsets.UTF_8));

        return mockInputStream;
    }

    public InputStream getMockInput() {
        return mockIn;
    }

    public OutputStream getMockOutput() {
        return mockOut;
    }
}
