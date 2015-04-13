package JavaServer.ResponseHandlers;

import JavaServer.RequestHandlers.Request;

public class ResponseCodeBuilder {
    private Request request;
    private HTTPHeaders headers;
    private StatusCodes statusCodes;

    public ResponseCodeBuilder(Request request) {
        this.request = request;
        statusCodes = new StatusCodes();
        headers = new HTTPHeaders();
    }

    public String createValidResponse() {
        return statusCodes.getCodes().get("200");
    }

    public String returnFourOhFour() {
        return statusCodes.getCodes().get("404");
    }

    public String returnOptionsString() {
        return headers.getHeaders().get("Allow");
    }
}
