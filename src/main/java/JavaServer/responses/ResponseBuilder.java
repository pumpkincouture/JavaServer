package JavaServer.responses;

import JavaServer.requests.Request;
import JavaServer.responses.methods.Response;

public class ResponseBuilder {
    private Response requestManager;
    private static final String CARRIAGE_RETURN =  "\r\n";

    public ResponseBuilder(Response requestManager) {
        this.requestManager = requestManager;
    }

    public String getResponseHeaders(Request request) {
        String responseString = "";

        responseString += requestManager.manage(request) + CARRIAGE_RETURN;
        responseString += requestManager.getCorrectHeaders(request) + CARRIAGE_RETURN;
        return responseString;
    }

}
