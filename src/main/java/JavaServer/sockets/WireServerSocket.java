package JavaServer.sockets;

import java.io.IOException;
import java.net.ServerSocket;

public class WireServerSocket implements ServerSocketService {
    private ServerSocket serverSocket;

    public WireServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public SocketService accept() throws IOException {
        return new WireSocket(serverSocket.accept());
    }

    @Override
    public void close() throws IOException {
        serverSocket.close();
    }

    @Override
    public boolean isClosed() throws IOException {
        return serverSocket.isClosed();
    }

    public int getPort() {
        return serverSocket.getLocalPort();
    }

    @Override
    public boolean isConnected() {
        return serverSocket.isBound();
    }
}
