package JavaServer.runner;

import JavaServer.connection.ConnectionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {
    private ServerSocket serverSocket;
    private String directory;

    public HTTPServer(ServerSocket serverSocket, String directory) {
        this.serverSocket = serverSocket;
        this.directory = directory;
    }

    public void run() throws IOException {
        while (true) {
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            ConnectionManager connectionManager = new ConnectionManager(out, in, clientSocket, directory);
            connectionManager.executeRequest();
        }
    }
}
