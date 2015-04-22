package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.FileManager;

public class GetResponse extends Response {
    private static final String SIMPLE_PATH = "/";
    private static final String METHOD_OPTIONS = "/method_options";
    public static final String REDIRECT = "/redirect";
    private FileManager fileManager;

    public GetResponse(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public String getCorrectStatus(Request request) {
        if (request.getPath().equals(SIMPLE_PATH) || (request.getPath().equals(METHOD_OPTIONS)) || request.getPath().equals("/file1")) {
            return getCodes().get("200");
        } else if (request.getPath().equals(REDIRECT)) {
            return getCodes().get("302");
        }
        else {
            return getCodes().get("404");
        }
    }

    @Override
    public String getCorrectHeaders(Request request) {
        if (request.getPath().equals(REDIRECT)) {
            return getHeaders().get("location");
        } else if (request.getPath().equals(METHOD_OPTIONS)) {
            return getHeaders().get("options");
        }
        return "";
    }

    @Override
    public String getCorrectBody(Request request) {
        if (fileManager.doesFileExist() && request.getPath().equals("/")) {
            return fileManager.getDirectoryLinks();
        } else if (request.getPath().equals("/file1")) {
            return fileManager.getFileContents();
        }
        return "";
    }

}
