package JavaServer;
import JavaServer.configuration.ServerConfiguration;
import JavaServer.requests.Logger;
import JavaServer.runner.HTTPServer;
import JavaServer.sockets.WireServerSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerMain {

    static public void main(String args[]) throws IOException {
        ServerConfiguration serverConfiguration = new ServerConfiguration(args);

        System.out.println("Server is starting....");
        System.out.println("Listening on Port " + serverConfiguration.getPortNumber());

        try {
            ServerSocket serverSocket = new ServerSocket(serverConfiguration.getPortNumber());
            Logger logger = new Logger();

            ExecutorService executorService = Executors.newFixedThreadPool(4);

            HTTPServer server = new HTTPServer(new WireServerSocket(serverSocket), serverConfiguration.getDirectory(), logger, executorService);
            server.run();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }
    }
}
