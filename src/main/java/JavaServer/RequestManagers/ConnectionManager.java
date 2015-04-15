package JavaServer.RequestManagers;

import JavaServer.MethodManagers.ManagerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ConnectionManager {
    private PrintWriter out;
    private BufferedReader in;
    private Socket socket;

    public ConnectionManager(PrintWriter out, BufferedReader in, Socket socket) {
        this.out = out;
        this.in = in;
        this.socket = socket;
    }

    public void executeRequest() throws IOException {

        try {
            String requestString = "";

            while (requestString.length() == 0) {
                requestString += in.readLine();
            }

            RequestParser requestParser = new RequestParser(requestString);
            Request request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
            ManagerFactory managerFactory = new ManagerFactory(request.getMethod());

            out.flush();
            out.write(managerFactory.createMethodHandler().manage(request));
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
