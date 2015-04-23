package JavaServer.responses.methods;

import JavaServer.requests.Request;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

public abstract class Response {

    private String requestDataString = "";
    private Map<String, String> requestData;

    public void updateRequestData(Map<String, String> updatedData) {
        this.requestDataString = turnDataIntoString(updatedData);
    }

    public String getRequestData() {
        return requestDataString;
    }

    public void resetData() {
        requestDataString = "";
    }

    private String turnDataIntoString(Map<String, String> updatedData) {
        String dataString = "";

        for (Map.Entry<String, String> entry : updatedData.entrySet()) {
            dataString += entry.getKey() + "=" + entry.getValue();
        }
        return dataString;
    }


    public abstract String getCorrectStatus(Request request);

    public abstract String getCorrectHeaders(Request request);

    public abstract String getCorrectBody(Request request) throws IOException;

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
