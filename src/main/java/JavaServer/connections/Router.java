package JavaServer.connections;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.DataManager;
import JavaServer.responses.FileManager;
import JavaServer.responses.ResponseBuilder;
import JavaServer.responses.methods.ResponseFactory;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;

public class Router {
    private String requestString;
    private String directory;
    private RequestParser requestParser;
    private Request request;
    private ResponseBuilder responseBuilder;
    private ResponseFactory responseFactory;
    private FileManager fileManager;
    private DataOutputStream out;
    private DataManager dataManager;

    public Router(String requestString, String directory, DataOutputStream out, DataManager dataManager) {
        this.requestString = requestString;
        this.directory = directory;
        this.out= out;
        this.dataManager = dataManager;
    }

    public void createHandlers() {
        requestParser = new RequestParser(requestString);
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        fileManager = new FileManager(new File(directory + request.getPath()));
        responseFactory = new ResponseFactory(request, fileManager, dataManager);
        responseBuilder = new ResponseBuilder(responseFactory.createResponse());
    }

    public String getResponse() {
        return responseBuilder.getResponseHeaders(request);
    }

    public String getBody() throws IOException {
        return responseBuilder.getBody(request);
    }
}
