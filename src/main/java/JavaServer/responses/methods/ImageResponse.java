package JavaServer.responses.methods;

import JavaServer.requests.Request;

import java.io.IOException;

public class ImageResponse extends Response {
    @Override
    public String getCorrectStatus(Request request) {
        return getCodes().get("200");
    }

    @Override
    public String getCorrectHeaders(Request request) {
        return "";
    }

    @Override
    public String getCorrectBody(Request request) throws IOException {
        return "";
    }
}
