package JavaServer.MethodManagers;

import JavaServer.RequestManagers.Request;

import java.util.Hashtable;

public abstract class RequestManager {

    public abstract String manage(Request request);

    public String getCorrectResponse(String path, String validPath) {
        if (isPathValid(path, validPath)) {
            return getCodes().get("200");
        } else {
            return getCodes().get("404");
        }
    }

    public boolean isPathValid(String path, String validPath) {
        return path.equals(validPath);
    }

    public Hashtable<String, String> getCodes() {
        Hashtable<String, String> codes = new Hashtable<>();
        codes.put("200", "HTTP/1.1 200 OK");
        codes.put("404", "HTTP/1.1 404 Not Found");

        return codes;
    }

    public Hashtable<String, String> getHeaders() {
        Hashtable<String, String> headers = new Hashtable<>();
        headers.put("Allow", "GET,HEAD,POST,OPTIONS,PUT");
        headers.put("Accept", "text/plain");
        headers.put("Host", "http://localhost:5000/");
        headers.put("Accept-Ranges", "bytes");

        return headers;
    }
}
