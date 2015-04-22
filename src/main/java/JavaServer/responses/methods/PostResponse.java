package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.methods.validators.PathValidator;
import JavaServer.responses.methods.validators.PostPathValidator;

public class PostResponse extends Response {
    private PathValidator pathValidator;

    public PostResponse() {
        pathValidator = new PostPathValidator();
    }

    @Override
    public String getCorrectStatus(Request request) {
        return pathValidator.returnCorrectStatus(request.getPath());
    }

    @Override
    public String getCorrectHeaders(Request request) {
        return pathValidator.returnCorrectHeader(request.getPath());
    }

    @Override
    public String getCorrectBody(Request request) {
        return pathValidator.returnCorrectBody();
    }
}
