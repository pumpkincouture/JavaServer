package JavaServer.RequestManagers;

import java.util.HashMap;

public class RequestParser {
    static final int METHOD = 0;
    static final int PATH = 1;
    static final String LINE_BREAK = "\\n";

    String request;
    String[] splitRequest;

    public RequestParser(String request) {
        this.request = request;
        splitRequest = request.split(" ", 3);
    }

    public HashMap<String, String> getAllRequestAttributes() {
        HashMap<String, String> requestAttributes = new HashMap();

        requestAttributes.put("requestMethod", getMethod());
        requestAttributes.put("path", getPath());
        requestAttributes.put("data", getPostedData());

        return requestAttributes;
    }

    private String getMethod() {
        return splitRequest[METHOD];
    }

    private String getPath() {
        return splitRequest[PATH];
    }

    private String getPostedData() {
        String data = null;

        String[] lines = request.split(blankLine());

        if (lines.length > 1) {
            data = lines[lines.length - 1];
        }

        return data;
    }

    private String blankLine() {
        return LINE_BREAK;
    }
}
