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
        responseString += requestManager.getCorrectHeaders(request) + CARRIAGE_RETURN;
        return responseString;
    }

    public String getBody(Request request) {
        return requestManager.getCorrectBody(request);
    }

    public String getDirectory() {

        String directoryCommand = "-d";
        return System.getProperty("user.dir") + "/cob_spec/public";
    }


}
