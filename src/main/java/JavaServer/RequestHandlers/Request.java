package JavaServer.RequestHandlers;

import java.util.HashMap;

public class Request {
    private HashMap<String, String> requestAttributes;

    public Request(HashMap<String, String> requestAttributes) {
        this.requestAttributes = requestAttributes;
    }

    public String getMethod() {
        return requestAttributes.get("requestMethod");
    }

    public String getPath() {
        return requestAttributes.get("path");
    }

    public String getData() {
        return requestAttributes.get("data");
    }
}
