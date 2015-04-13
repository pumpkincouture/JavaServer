package JavaServer.ResponseHandlers;

import JavaServer.RequestHandlers.Request;

import java.nio.file.Path;
import java.nio.file.Paths;

public class GetHandler implements RequestHandler {
    private ResponseCodeBuilder responseCodeBuilder;

    public GetHandler(ResponseCodeBuilder responseCodeBuilder) {
        this.responseCodeBuilder = responseCodeBuilder;
    }

    @Override
    public String handle(Request request) {
        Path path = Paths.get("../cob_spec/public" + request.getPath());

       return getResponse(request);
    }

    private String getResponse(Request request) {
        if (request.getPath().equals("/")) {
            return responseCodeBuilder.createValidResponse();
        }

        return responseCodeBuilder.returnFourOhFour();
    }
}
