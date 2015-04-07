package JavaServer;

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
        return splitRequest[2];
    }
}
