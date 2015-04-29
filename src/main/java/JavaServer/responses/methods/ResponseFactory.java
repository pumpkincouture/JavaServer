package JavaServer.responses.methods;

import JavaServer.requests.Logger;
import JavaServer.requests.Request;
import JavaServer.requests.RouteValidator;
import JavaServer.responses.DataManager;
import JavaServer.responses.FileManager;

public class ResponseFactory {
    private Request request;
    private FileManager fileManager;
    private DataManager dataManager;
    private RouteValidator routeValidator;
    private Logger logger;

    public ResponseFactory(Request request, FileManager fileManager, DataManager dataManager, Logger logger) {
        this.request = request;
        this.fileManager = fileManager;
        this.dataManager = dataManager;
        this.routeValidator = new RouteValidator(request);
        this.logger = logger;
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
        else if (routeValidator.isParameterPath()) {
            return new ParamResponse(dataManager, request.getData());
        }
        else if (routeValidator.methodEqualsGet()) {
            if (routeValidator.isDirectory()) {
                return new GetResponse(fileManager, dataManager, request.getPath());
                }
            if (routeValidator.isPartialPathWithRange()) {
                return new PartialResponse(fileManager, request.getHeaders());
            }
            if (fileManager.doesFileExist()) {
                    return new ContentResponse(fileManager);
                }
            else if (routeValidator.requiresAuthorization()) {
                if (routeValidator.requestHasCorrectAuthorization()) {
                    return new LogsResponse(logger);
                }
                return new UnauthorizedResponse();
            }
            return new GetResponse(fileManager, dataManager, request.getPath());
        }
        else if (routeValidator.methodEqualsPost()) {
            return new PostResponse(dataManager, request.getPath(), request.getData());
        } else if (routeValidator.methodEqualsPut()) {
            return new PutResponse(dataManager, request.getPath(), request.getData());
        } else if (routeValidator.methodEqualsDelete()) {
            return new DeleteResponse(dataManager, request.getPath());
        } else if (routeValidator.methodEqualsPatch() && routeValidator.containsEtagAuthorization()) {
            return new PatchResponse(dataManager, request.getData(), fileManager);
        }
        return new FourOhFourResponse();
    }
}
