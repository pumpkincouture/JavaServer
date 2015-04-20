package JavaServer.responses.methods;

import JavaServer.requests.Request;

import java.util.Hashtable;

public abstract class Response {

    public abstract String manage(Request request);

    public abstract String getCorrectHeaders(Request request);

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

    public Hashtable<String, String> getValidRoutes() {
        Hashtable<String, String> validRoutes = new Hashtable<>();
        validRoutes.put("GET", "/");
        validRoutes.put("GET", "/method_options");
        validRoutes.put("GET", "/redirect");
        validRoutes.put("OPTIONS", "/method_options");
        validRoutes.put("POST", "/form");
        validRoutes.put("POST", "/method_options");
        validRoutes.put("POST", "/text-file.txt");
        validRoutes.put("PUT", "/form");
        validRoutes.put("PUT", "/method_options");
        validRoutes.put("PUT", "/file1");

        return validRoutes;
    }

    public Hashtable<String, String> getCodes() {
        Hashtable<String, String> codes = new Hashtable<>();
        codes.put("200", "HTTP/1.1 200 OK");
        codes.put("404", "HTTP/1.1 404 Not Found");
        codes.put("405", "HTTP/1.1 405 Method Not Allowed");
        codes.put("302", "HTTP/1.1 302 Found");
        return codes;
    }

    public Hashtable<String, String> getHeaders() {
        Hashtable<String, String> headers = new Hashtable<>();
        headers.put("options", "Allow: GET,HEAD,POST,OPTIONS,PUT");
        headers.put("location", "Location: http://localhost:5000/");
        headers.put("Accept:", "text/plain");
        headers.put("Host:", "http://localhost:5000/");
        headers.put("Accept-Ranges:", "bytes");

        return headers;
    }
}
