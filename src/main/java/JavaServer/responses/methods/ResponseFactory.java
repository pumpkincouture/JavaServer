package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.FileManager;

public class ResponseFactory {
    private Request request;
    private FileManager fileManager;
    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";
    private static final String PUT_METHOD = "PUT";

    public ResponseFactory(Request request, FileManager fileManager) {
        this.request = request;
        this.fileManager = fileManager;
    }

    public Response createResponse() {
        System.out.println(fileManager.convertFilesToPaths());
        if (isRedirectPath()) {
            Response notFoundResponse = new NotFoundResponse();
            return notFoundResponse;
        }
//        else if (isInvalidPath()) {
//            System.out.println("not valid");
//        }
        else if (isOptionsPath()) {
            Response optionsHandler = new OptionsResponse();
            return optionsHandler;
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
        }
        Response gethandler = new GetResponse(fileManager);
        return gethandler;
    }

    private boolean isRedirectPath() {
        return request.getPath().equals("/redirect");
    }

    private boolean isOptionsPath() {
        return request.getPath().equals("/method_options");
    }

    private boolean isInvalidPath() {
        System.out.println(fileManager.convertFilesToPaths().contains(request.getPath()));
        return request.getMethod().equals(GET_METHOD) ;
    }
}
