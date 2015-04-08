package JavaServer;

import java.util.Hashtable;

public class ResponseCodeBuilder {
    private Request request;
    private Hashtable<String, String> headers;
    private StatusCodes statusCodes;

    public ResponseCodeBuilder(Request request) {
        this.request = request;
        statusCodes = new StatusCodes();
    }

    public Hashtable<String, String> getHeaders() {
        headers = new Hashtable<String, String>();
        headers.put("Allow", "GET,HEAD,POST,OPTIONS,PUT");
        headers.put("Accept", "text/plain");
        headers.put("Host", "http://localhost:5000/");
        headers.put("Accept-Ranges", "bytes");

        return headers;
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
