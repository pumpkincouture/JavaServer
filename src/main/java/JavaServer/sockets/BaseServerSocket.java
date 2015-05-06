package JavaServer.sockets;

import java.io.IOException;
import java.net.ServerSocket;

public class BaseServerSocket implements ServerSocketService {
    private ServerSocket serverSocket;

    public BaseServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public SocketService accept() throws IOException {
        return new BaseSocket(serverSocket.accept());
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
