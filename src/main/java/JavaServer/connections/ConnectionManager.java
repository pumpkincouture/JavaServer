package JavaServer.connections;

import JavaServer.responses.DataManager;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

public class ConnectionManager {
    private DataOutputStream out;
    private BufferedReader in;
    private Socket socket;
    private String directory;
    private DataManager dataManager;

    public ConnectionManager(BufferedReader in, Socket socket, String directory, DataManager dataManager, DataOutputStream out) {
        this.out = out;
        this.in = in;
        this.socket = socket;
        this.directory = directory;
        this.dataManager = dataManager;
    }

    public void executeRequest() throws IOException {

        try {
            String requestLines = "";

            do {
                requestLines += (char) in.read();
            } while (in.ready());

            Router router = new Router(requestLines, directory, in, dataManager);
            router.createHandlers();

            byte[] bytes = router.getResponse().getBytes(Charset.forName("UTF-8"));


            out.flush();
            out.writeBytes(router.getResponse());
            out.flush();
            out.writeBytes(router.getBody());
            out.flush();

            in.close();
            out.close();
            socket.close();
        }

        catch (Error err) {
            System.out.println("Something went wrong!");
        }
    }
}
