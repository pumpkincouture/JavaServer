package JavaServer.sockets;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface ServerSocketService {

    public void close() throws IOException;
    public OutputStream getOutPutStream() throws IOException;
    public InputStream getInputStream() throws IOException;
    public boolean isClosed();
}
