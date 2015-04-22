package JavaServer.responses.methods;

public class OptionsPathValidator extends PathValidator{
    private static final String OPTIONS_PATH = "/method_options";
    public static final String EMPTY_STRING = "";

    @Override
    public String returnCorrectStatus(String requestPath) {
        if (requestPath.equals(OPTIONS_PATH)) {
            return getCodes().get("200");
        }
        return getCodes().get("404");
    }

    @Override
    public String returnCorrectHeader(String requestPath) {
        return getHeaders().get("options");
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
