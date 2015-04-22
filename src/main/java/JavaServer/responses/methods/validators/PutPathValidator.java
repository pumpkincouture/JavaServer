package JavaServer.responses.methods.validators;

public class PutPathValidator extends PathValidator {

    @Override
    public String returnCorrectStatus(String requestPath) {
        if (isValidPath(requestPath, "form") || (isValidPath(requestPath, "options"))) {
            return getCodes().get("200");
        } else if (isValidPath(requestPath, "file_1")) {
            return getCodes().get("405");
        }
        return getCodes().get("404");
    }

    @Override
    public String returnCorrectHeader(String requestPath) {
//        if (isValidPath(requestPath, "options")) {
//            return getHeaders().get("options");
//        }
        return emptyString();
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
