package JavaServer.responses.methods.validators;

import JavaServer.responses.methods.validators.PathValidator;

public class PostPathValidator extends PathValidator {
    private static final String FORM_PATH = "/form";
    public static final String METHOD_OPTIONS = "/method_options";
    public static final String TEXT_FILE = "/text-file.txt";
    public static final String EMPTY_STRING = "";

    @Override
    public String returnCorrectStatus(String requestPath) {
        if (requestPath.equals(FORM_PATH) || (requestPath.equals(METHOD_OPTIONS))) {
            return getCodes().get("200");
        } else if (requestPath.equals(TEXT_FILE)) {
            return getCodes().get("405");
        }
        return getCodes().get("404");
    }

    @Override
    public String returnCorrectHeader(String requestPath) {
        if (requestPath.equals(METHOD_OPTIONS)) {
            return getHeaders().get("options");
        }
        return EMPTY_STRING;
    }

    @Override
    public String returnCorrectBody() {
        return EMPTY_STRING;
    }

    @Override
    public boolean correctMethodForBodyContents(String requestPath) {
        return false;
    }

    @Override
    public boolean doesPathEqualFile(String requestPath) {
        return false;
    }
}
