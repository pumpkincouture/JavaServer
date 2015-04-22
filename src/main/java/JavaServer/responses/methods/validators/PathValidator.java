package JavaServer.responses.methods.validators;

import java.util.Hashtable;

public abstract class PathValidator {

    public abstract String returnCorrectStatus(String requestPath);

    public abstract String returnCorrectHeader(String requestPath);

    public abstract String returnCorrectBody();

    public abstract boolean correctMethodForBodyContents(String requestPath);

    public abstract boolean doesPathEqualFile(String requestPath);

    public Hashtable<String, String> getAvailablePaths() {
        Hashtable<String, String> validPaths = new Hashtable<>();
        validPaths.put("directory", "/");
        validPaths.put("options", "/method_options");
        validPaths.put("redirect", "/redirect");
        validPaths.put("file_1", "/file1");
        validPaths.put("file_2", "/file2");
        validPaths.put("form", "/form");
        validPaths.put("text_file", "/text-file.txt");

        return validPaths;
    }

    public String emptyString() {
        return "";
    }

    public boolean isValidPath(String requestPath, String key) {
        return requestPath.equals(getAvailablePaths().get(key));
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
        headers.put("content", "Content-Type: text/html");
        headers.put("Host:", "http://localhost:5000/");
        headers.put("Accept-Ranges:", "bytes");

        return headers;
    }
}
