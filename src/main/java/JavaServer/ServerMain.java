package JavaServer;

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
                Request request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getMethod());

                System.out.println(request.getMethod() + " this is the method");
                System.out.println(request.getHTTP() + " this is the http version");
                System.out.println(request.getPath() + " this is the path");
                in.close();
            }

        }
        catch (Exception err) {
                System.out.println(err);
                err.printStackTrace();
        }
    }
}
