package JavaServer.connections;

import JavaServer.requests.Logger;
import java.io.*;
import java.net.Socket;

public class ConnectionManager implements Runnable {
    private Socket socket;
    private String directory;
    private Logger logger;
    private BufferedReader bufferedReader;
    private DataOutputStream dataOutputStream;


    public ConnectionManager(Socket socket, String directory, Logger logger) throws IOException {
        this.socket = socket;
        this.directory = directory;
        this.logger = logger;
        bufferedReader = getSocketInputStream();
        dataOutputStream = getSocketOutPutStream();
    }

    @Override
    public void run() {
        try {
            String requestLines = "";

            do {
                requestLines += (char) bufferedReader.read();
            } while (bufferedReader.ready());

            Router router = new Router(requestLines, directory, dataOutputStream, logger);
            router.createHandlers();

            dataOutputStream.flush();
            dataOutputStream.writeBytes(router.getResponse());
            dataOutputStream.flush();
            dataOutputStream.writeBytes(router.getBody());
            dataOutputStream.flush();

            bufferedReader.close();
            dataOutputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private BufferedReader getSocketInputStream() throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    private DataOutputStream getSocketOutPutStream() throws IOException {
        return new DataOutputStream(socket.getOutputStream());
    }
}
