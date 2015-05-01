package JavaServer.connections;

import JavaServer.requests.Logger;
import JavaServer.responses.DataManager;

import java.io.*;
import java.net.Socket;

public class ConnectionManager implements Runnable {
    private DataOutputStream out;
    private BufferedReader in;
    private Socket socket;
    private String directory;
    private DataManager dataManager;
    private Logger logger;

    public ConnectionManager(BufferedReader in, Socket socket, String directory, DataManager dataManager, DataOutputStream out, Logger logger) {
        this.out = out;
        this.in = in;
        this.socket = socket;
        this.directory = directory;
        this.dataManager = dataManager;
        this.logger = logger;
    }

    @Override
    public void run() {
        try {
            String requestLines = "";

            do {
                requestLines += (char) in.read();
            } while (in.ready());

            Router router = new Router(requestLines, directory, out, dataManager, logger);
            router.createHandlers();

            out.flush();
            out.writeBytes(router.getResponse());
            out.flush();
            out.writeBytes(router.getBody());
            out.flush();

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
