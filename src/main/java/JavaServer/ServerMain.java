package JavaServer;
import JavaServer.configuration.ServerConfiguration;
import JavaServer.requests.Logger;
import JavaServer.responses.DataManager;
import JavaServer.runner.HTTPServer;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerMain {

    static public void main(String args[]) throws IOException {

        ServerConfiguration serverConfiguration = new ServerConfiguration(args);
        System.out.println("Server is starting....");
        System.out.println("Listening on Port " + serverConfiguration.getPortNumber());
        ServerSocket serverSocket = new ServerSocket(serverConfiguration.getPortNumber());
        DataManager dataManager = new DataManager();
        Logger logger = new Logger();

        HTTPServer server = new HTTPServer(serverSocket, serverConfiguration.getDirectory(), dataManager, logger);
        server.run();
    }
}
