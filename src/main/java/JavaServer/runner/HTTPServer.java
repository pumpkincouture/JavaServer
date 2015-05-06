package JavaServer.runner;

import JavaServer.connections.ConnectionManager;
import JavaServer.requests.Logger;
import JavaServer.sockets.ServerSocketService;
import JavaServer.sockets.SocketService;

import java.io.*;
import java.util.concurrent.ExecutorService;

public class HTTPServer {
    private String directory;
    private Logger logger;
    private ExecutorService executorService;
    private ServerSocketService serverSocketService;
    private int port;

    public HTTPServer(ServerSocketService serverSocketService, String directory, Logger logger, ExecutorService executorService) {
        this.serverSocketService = serverSocketService;
        this.directory = directory;
        this.logger = logger;
        this.executorService = executorService;
    }

    public HTTPServer(ServerSocketService serverSocketService, int port, String directory) {
        this.serverSocketService = serverSocketService;
        this.port = port;
        this.directory = directory;
    }

    public void run() throws IOException {
        while (!executorService.isShutdown()) {
            SocketService clientSocket = serverSocketService.accept();
            executorService.execute(new ConnectionManager(clientSocket, directory, logger));
        }
    }

    public void run(Logger logger) throws IOException {
        SocketService clientSocket = serverSocketService.accept();
        new ConnectionManager(clientSocket, directory, logger);
    }
}
