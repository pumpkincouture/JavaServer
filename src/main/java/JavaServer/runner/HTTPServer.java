package JavaServer.runner;

import JavaServer.connections.ConnectionManager;
import JavaServer.requests.Logger;
import JavaServer.sockets.ServerSocketService;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

public class HTTPServer {
    private ServerSocket serverSocket;
    private String directory;
    private Logger logger;
    private ExecutorService executorService;
    private ServerSocketService serverSocketService;
    private int port;

    public HTTPServer(ServerSocket serverSocket, String directory, Logger logger, ExecutorService executorService) {
        this.serverSocket = serverSocket;
        this.directory = directory;
        this.logger = logger;
        this.executorService = executorService;
    }

    public HTTPServer(ServerSocketService serverSocketService, int port) {
        this.serverSocketService = serverSocketService;
        this.port = port;
    }

    public void run() throws IOException {
        while (!executorService.isShutdown()) {
            Socket clientSocket = serverSocket.accept();
            executorService.execute(new ConnectionManager(clientSocket, directory, logger));
        }
    }
}
