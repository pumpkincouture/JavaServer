package JavaServer.responses.methods;

import JavaServer.requests.Logger;
import JavaServer.requests.Request;
import JavaServer.helpers.RouteValidator;
import JavaServer.responses.FileWriter;

public class ResponseFactory {
    private Request request;
    private FileWriter fileWriter;
    private RouteValidator routeValidator;
    private Logger logger;

    public ResponseFactory(Request request, FileWriter fileWriter, Logger logger) {
        this.request = request;
        this.fileWriter = fileWriter;
        this.logger = logger;
        this.routeValidator = new RouteValidator(request);
    }

    public Response createResponse() {
        routeValidator.addMethodsToValidPaths(fileWriter.convertFilesToPaths());
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
            return new ParamResponse(request.getData(), fileWriter);
        }
        else if (routeValidator.methodEqualsGet()) {
            if (routeValidator.isDirectory()) {
                return new GetResponse(fileWriter, request);
                }
            if (fileWriter.doesFileExist()) {
                    return new ContentResponse(fileWriter, request);
                }
            else if (routeValidator.requiresAuthorization()) {
                if (routeValidator.requestHasCorrectAuthorization()) {
                    return new LogsResponse(logger);
                }
                return new UnauthorizedResponse();
            }
            return new GetResponse(fileWriter, request);
        }
        else if (routeValidator.methodEqualsPost()) {
            return new PostResponse(fileWriter, request.getPath(), request.getData());
        } else if (routeValidator.methodEqualsPut()) {
            return new PutResponse(fileWriter, request.getData(), request.getPath());
        } else if (routeValidator.methodEqualsDelete()) {
            return new DeleteResponse(fileWriter, request.getPath());
        } else if (routeValidator.methodEqualsPatch()) {
            return new PatchResponse(fileWriter, request);
        }
        return new FourOhFourResponse();
    }
}
