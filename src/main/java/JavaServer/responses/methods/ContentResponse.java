package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.FileManager;

public class ContentResponse extends Response {
    private FileManager fileManager;

    public ContentResponse(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public String getCorrectStatus(Request request) {
        return getCodes().get("200");
    }

    @Override
    public String getCorrectHeaders(Request request) {
        return EMPTY_STRING;
    }

    @Override
    public String getCorrectBody(Request request) {
        fileManager.getFileContents();
        return EMPTY_STRING;
    }
}
