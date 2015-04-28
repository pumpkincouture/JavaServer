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
//       System.out.println(URLDecoder.decode(request));
//       System.out.println(request + " the request string");
//       System.out.println(splitFirstLine.length + " the length of the request string");
       //cannot split the string right away - move splitfirstline to a method that can handle
//       System.out.println(findMethod() + " the found method");
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
        return extractPath(splitFirstLineWithParams[SECOND_ELEMENT]);
    }

    private void parseAndReturnData() {
        if (request.contains("/parameters?")) {
            data = decodeParameters(separatePathFromParams(splitFirstLineWithParams[SECOND_ELEMENT]));
        } else if (!request.contains("/parameters?")) {
            data = createTable(getMatchingStrings(EQUAL_SIGN), EQUAL_SIGN);
        }
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

    private String extractPath(String lineToParse) {
        String[] getPath = lineToParse.split("\\?");

        return getPath[FIRST_ELEMENT];
    }

    private String separatePathFromParams(String toSeparate) {
        String[] getPath = toSeparate.split("\\?");

        return getPath[SECOND_ELEMENT];
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
        } else {
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
