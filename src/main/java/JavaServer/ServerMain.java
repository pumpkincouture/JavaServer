package JavaServer;
import JavaServer.configuration.ServerConfiguration;
import JavaServer.requests.HTTPServer;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class ServerMain {

    static public void main(String args[]) throws IOException {

        ServerConfiguration serverConfiguration = new ServerConfiguration(args);
        ServerSocket serverSocket = new ServerSocket(serverConfiguration.getPortNumber());

        System.out.println(serverConfiguration.getDirectory());

        List<String> results = new ArrayList<>();

        File[] files = new File(serverConfiguration.getDirectory()).listFiles();


        HTTPServer server = new HTTPServer(serverSocket, serverConfiguration.getDirectory());
        server.run();
    }
}
