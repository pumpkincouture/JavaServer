package JavaServer.MethodHandlers;

import JavaServer.RequestHandlers.Request;
import JavaServer.ResponseHandlers.ResponseCodeBuilder;

public class GetHandler implements RequestHandler {
    private ResponseCodeBuilder responseCodeBuilder;

    public GetHandler(ResponseCodeBuilder responseCodeBuilder) {
        this.responseCodeBuilder = responseCodeBuilder;
    }

    @Override
    public String handle(Request request) {
       return getResponse(request);
    }

    private String getResponse(Request request) {
        if (request.getPath().equals("/")) {
            return responseCodeBuilder.createValidResponse();
        }
        return responseCodeBuilder.returnFourOhFour();
    }
}
