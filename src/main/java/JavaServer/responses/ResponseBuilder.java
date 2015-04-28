package JavaServer.responses;

import JavaServer.responses.methods.Response;

import java.io.IOException;

public class ResponseBuilder {
    private Response requestManager;
    private static final String CARRIAGE_RETURN =  "\r\n";
    private static final String BLANK_LINE = "\n";

    public ResponseBuilder(Response requestManager) {
        this.requestManager = requestManager;
    }

    public String getResponseHeaders() {
        String responseString = "";

        responseString += requestManager.getCorrectStatus() + BLANK_LINE;
        responseString += requestManager.getCorrectHeaders() + CARRIAGE_RETURN;
        return responseString;
    }

    public String getBody() throws IOException {
        return requestManager.getCorrectBody();
    }
}
