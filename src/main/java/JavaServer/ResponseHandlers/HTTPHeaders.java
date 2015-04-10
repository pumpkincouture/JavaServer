package JavaServer.ResponseHandlers;

import java.util.Hashtable;

public class HTTPHeaders {

    private Hashtable<String, String> headers;

    public Hashtable<String, String> getHeaders() {
        headers = new Hashtable<String, String>();
        headers.put("Allow", "GET,HEAD,POST,OPTIONS,PUT");
        headers.put("Accept", "text/plain");
        headers.put("Host", "http://localhost:5000/");
        headers.put("Accept-Ranges", "bytes");

        return headers;
    }
}
