package JavaServer.MethodHandlers;

import JavaServer.RequestHandlers.Request;
import JavaServer.ResponseHandlers.ResponseCodeBuilder;

public class GetManager extends RequestManager {
    private ResponseCodeBuilder responseCodeBuilder;

    public GetManager(ResponseCodeBuilder responseCodeBuilder) {
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
