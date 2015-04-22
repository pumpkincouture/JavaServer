package JavaServer.responses.methods.validators;

public class OptionsPathValidator extends PathValidator {

    @Override
    public String returnCorrectStatus(String requestPath) {
        if (isValidPath(requestPath, "options")) {
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
        return emptyString();
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
