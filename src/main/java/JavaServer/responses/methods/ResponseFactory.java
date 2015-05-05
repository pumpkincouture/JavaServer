package JavaServer.responses.methods;

import JavaServer.requests.Logger;
import JavaServer.requests.Request;
import JavaServer.requests.RouteValidator;
import JavaServer.responses.FileManager;

public class ResponseFactory {
    private Request request;
    private FileManager fileManager;
    private RouteValidator routeValidator;
    private Logger logger;

    public ResponseFactory(Request request, FileManager fileManager, Logger logger) {
        this.request = request;
        this.fileManager = fileManager;
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
            return new ParamResponse(request.getData(), fileManager);
        }
        else if (routeValidator.methodEqualsGet()) {
            if (routeValidator.isDirectory()) {
                return new GetResponse(fileManager, request.getPath());
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
            return new GetResponse(fileManager, request.getPath());
        }
        else if (routeValidator.methodEqualsPost()) {
            return new PostResponse(fileManager, request.getPath(), request.getData());
        } else if (routeValidator.methodEqualsPut()) {
            return new PutResponse(fileManager, request.getData(), request.getPath());
        } else if (routeValidator.methodEqualsDelete()) {
            return new DeleteResponse(fileManager, request.getPath());
        } else if (routeValidator.methodEqualsPatch()) {
            return new PatchResponse(fileManager, request);
        }
        return new FourOhFourResponse();
    }
}
