package JavaServer.requests;

import JavaServer.responses.FileManager;
import JavaServer.responses.ResponseBuilder;
import JavaServer.responses.methods.ResponseFactory;

import java.io.File;

public class Router {
    private String requestString;
    private String directory;
    private RequestParser requestParser;
    private Request request;
    private ResponseBuilder responseBuilder;
    private ResponseFactory responseFactory;
    private FileManager fileManager;

    public Router(String requestString, String directory) {
        this.requestString = requestString;
        this.directory = directory;
    }

    public void createHandlers() {
        requestParser = new RequestParser(requestString);
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        responseFactory = new ResponseFactory(request);
        fileManager = new FileManager(new File(directory + request.getPath()));
        responseBuilder = new ResponseBuilder(responseFactory.createResponse());
    }

    public String getResponse() {
        return responseBuilder.getResponseHeaders(request);
    }

    public String getBody() {
        return responseBuilder.getBody(request);
    }
}
