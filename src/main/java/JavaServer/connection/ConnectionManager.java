package JavaServer.connection;

import JavaServer.requests.Router;

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
            String requestString = "";

            while (requestString.length() == 0) {
                requestString += in.readLine();
            }

            Router router = new Router(requestString, directory);
            router.createHandlers();

            System.out.println(router.getResponse());

            out.flush();
            out.write(router.getResponse());
            out.write(router.getBody());
            out.flush();

            in.close();
            out.close();
            socket.close();
        }

        catch (Error err) {
            System.out.println("this is an error message");
        }
    }
}
