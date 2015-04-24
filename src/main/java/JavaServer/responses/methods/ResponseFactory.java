package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.DataManager;
import JavaServer.responses.FileManager;

import java.util.ArrayList;
import java.util.List;

public class ResponseFactory {
    private Request request;
    private FileManager fileManager;
    private DataManager dataManager;
    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";
    private static final String PUT_METHOD = "PUT";
    private static final String DELETE_METHOD = "DELETE";
    private List<String> allValidPaths = new ArrayList<>();

    public ResponseFactory(Request request, FileManager fileManager, DataManager dataManager) {
        this.request = request;
        this.fileManager = fileManager;
        this.dataManager = dataManager;
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
            if (isDirectory()) {
                if (fileManager.isFileImage()) {
                    //this should handle image sending - make a subclass
                    Response fourOhFourResponse = new FourOhFourResponse();
                    return fourOhFourResponse;
                } else {
                    Response gethandler = new GetResponse(fileManager, dataManager);
                    return gethandler;
                }
            }
            if (fileManager.doesFileExist()) {
                Response contentHandler = new ContentResponse(fileManager);
                return contentHandler;
            }
            Response gethandler = new GetResponse(fileManager, dataManager);
            return gethandler;
        }
        else if (request.getMethod().equals(POST_METHOD)) {
            Response postHandler = new PostResponse(dataManager);
            return postHandler;
        } else if (request.getMethod().equals(PUT_METHOD)) {
            Response putHandler = new PutResponse(dataManager);
            return putHandler;
        } else if (request.getMethod().equals(DELETE_METHOD)) {
            return new DeleteResponse(dataManager);
        }
        Response fourOhFourResponse = new FourOhFourResponse();
        return fourOhFourResponse;
    }

    private boolean isRedirectPath() {
        return request.getPath().equals("/redirect");
    }

    private boolean isOptionsPath() {
        return request.getPath().equals("/method_options");
    }

    private boolean isDirectory() {
        return request.getPath().equals("/");
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
