package JavaServer.RequestManagers;

import java.util.*;

public class RequestParser {
    static final int FIRST_ELEMENT = 0;
    static final int SECOND_ELEMENT = 1;
    static final int ELEMENTS_IN_REQUEST_LINE = 3;
    static final String LINE_BREAK = "\\n";
    static final String EMPTY_SPACE = " ";
    static final String COLON = ": ";
    static final String AFTER_COLON = ":\\s";
    static final String EQUALS = "=";

    String request;
    String[] splitFirstLine;
    String[] splitRequest;

    public RequestParser(String request) {
        this.request = request;
        splitFirstLine = request.split(EMPTY_SPACE , ELEMENTS_IN_REQUEST_LINE);
        splitRequest = request.split((blankLine()));
    }

   public String getMethod() {
       return findMethod();
   }

    public String getPath() {
        return findPath();
    }

    public HashMap<String, String> getHeaders() {
        return createHeadersTable(getHeaderStrings(COLON), AFTER_COLON);
    }

    public HashMap<String, String> getData() {
        return createHeadersTable(getHeaderStrings(EQUALS), EQUALS);
    }

    private String findMethod() {
        return splitFirstLine[FIRST_ELEMENT];
    }

    private String findPath() {
        return splitFirstLine[SECOND_ELEMENT];
    }

    private List<String> getHeaderStrings(String splitType) {
        List<String> headersStrings = new ArrayList<>();
        for (String string : splitRequest) {
            if (string.contains(splitType)) {
                headersStrings.add(string);
            }
        }
        return headersStrings;
    }

    private HashMap<String, String> createHeadersTable(List<String> headerStrings, String splitOn) {
        LinkedHashMap<String, String> headers = new LinkedHashMap<>();

        for (String string : headerStrings) {
            String[] splitHeaders = string.split(splitOn);

            headers.put(splitHeaders[FIRST_ELEMENT], splitHeaders[SECOND_ELEMENT]);
        }

        return headers;
    }

    private String blankLine() {
        return LINE_BREAK;
    }
}
