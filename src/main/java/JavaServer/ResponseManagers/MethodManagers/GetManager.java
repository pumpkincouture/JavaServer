package JavaServer.ResponseManagers.MethodManagers;

import JavaServer.RequestManagers.Request;

public class GetManager extends RequestManager {
    private static final String SIMPLE_PATH = "/";

    @Override
    public String manage(Request request) {
        if (request.getPath().equals("/") || (request.getPath().equals("/method_options"))) {
            return getCodes().get("200");
        } else if (request.getPath().equals("/redirect")) {
            return getCodes().get("302");
        }
        else {
            return getCodes().get("404");
        }

//        return getCodes().get("404");
//        return getCorrectResponse(request.getPath(), SIMPLE_PATH) || getCorrectResponse(request.getPath(), "/method_options");
    }

    @Override
    public String getCorrectHeaders(Request request) {
        if (request.getPath().equals("/redirect")) {
            return getHeaders().get("location");
        } else {
            return getHeaders().get("options");
        }
    }
}
