package JavaServer.responses.methods;

import JavaServer.requests.Request;

public class GetResponse extends Response {
    private static final String SIMPLE_PATH = "/";
    private static final String METHOD_OPTIONS = "/method_options";
    public static final String REDIRECT = "/redirect";

    @Override
    public String manage(Request request) {
        if (request.getPath().equals(SIMPLE_PATH) || (request.getPath().equals(METHOD_OPTIONS))) {
            return getCodes().get("200");
        } else if (request.getPath().equals(REDIRECT)) {
            return getCodes().get("302");
        }
        else {
            return getCodes().get("404");
        }
    }

    @Override
    public String getCorrectHeaders(Request request) {
        if (request.getPath().equals(REDIRECT)) {
            return getHeaders().get("location");
        } else {
            return getHeaders().get("options");
        }
    }
}
