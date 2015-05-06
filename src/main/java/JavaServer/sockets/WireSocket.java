package JavaServer.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WireSocket implements ServerSocketService {
    private Socket socket;

    public WireSocket(Socket socket) {
        this.socket = socket;

    }
    @Override
    public void close() throws IOException {
        socket.close();
    }

    @Override
    public OutputStream getOutPutStream() throws IOException {
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
