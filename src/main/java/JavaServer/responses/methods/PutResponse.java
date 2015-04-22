package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.methods.validators.PathValidator;
import JavaServer.responses.methods.validators.PutPathValidator;

public class PutResponse extends Response {
    private PathValidator pathValidator;

    public PutResponse() {
        pathValidator = new PutPathValidator();
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
