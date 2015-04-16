package JavaServer.ResponseManagers;

import JavaServer.RequestManagers.Request;
import JavaServer.ResponseManagers.MethodManagers.RequestManager;

public class ResponseCodeBuilder {
    private RequestManager requestManager;
    private static final String CARRIAGE_RETURN =  "\r\n";

    public ResponseCodeBuilder(RequestManager requestManager) {
        this.requestManager = requestManager;
    }

    public String getResponseHeaders(Request request) {
        String responseString = "";

        responseString += requestManager.manage(request) + CARRIAGE_RETURN;
        responseString += requestManager.getCorrectHeaders() + CARRIAGE_RETURN;
        return responseString;
    }

}
