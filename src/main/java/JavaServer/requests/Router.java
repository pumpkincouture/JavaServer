package JavaServer.requests;

import JavaServer.responses.ResponseBuilder;
import JavaServer.responses.methods.ResponseFactory;

public class Router {
    private String requestString;
    private RequestParser requestParser;
    private Request request;
    private ResponseBuilder responseBuilder;
    private ResponseFactory responseFactory;

    public Router (String requestString) {
        this.requestString = requestString;
    }

    public void createHandlers() {
        requestParser = new RequestParser(requestString);
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        responseFactory = new ResponseFactory(request);
        responseBuilder = new ResponseBuilder(responseFactory.createResponse());
    }

    public String getResponse() {
        return responseBuilder.getResponseHeaders(request);
    }

    public String getBody() {
        return responseBuilder.getBody(request);
    }
}
