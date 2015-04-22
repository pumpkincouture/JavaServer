package JavaServer.connections;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MockSocket {
    private InputStream mockIn;
    private String input;
    private OutputStream mockOut;

    public MockSocket(String input) {
        this.input = input;
        mockIn = createMockInput();
        mockOut = new ByteOutputStream();
    }

    private InputStream createMockInput() {
        InputStream mockInputStream = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));

        return mockInputStream;
    }

    public InputStream getMockInput() {
        return mockIn;
    }

    public OutputStream getMockOutput() {
        return mockOut;
    }
}
