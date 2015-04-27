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
        return "";
    }

    @Override
    public String getCorrectBody(Request request) {
        if (fileManager.isFileImage()) {

        }
        return fileManager.getFileContents();
    }
}
