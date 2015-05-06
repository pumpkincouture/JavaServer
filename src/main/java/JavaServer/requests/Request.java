package JavaServer.requests;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private String requestMethod;
    private String path;
    private Map<String, String> headers;
    private Map<String, String> data;

    public Request(String requestMethod, String path, HashMap<String, String> headers, HashMap<String, String> data) {
        this.requestMethod = requestMethod;
        this.path = path;
        this.headers = headers;
        this.data = data;
    }

    public String getMethod() {
        return requestMethod;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public Map<String, String> getData() {
        return data;
    }

    public boolean hasAuthorization() {
        return getHeaders().get("Authorization") != null;
    }

    public String getRequestLine() {
        return "" + requestMethod + " " + path + " " + "HTTP/1.1";
    }
}