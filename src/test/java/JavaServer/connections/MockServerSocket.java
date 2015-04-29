package JavaServer.connections;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MockServerSocket extends ServerSocket{
    private Socket socket;
    private int port;
    private boolean isConnected = true;
    private boolean isClosed = false;

    public MockServerSocket(int port) throws IOException {
        this.socket = new MockSocket();
        this.port = port;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public int getPortNumber() {
        return port;
    }

    public Socket accept() {
        return socket;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public String getOutPut() throws IOException {
        return socket.getOutputStream().toString();
    }
}
