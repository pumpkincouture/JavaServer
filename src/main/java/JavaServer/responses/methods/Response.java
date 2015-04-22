package JavaServer.responses.methods;

import JavaServer.requests.Request;

import java.io.IOException;

public abstract class Response {

    public abstract String getCorrectStatus(Request request);

    public abstract String getCorrectHeaders(Request request);

    public abstract String getCorrectBody(Request request) throws IOException;
}
