package JavaServer.responses.methods;

import JavaServer.requests.Logger;

public class LogsResponse extends Response {
    private Logger logger;

    public LogsResponse(Logger logger) {
        this.logger = logger;
    }

    @Override
    public String getCorrectStatus() {
        return getCodes().get("200");
    }

    @Override
    public String getCorrectHeaders() {
        return EMPTY_STRING;
    }

    @Override
    public String getCorrectBody() {
        return logger.getLogs();
    }
}
