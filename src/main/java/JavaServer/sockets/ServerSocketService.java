package JavaServer.sockets;

import java.io.IOException;

public interface ServerSocketService {

    public SocketService accept() throws IOException;
    public void close() throws IOException;
    public boolean isClosed() throws IOException;
    public boolean isConnected();
}
