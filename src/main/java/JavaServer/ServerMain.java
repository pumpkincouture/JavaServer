package JavaServer;
import JavaServer.configuration.ServerConfiguration;
import JavaServer.runner.HTTPServer;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerMain {

    static public void main(String args[]) throws IOException {

        ServerConfiguration serverConfiguration = new ServerConfiguration(args);
        ServerSocket serverSocket = new ServerSocket(serverConfiguration.getPortNumber());

        HTTPServer server = new HTTPServer(serverSocket, serverConfiguration.getDirectory());
        server.run();
    }
}
