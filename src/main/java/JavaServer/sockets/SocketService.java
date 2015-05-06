package JavaServer.sockets;

import java.io.IOException;

public interface SocketService {

    public ServerSocketService accept() throws IOException;
    public void close() throws IOException;
    public boolean isClosed() throws IOException;
    public boolean isConnected();
}
