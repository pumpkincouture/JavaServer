package JavaServer.runner;

import JavaServer.connections.ConnectionManager;
import JavaServer.responses.DataManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {
    private ServerSocket serverSocket;
    private String directory;
    private DataManager dataManager;

    public HTTPServer(ServerSocket serverSocket, String directory, DataManager dataManager) {
        this.serverSocket = serverSocket;
        this.directory = directory;
        this.dataManager = dataManager;
    }

    public void run() throws IOException {
        while (true) {
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            ConnectionManager connectionManager = new ConnectionManager(out, in, clientSocket, directory, dataManager);
            connectionManager.executeRequest();
        }
    }
}
