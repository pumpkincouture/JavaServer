package JavaServer.runner;

import JavaServer.connections.ConnectionManager;
import JavaServer.requests.Logger;
import JavaServer.responses.DataManager;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HTTPServer implements Runnable {
    private ServerSocket serverSocket;
    private String directory;
    private DataManager dataManager;
    private Logger logger;
    private ExecutorService pool;

    public HTTPServer(ServerSocket serverSocket, String directory, DataManager dataManager, Logger logger) {
        this.serverSocket = serverSocket;
        this.directory = directory;
        this.dataManager = dataManager;
        this.logger = logger;
        pool = Executors.newFixedThreadPool(6);
    }

    @Override
    public void run() {
        while (true) {
            Socket clientSocket = null;
            try {
                clientSocket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
            }
            DataOutputStream out = null;
            try {
                out = new DataOutputStream(clientSocket.getOutputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader in = null;
            try {
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
                pool.shutdown();
            }
            ConnectionManager connectionManager = new ConnectionManager(in, clientSocket, directory, dataManager, out, logger);
            pool.execute(connectionManager);
//            connectionManager.run();
        }
    }
}
