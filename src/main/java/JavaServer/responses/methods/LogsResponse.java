package JavaServer.responses.methods;

import JavaServer.requests.Logger;
import JavaServer.requests.Request;

import java.io.IOException;

public class LogsResponse extends Response {
    private Logger logger;

    public LogsResponse(Logger logger) {
        this.logger = logger;
    }

    @Override
    public String getCorrectStatus(Request request) {
        return getCodes().get("200");
    }

    @Override
    public String getCorrectHeaders(Request request) {
        return EMPTY_STRING;
    }

    @Override
    public String getCorrectBody(Request request) throws IOException {
        return logger.getLogs();
    }
}
