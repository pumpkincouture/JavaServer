package JavaServer.mocksockets;


import JavaServer.sockets.SocketService;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MockSocket extends Socket implements SocketService {
    private InputStream mockIn;
    private OutputStream mockOut;
    private boolean closed = false;
    String statusLine = "HTTP/1.1 200 OK\r\n";
    private InputStream input;
    private OutputStream output;

    public MockSocket() {
        mockIn = createMockInput();
    }

    public InputStream createMockInput() {
        String exampleInput = "GET / HTTP/1.1";
        InputStream mockInputStream = new ByteArrayInputStream(exampleInput.getBytes(StandardCharsets.UTF_8));

        return mockInputStream;
    }

    public MockSocket(String host, int port, byte[] in) throws IOException {
        this.input = new ByteArrayInputStream(in);
        this.output = new ByteArrayOutputStream();
    }

    @Override
    public void close(){
        this.closed = true;
    }

    @Override
    public boolean isClosed() {
        return this.closed;
    }

    @Override
    public OutputStream getOutputStream() {
        return mockOut;
    }

    public String getOutputMock() {
        return statusLine;
    }

    @Override
    public InputStream getInputStream() {
        return mockIn;
    }

    public InputStream getMockInput() {
        return mockIn;
    }

    public OutputStream getMockOutput() {
        return mockOut;
    }
}
