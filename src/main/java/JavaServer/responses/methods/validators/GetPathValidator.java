package JavaServer.responses.methods.validators;

public class GetPathValidator extends PathValidator {
    private static final String DIRECTORY = "/";
    private static final String METHOD_OPTIONS = "/method_options";
    public static final String REDIRECT = "/redirect";
    public static final String FILE_1 = "/file1";
    public static final String EMPTY_STRING = "";

    @Override
    public String returnCorrectStatus(String requestPath) {
        if (requestPath.equals(DIRECTORY) || (requestPath.equals(METHOD_OPTIONS)) || requestPath.equals(FILE_1)) {
            return getCodes().get("200");
        } else if (requestPath.equals(REDIRECT)) {
            return getCodes().get("302");
        }
        else {
            return getCodes().get("404");
        }
    }

    @Override
    public String returnCorrectHeader(String requestPath) {
        if (requestPath.equals(REDIRECT)) {
            return getHeaders().get("location");
        } else if (requestPath.equals(METHOD_OPTIONS)) {
            return getHeaders().get("options");
        }
        return EMPTY_STRING;
    }

    @Override
    public String returnCorrectBody() {
        return null;
    }

    @Override
    public boolean correctMethodForBodyContents(String requestPath) {
        return requestPath.equals(DIRECTORY);
    }

    @Override
    public boolean doesPathEqualFile(String requestPath) {
        return requestPath.equals(FILE_1);
    }
}
