package JavaServer;

import JavaServer.RequestHandlers.Request;
import JavaServer.RequestHandlers.RequestParser;
import JavaServer.MethodHandlers.ManagerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    static public void main (String args[]) throws IOException {

        try {
            ServerSocket server = new ServerSocket(5000);


            while (true) {
                Socket clientSocket = server.accept();
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                RequestParser requestParser = new RequestParser(in.readLine());
                Request request = new Request(requestParser.getAllRequestAttributes());
                ManagerFactory handlerFactory = new ManagerFactory(request);

                while (in.readLine() != null) {
                    System.out.println(in.readLine());
                }

                out.flush();
                out.write(handlerFactory.createMethodHandler().handle(request));
                out.flush();

                in.close();
            }
        }
        catch (Exception err) {
                System.out.println(err);
                err.printStackTrace();
        }

        finally {
            System.out.println("You're in the finally block");
        }
    }
}
