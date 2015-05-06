package JavaServer.mocksockets;

import JavaServer.sockets.ServerSocketService;
import JavaServer.sockets.SocketService;

import java.io.IOException;

public class MockServerSocket implements ServerSocketService {
    private SocketService socket;
    private int port;
    private boolean isConnected = true;
    public boolean isClosed = false;

    public MockServerSocket(int port) throws IOException {
        this.socket = new MockSocket();
        this.port = port;
    }

    @Override
    public boolean isConnected() {
        return isConnected;
    }

    public int getPortNumber() {
        return port;
    }

    @Override
    public SocketService accept() {
        this.isClosed = false;
        this.isConnected = true;
        return this.socket;
    }

    @Override
    public void close() throws IOException {
        this.isClosed = true;
    }

    @Override
    public boolean isClosed() throws IOException {
        return isClosed;
    }

    public String getOutPut() throws IOException {
        return socket.getOutputStream().toString();
    }
}
