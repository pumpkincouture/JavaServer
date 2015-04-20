package JavaServer.ResponseManagers.methods;

import JavaServer.RequestManagers.Request;

public class OptionsResponse extends Response {
    private static final String OPTIONS_PATH = "/method_options";

    @Override
    public String manage(Request request) {
        return getCorrectResponse(request.getPath(), OPTIONS_PATH);
    }

    @Override
    public String getCorrectHeaders(Request request) {
        return getHeaders().get("options");
    }
}
