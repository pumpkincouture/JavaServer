package JavaServer.MethodHandlers;

import JavaServer.RequestHandlers.Request;
import JavaServer.ResponseHandlers.ResponseCodeBuilder;

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
