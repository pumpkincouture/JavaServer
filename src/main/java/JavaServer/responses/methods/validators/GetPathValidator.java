package JavaServer.responses.methods.validators;

public class GetPathValidator extends PathValidator {

    @Override
    public String returnCorrectStatus(String requestPath) {
        if (isValidPath(requestPath, "directory") || isValidPath(requestPath, "options") || isValidPath(requestPath, "file_1")) {
            return getCodes().get("200");
//        } else if (isValidPath(requestPath, "redirect")) {
//            return getCodes().get("302");
//        }
        }
        else {
            return getCodes().get("404");
        }
    }

    @Override
    public String returnCorrectHeader(String requestPath) {
        if (isValidPath(requestPath, "redirect")) {
            return getHeaders().get("location");
        } else if (isValidPath(requestPath, "options")) {
            return getHeaders().get("options");
        }
        return emptyString();
    }

    @Override
    public String returnCorrectBody() {
        return null;
    }

    @Override
    public boolean correctMethodForBodyContents(String requestPath) {
        return requestPath.equals(getAvailablePaths().get("directory"));
    }

    @Override
    public boolean doesPathEqualFile(String requestPath) {
        return requestPath.equals(getAvailablePaths().get("file_1"));
    }
}
