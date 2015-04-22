package JavaServer.responses.methods;

import JavaServer.requests.Request;

public class OptionsResponse extends Response {
    private static final String OPTIONS_PATH = "/method_options";

    @Override
    public String getCorrectStatus(Request request) {
        return getCorrectResponse(request.getPath(), OPTIONS_PATH);
    }

    @Override
    public String getCorrectHeaders(Request request) {
        return getHeaders().get("options");
    }

    @Override
    public String getCorrectBody(Request request) {
        return "";
    }
}