package JavaServer.RequestHandlers;

public class RequestParser {
    String request;
    String[] splitRequest;

    public RequestParser(String request) {
        this.request = request;
        splitRequest = request.split(" ", 3);
    }

    public String getMethod() {
        return splitRequest[0];
    }

    public String getPath() {
        return splitRequest[1];
    }

    public String getHTTPVersion() {
        if (!splitRequest[splitRequest.length - 1].contains("HTTP")) {
            return "";
        }
        return splitRequest[splitRequest.length - 1];
    }
}
