package JavaServer;

import JavaServer.RequestHandlers.Request;
import JavaServer.ResponseHandlers.HTTPHeaders;
import JavaServer.ResponseHandlers.StatusCodes;

public class ResponseCodeBuilder {
    private Request request;
    private HTTPHeaders headers;
    private StatusCodes statusCodes;

    public ResponseCodeBuilder(Request request) {
        this.request = request;
        statusCodes = new StatusCodes();
        headers = new HTTPHeaders();
    }


    public String returnResponseCode() {
        if (request.getMethod().contains("GET") && request.getPath().contains("/redirect")) {
            return "HTTP/1.1 302 Moved Temporarily";
        }
        else if (request.getMethod().contains("PUT") && request.getPath().equals("/file1") || request.getPath().equals("/text-file.txt")) {
            return "HTTP/1.1 405 Method Not Allowed";
        }
        else if (request.getMethod().contains("GET") && request.getPath().equals("/") || request.getMethod().contains("OPTIONS")) {
            return "HTTP/1.1 200 OK";
        }
        return "HTTP/1.1 404 Not Found";
    }
}
