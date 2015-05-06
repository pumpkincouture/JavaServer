package JavaServer.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class BaseSocket implements SocketService {
    private Socket socket;

    public BaseSocket(Socket socket) {
        this.socket = socket;

    }
    @Override
    public void close() throws IOException {
        socket.close();
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return socket.getOutputStream();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return socket.getInputStream();
    }

    @Override
    public boolean isClosed() {
        return socket.isClosed();
    }
}
