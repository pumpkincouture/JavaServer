package JavaServer;

import JavaServer.RequestHandlers.Request;
import JavaServer.RequestHandlers.RequestParser;
import JavaServer.ResponseHandlers.HandlerFactory;

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
                HandlerFactory handlerFactory = new HandlerFactory(request);

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
    }
}
