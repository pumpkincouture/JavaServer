package JavaServer.connections;

import JavaServer.requests.Logger;
import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.FileManager;
import JavaServer.responses.ResponseBuilder;
import JavaServer.responses.methods.ResponseFactory;

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
    private Logger logger;

    public Router(String requestString, String directory, DataOutputStream out, Logger logger) {
        this.requestString = requestString;
        this.directory = directory;
        this.out= out;
        this.logger = logger;
    }

    public void createHandlers() {
        requestParser = new RequestParser(requestString);
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        logger.logRequest(request.getRequestLine());
        fileManager = new FileManager(new File(directory + request.getPath()), out);
        responseFactory = new ResponseFactory(request, fileManager, logger);
        responseBuilder = new ResponseBuilder(responseFactory.createResponse());
    }

    public String getResponse() {
        return responseBuilder.getResponseHeaders();
    }

    public String getBody() throws IOException {
        return responseBuilder.getBody();
    }
}
