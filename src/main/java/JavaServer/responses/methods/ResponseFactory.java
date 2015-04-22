package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.FileManager;

public class ResponseFactory {
    private Request request;
    private FileManager fileManager;
    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";
    private static final String PUT_METHOD = "PUT";
    private static final String OPTIONS_METHOD = "OPTIONS";

    public ResponseFactory(Request request, FileManager fileManager) {
        this.request = request;
        this.fileManager = fileManager;
    }

    public Response createResponse() {
        switch (request.getMethod()) {
            case GET_METHOD:
                Response gethandler = new GetResponse(fileManager);
                return gethandler;
            case POST_METHOD:
                Response postHandler = new PostResponse();
                return postHandler;
            case PUT_METHOD:
                Response putHandler = new PutResponse();
                return putHandler;
            case OPTIONS_METHOD:
                Response optionsHandler = new OptionsResponse();
                return optionsHandler;

        }
        Response gethandler = new GetResponse(fileManager);
        return gethandler;
    }

}
