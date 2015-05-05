package JavaServer.runner;

import JavaServer.connections.ConnectionManager;
import JavaServer.requests.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class HTTPServer {
    private ServerSocket serverSocket;
    private String directory;
    private Logger logger;
    private ExecutorService executorService;

    public HTTPServer(ServerSocket serverSocket, String directory, Logger logger, ExecutorService executorService) {
        this.serverSocket = serverSocket;
        this.directory = directory;
        this.logger = logger;
        this.executorService = executorService;
    }

    public void run() throws IOException {
        while (!executorService.isShutdown()) {
            Socket clientSocket = serverSocket.accept();
            executorService.execute(new ConnectionManager(clientSocket, directory, logger));
        }
    }
}
