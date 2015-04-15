package JavaServer.RequestManagers;

import java.util.HashMap;

public class Request {
    private String requestMethod;
    private String path;
    private HashMap<String, String> headers;
    private HashMap<String, String> data;

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

    public HashMap<String, String> getHeaders() {
        return headers;
    }

    public HashMap<String, String> getData() {
        return data;
    }
}
