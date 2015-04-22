package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.methods.validators.OptionsPathValidator;
import JavaServer.responses.methods.validators.PathValidator;

public class OptionsResponse extends Response {
    private PathValidator pathValidator;

    public OptionsResponse() {
        pathValidator = new OptionsPathValidator();
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
