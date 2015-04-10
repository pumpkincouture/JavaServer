package JavaServer.ResponseHandlers;

import java.util.Hashtable;

public class StatusCodes {
    private Hashtable<String, String> codes;

    public Hashtable<String, String> getCodes() {
        codes = new Hashtable<String, String>();
        codes = new Hashtable<String, String>();
        codes.put("302", "Moved Temporarily");
        codes.put("405", "Method Not Allowed");
        codes.put("200", "OK");
        codes.put("404", "Not Found");

        return codes;
    }

}
