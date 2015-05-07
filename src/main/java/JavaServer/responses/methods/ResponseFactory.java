package JavaServer.responses.methods;

import JavaServer.requests.Logger;
import JavaServer.requests.Request;
import JavaServer.helpers.RouteValidator;
import JavaServer.responses.FileAdmin;

public class ResponseFactory {
    private Request request;
    private FileAdmin fileAdmin;
    private RouteValidator routeValidator;
    private Logger logger;

    public ResponseFactory(Request request, FileAdmin fileAdmin, Logger logger) {
        this.request = request;
        this.fileAdmin = fileAdmin;
        this.logger = logger;
        this.routeValidator = new RouteValidator(request);
    }

    public Response createResponse() {
        routeValidator.addMethodsToValidPaths(fileAdmin.convertFilesToPaths());
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
            return new ParamResponse(request.getData(), fileAdmin);
        }
        else if (routeValidator.methodEqualsGet()) {
            if (routeValidator.isDirectory()) {
                return new GetResponse(fileAdmin, request);
                }
            if (fileAdmin.doesFileExist()) {
                    return new ContentResponse(fileAdmin, request);
                }
            else if (routeValidator.requiresAuthorization()) {
                if (routeValidator.requestHasCorrectAuthorization()) {
                    return new LogsResponse(logger);
                }
                return new UnauthorizedResponse();
            }
            return new GetResponse(fileAdmin, request);
        }
        else if (routeValidator.methodEqualsPost()) {
            return new PostResponse(fileAdmin, request.getPath(), request.getData());
        } else if (routeValidator.methodEqualsPut()) {
            return new PutResponse(fileAdmin, request.getData(), request.getPath());
        } else if (routeValidator.methodEqualsDelete()) {
            return new DeleteResponse(fileAdmin, request.getPath());
        } else if (routeValidator.methodEqualsPatch()) {
            return new PatchResponse(fileAdmin, request);
        }
        return new FourOhFourResponse();
    }
}
