package JavaServer.responses.methods;

import java.io.IOException;
import java.util.Hashtable;

public abstract class Response {
    public static final String EMPTY_STRING = "";
    public static final String EQUAL_SIGN = "=";
    public static final String EQUALITY_WITH_SPACES = " = ";

    public abstract String getCorrectStatus();

    public abstract String getCorrectHeaders();

    public abstract String getCorrectBody() throws IOException;

    public Hashtable<String, String> getCodes() {
        Hashtable<String, String> codes = new Hashtable<>();
        codes.put("200", "HTTP/1.1 200 OK");
        codes.put("404", "HTTP/1.1 404 Not Found");
        codes.put("405", "HTTP/1.1 405 Method Not Allowed");
        codes.put("302", "HTTP/1.1 302 Found");
        codes.put("401", "HTTP/1.1 401 Unauthorized");
        return codes;
    }

    public Hashtable<String, String> getHeaders() {
        Hashtable<String, String> headers = new Hashtable<>();
        headers.put("options", "Allow: GET,HEAD,POST,OPTIONS,PUT");
        headers.put("location", "Location: http://localhost:5000/");
        headers.put("Host:", "http://localhost:5000/");

        return headers;
    }
}
