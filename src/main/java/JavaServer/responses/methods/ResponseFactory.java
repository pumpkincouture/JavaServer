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
        if (isRedirectPath()) {
            Response notFoundResponse = new NotFoundResponse();
            return notFoundResponse;
        }
        else if (request.getMethod().equals(GET_METHOD)) {
            Response gethandler = new GetResponse(fileManager);
            return gethandler;
        }
        else if (request.getMethod().equals(POST_METHOD)) {
            Response postHandler = new PostResponse();
            return postHandler;
        } else if (request.getMethod().equals(PUT_METHOD)) {
            Response putHandler = new PutResponse();
            return putHandler;
        } else if (request.getMethod().equals(OPTIONS_METHOD)) {
            Response optionsHandler = new OptionsResponse();
            return optionsHandler;
        }

        Response gethandler = new GetResponse(fileManager);
        return gethandler;
    }

    private boolean isRedirectPath() {
        return request.getPath().equals("/redirect");
    }
}
