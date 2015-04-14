package JavaServer;
import JavaServer.RequestManagers.HTTPServer;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerMain {

    static public void main(String args[]) throws IOException {

        ServerSocket serverSocket = new ServerSocket(5000);
        HTTPServer server = new HTTPServer(serverSocket);
    }
}
