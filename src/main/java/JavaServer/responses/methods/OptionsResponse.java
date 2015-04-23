package JavaServer.responses.methods;

import JavaServer.requests.Request;

public class OptionsResponse extends Response {

    @Override
    public String getCorrectStatus(Request request) {
        return getCodes().get("200");
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
