package JavaServer.responses;

import JavaServer.requests.Request;
import JavaServer.responses.methods.Response;

import java.io.IOException;

public class ResponseBuilder {
    private Response requestManager;
    private static final String CARRIAGE_RETURN =  "\r\n";
    private static final String BLANK_LINE = "\n";

    public ResponseBuilder(Response requestManager) {
        this.requestManager = requestManager;
    }

    public String getResponseHeaders(Request request) {
        String responseString = "";

        responseString += requestManager.getCorrectStatus(request) + BLANK_LINE;
        responseString += requestManager.getCorrectHeaders(request) + CARRIAGE_RETURN;
        return responseString;
    }

    public String getBody(Request request) throws IOException {
        return requestManager.getCorrectBody(request);
    }
}
