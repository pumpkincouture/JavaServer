package JavaServer.runner;

import JavaServer.connections.ConnectionManager;
import JavaServer.responses.DataManager;

import java.io.*;
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
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            ConnectionManager connectionManager = new ConnectionManager(in, clientSocket, directory, dataManager, out);
            connectionManager.executeRequest();
        }
    }
}
