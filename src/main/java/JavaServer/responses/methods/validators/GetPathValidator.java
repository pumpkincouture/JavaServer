package JavaServer.responses.methods.validators;

public class GetPathValidator extends PathValidator {

    @Override
    public String returnCorrectStatus(String requestPath) {
        if (isValidPath(requestPath, "directory") || isValidPath(requestPath, "options") || isValidPath(requestPath, "file_1")) {
            return getCodes().get("200");
        }
        return "";
        }

    @Override
    public String returnCorrectHeader(String requestPath) {
        if (isValidPath(requestPath, "redirect")) {
            return getHeaders().get("location");
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
