package JavaServer.requests;

import java.net.URLDecoder;
import java.util.*;

public class RequestParser {
    static final int FIRST_ELEMENT = 0;
    static final int SECOND_ELEMENT = 1;
    static final String LINE_BREAK = "\\n";
    static final String EMPTY_SPACE = " ";
    static final String COLON = ": ";
    static final String AFTER_COLON = ":\\s";
    static final String EQUAL_SIGN = "=";
    static final String AUTHORIZATION = "Authorization";

    String request;
    String[] splitFirstLineWithParams;
    String[] splitRequest;
    HashMap<String, String> data;

    public RequestParser(String request) {
        this.request = request;
        splitRequest();
    }

    public void splitRequest() {
        if (request.contains("/parameters?")) {
            splitFirstLineWithParams = request.split(EMPTY_SPACE);
        } else if (!request.contains("/parameters?")) {
            splitFirstLineWithParams = request.split(EMPTY_SPACE);
        }
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
        parseAndReturnData();
        return data;
    }

    private String findMethod() {
        return splitFirstLineWithParams[FIRST_ELEMENT];
    }

    private String findPath() {
        if (!request.contains("/parameters?")) {
            return splitFirstLineWithParams[SECOND_ELEMENT];
        }
        return extractPath(splitFirstLineWithParams[SECOND_ELEMENT], FIRST_ELEMENT);
    }

    private void parseAndReturnData() {
        if (request.contains("/parameters?")) {
            data = decodeParameters(extractPath(splitFirstLineWithParams[SECOND_ELEMENT], SECOND_ELEMENT));
        } else if (request.contains("PATCH")) {
            data = getPatchedData();
        }
        else if (!request.contains("/parameters?")) {
            data = createTable(getMatchingStrings(EQUAL_SIGN), EQUAL_SIGN);
        }
    }

    private HashMap<String, String> getPatchedData() {
        HashMap<String, String> params = new HashMap<>();
        for (String string : splitRequest) {
            if (string.contains("patched") || string.contains("default")) {
                String[] patchedContent = string.split(EMPTY_SPACE);
                for (String patchedStrings : patchedContent) {
                    params.put(patchedStrings, patchedStrings);
                }
            }
        }
        return params;
    }

    private List<String> getMatchingStrings(String stringToMatch) {
        List<String> stringsList = new ArrayList<>();
        for (String string : splitRequest) {
            if (stringToMatch.equals(COLON)) {
                if (string.contains(stringToMatch)) {
                    stringsList.add(string);
                }
            } else if (stringToMatch.equals(EQUAL_SIGN)) {
                if (string.contains(stringToMatch) && !string.contains(AUTHORIZATION)) {
                    stringsList.add(string);
                }
            }
        }
        return stringsList;
    }

    private String extractPath(String toSeparate, int element) {
        String[] getPath = toSeparate.split("\\?");

        return getPath[element];
    }

    private HashMap<String, String> decodeParameters(String firstLine) {
        HashMap<String, String> params = new HashMap<>();
        String[] splitLine = firstLine.split("&");

        for (String string : splitLine) {
            String[] split = string.split(EQUAL_SIGN);
            params.put(split[0], URLDecoder.decode(split[1]));
        }

        return params;

    }

    private HashMap<String, String> createTable(List<String> listOfStrings, String stringToSplitOn) {
        HashMap<String, String> table = new LinkedHashMap<>();

        if (listOfStrings.size() == 0) {
            return table;
        }
        else {
            for (String string : listOfStrings) {
                String[] splitHeaders = string.split(stringToSplitOn);

                table.put(splitHeaders[FIRST_ELEMENT], splitHeaders[SECOND_ELEMENT]);
            }
        }

        return table;
    }

    private String blankLine() {
        return LINE_BREAK;
    }
}
