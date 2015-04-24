package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RouteValidator;
import JavaServer.responses.DataManager;
import JavaServer.responses.FileManager;

public class ResponseFactory {
    private Request request;
    private FileManager fileManager;
    private DataManager dataManager;
    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";
    private static final String PUT_METHOD = "PUT";
    private static final String DELETE_METHOD = "DELETE";
    private RouteValidator routeValidator;

    public ResponseFactory(Request request, FileManager fileManager, DataManager dataManager) {
        this.request = request;
        this.fileManager = fileManager;
        this.dataManager = dataManager;
        this.routeValidator = new RouteValidator(request);
    }

    public Response createResponse() {
        routeValidator.addMethodsToValidPaths(fileManager.convertFilesToPaths());
        if  (routeValidator.isInvalidPath()) {
            return new FourOhFourResponse();
        }
        else if (routeValidator.isRedirectPath()) {
            return new RedirectResponse();
        }
        else if (routeValidator.isOptionsPath()) {
            return new OptionsResponse();
        }
        else if (request.getMethod().equals(GET_METHOD)) {
            if (routeValidator.isDirectory()) {
                if (fileManager.isFileImage()) {
                    //this should handle image sending - make a subclass
                    return new ImageResponse();
                } else {
                    return new GetResponse(fileManager, dataManager);
                }
            }
            if (fileManager.doesFileExist() && fileManager.isFileImage()) {
                return new ImageResponse();
            }
            else if (fileManager.doesFileExist() && !fileManager.isFileImage()) {
                    return new ContentResponse(fileManager);
                }
            return new GetResponse(fileManager, dataManager);
        }
        else if (request.getMethod().equals(POST_METHOD)) {
            return new PostResponse(dataManager);
        } else if (request.getMethod().equals(PUT_METHOD)) {
            return new PutResponse(dataManager);
        } else if (request.getMethod().equals(DELETE_METHOD)) {
            return new DeleteResponse(dataManager);
        }
        return new FourOhFourResponse();
    }
}
