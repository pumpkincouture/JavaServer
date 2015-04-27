package JavaServer.responses.methods;

import JavaServer.requests.Request;

import java.io.IOException;

public class UnauthorizedResponse extends Response{

    @Override
    public String getCorrectStatus(Request request) {
        return getCodes().get("401");
    }

    @Override
    public String getCorrectHeaders(Request request) {
        return EMPTY_STRING;
    }

    @Override
    public String getCorrectBody(Request request) throws IOException {
        return "Authentication required";
    }
}
