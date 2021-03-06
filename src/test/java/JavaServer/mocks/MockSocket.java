package JavaServer.mocks;

import JavaServer.sockets.SocketService;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MockSocket extends Socket implements SocketService {
    private InputStream mockIn;
    private OutputStream mockOut;
    private boolean closed = false;

    public MockSocket() {
        createMockInput("GET / HTTP/1.1");
        createMockOutPut();
    }

    private void createMockInput(String goodRequest) {
        InputStream mockInputStream = new ByteArrayInputStream(goodRequest.getBytes(StandardCharsets.UTF_8));

        mockIn = mockInputStream;
    }

    private void createMockOutPut() {
        ByteArrayOutputStream mockInputStream = new ByteArrayOutputStream();

        mockOut = new DataOutputStream(mockInputStream);
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

    @Override
    public InputStream getInputStream() {
        return mockIn;
    }
}
