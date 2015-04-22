package JavaServer.responses.methods;

import JavaServer.requests.Request;

import java.io.IOException;

public class NotFoundResponse extends Response {

    @Override
    public String getCorrectStatus(Request request) {
        return getCodes().get("302");
    }

    @Override
    public String getCorrectHeaders(Request request) {
        return getHeaders().get("location");
    }

    @Override
    public String getCorrectBody(Request request) throws IOException {
        return "";
    }
}
