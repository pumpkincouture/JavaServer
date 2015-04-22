package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.FileManager;

import java.util.ArrayList;
import java.util.List;

public class ResponseFactory {
    private Request request;
    private FileManager fileManager;
    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";
    private static final String PUT_METHOD = "PUT";
    private List<String> allValidPaths = new ArrayList<>();

    public ResponseFactory(Request request, FileManager fileManager) {
        this.request = request;
        this.fileManager = fileManager;
        allValidPaths = fileManager.convertFilesToPaths();
    }

    public Response createResponse() {
        getValidPaths();
        if  (isInvalidPath()) {
            Response fourOhFourResponse = new FourOhFourResponse();
            return fourOhFourResponse;
        }
        else if (isRedirectPath()) {
            Response notFoundResponse = new RedirectResponse();
            return notFoundResponse;
        }
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
        return request.getMethod().equals(GET_METHOD) && !allValidPaths.contains(request.getPath());
    }

    private void getValidPaths() {
        allValidPaths.add("/");
        allValidPaths.add("/method_options");
        allValidPaths.add("/redirect");
        allValidPaths.add("/form");
    }
}
