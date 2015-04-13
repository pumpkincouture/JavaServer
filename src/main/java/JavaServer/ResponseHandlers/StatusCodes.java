package JavaServer.ResponseHandlers;

import java.util.Hashtable;

public class StatusCodes {
    private Hashtable<String, String> codes;

    public Hashtable<String, String> getCodes() {
        codes = new Hashtable<String, String>();
        codes.put("200", "HTTP/1.1 200 OK");
        codes.put("404", "HTTP/1.1 404 Not Found");

        return codes;
    }

}
