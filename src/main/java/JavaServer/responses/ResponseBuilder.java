package JavaServer.responses;

import JavaServer.requests.Request;
import JavaServer.responses.methods.Response;

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
        responseString += requestManager.getCorrectHeaders(request) + BLANK_LINE;
        responseString += requestManager.getCorrectBody(request) + CARRIAGE_RETURN;
        return responseString;
    }
}
