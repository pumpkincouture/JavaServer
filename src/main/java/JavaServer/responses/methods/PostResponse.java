package JavaServer.responses.methods;

import JavaServer.requests.Request;

public class PostResponse extends Response {

    @Override
    public String getCorrectStatus(Request request) {
        if (request.getPath().equals("/form")) {
            updateRequestData(request.getData());
            return getCodes().get("200");
        } else if (request.getPath().equals("/text-file.txt")) {
            return getCodes().get("405");
        }
        return getCodes().get("404");
    }

    @Override
    public String getCorrectHeaders(Request request) {
        return "";
    }

    @Override
    public String getCorrectBody(Request request) {
        return "";
    }
}
