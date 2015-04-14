package JavaServer.MethodHandlers;

import JavaServer.RequestHandlers.Request;
import JavaServer.ResponseHandlers.ResponseCodeBuilder;

public class OptionsManager extends RequestManager {
    private ResponseCodeBuilder responseCodeBuilder;

    public OptionsManager(ResponseCodeBuilder responseCodeBuilder) {
        this.responseCodeBuilder = responseCodeBuilder;
    }

    @Override
    public String handle(Request request) {
        if (request.getPath().equals("/method_options")) {
            return responseCodeBuilder.createValidResponse();
        }
        return responseCodeBuilder.returnFourOhFour();
    }
}
