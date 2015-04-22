package JavaServer.responses.methods;

import JavaServer.requests.Request;

public class PutResponse extends Response {
    private static final String FORM_PATH = "/form";
    public static final String METHOD_OPTIONS = "/method_options";
    public static final String FILE_PATH = "/file1";

    @Override
    public String getCorrectStatus(Request request) {
        if (request.getPath().equals(FORM_PATH) || (request.getPath().equals(METHOD_OPTIONS))) {
            return getCodes().get("200");
        } else if (request.getPath().equals(FILE_PATH)) {
            return getCodes().get("405");
        }
        return getCodes().get("404");
    }

    @Override
    public String getCorrectHeaders(Request request) {
        if (request.getPath().equals(METHOD_OPTIONS)) {
            return getHeaders().get("options");
        }
        return "";
    }

    @Override
    public String getCorrectBody(Request request) {
        return "";
    }
}