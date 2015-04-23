package JavaServer.connections;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionManager {
    private PrintWriter out;
    private BufferedReader in;
    private Socket socket;
    private String directory;

    public ConnectionManager(PrintWriter out, BufferedReader in, Socket socket, String directory) {
        this.out = out;
        this.in = in;
        this.socket = socket;
        this.directory = directory;
    }

    public void executeRequest() throws IOException {

        try {
            String requestLines = "";

            do {
                requestLines += (char) in.read();
            } while (in.ready());

            Router router = new Router(requestLines, directory, in);
            router.createHandlers();

            out.flush();
            out.write(router.getResponse());
            out.flush();
            out.write(router.getBody());
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
