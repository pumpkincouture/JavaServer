package JavaServer.ResponseHandlers;

import JavaServer.RequestHandlers.Request;

public class OptionsHandler implements RequestHandler {
    private ResponseCodeBuilder responseCodeBuilder;

    public OptionsHandler(ResponseCodeBuilder responseCodeBuilder) {
        this.responseCodeBuilder = responseCodeBuilder;
    }

    @Override
    public String handle(Request request) {
        return responseCodeBuilder.createValidResponse();
    }
}
