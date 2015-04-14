package JavaServer.RequestManagers;

import JavaServer.MethodManagers.ManagerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class HTTPServer {
    private ServerSocket serverSocket;

    public HTTPServer(ServerSocket serverSocket) throws IOException {
        this.serverSocket = serverSocket;

        while (true) {
            Socket clientSocket = serverSocket.accept();
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            RequestParser requestParser = new RequestParser(in.readLine());
            Request request = new Request(requestParser.getAllRequestAttributes());
            ManagerFactory managerFactory = new ManagerFactory(request);


            out.flush();
            out.write(managerFactory.createMethodHandler().manage(request));
            out.flush();

            in.close();
        }
    }
}
