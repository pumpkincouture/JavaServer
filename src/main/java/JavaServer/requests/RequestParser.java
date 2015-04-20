package JavaServer.requests;

import java.util.*;

public class RequestParser {
    static final int FIRST_ELEMENT = 0;
    static final int SECOND_ELEMENT = 1;
    static final int ELEMENTS_IN_REQUEST_LINE = 3;
    static final String LINE_BREAK = "\\n";
    static final String EMPTY_SPACE = " ";
    static final String COLON = ": ";
    static final String AFTER_COLON = ":\\s";
    static final String EQUAL_SIGN = "=";

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
        return createTable(getMatchingStrings(COLON), AFTER_COLON);
    }

    public HashMap<String, String> getData() {
        return createTable(getMatchingStrings(EQUAL_SIGN), EQUAL_SIGN);
    }

    private String findMethod() {
        return splitFirstLine[FIRST_ELEMENT];
    }

    private String findPath() {
        return splitFirstLine[SECOND_ELEMENT];
    }

    private List<String> getMatchingStrings(String stringToMatch) {
        List<String> stringsList = new ArrayList<>();
        for (String string : splitRequest) {
            if (string.contains(stringToMatch)) {
                stringsList.add(string);
            }
        }
        return stringsList;
    }

    private HashMap<String, String> createTable(List<String> listOfStrings, String stringToSplitOn) {
        HashMap<String, String> table = new LinkedHashMap<>();

        for (String string : listOfStrings) {
            String[] splitHeaders = string.split(stringToSplitOn);

            table.put(splitHeaders[FIRST_ELEMENT], splitHeaders[SECOND_ELEMENT]);
        }

        return table;
    }

    private String blankLine() {
        return LINE_BREAK;
    }
}
