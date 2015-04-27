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
    static final String AUTHORIZATION = "Authorization";

    String request;
    String[] splitFirstLine;
    String[] splitRequest;

    public RequestParser(String request) {
        this.request = request;
        splitFirstLine = request.split(EMPTY_SPACE , ELEMENTS_IN_REQUEST_LINE);
        splitRequest = request.split((blankLine()));
    }

   public String getMethod() {
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
