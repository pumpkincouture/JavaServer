package JavaServer.responses.methods;

import JavaServer.responses.FileManager;

public class ContentResponse extends Response {
    private FileManager fileManager;

    public ContentResponse(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public String getCorrectStatus() {
        return getCodes().get("200");
    }

    @Override
    public String getCorrectHeaders() {
        return EMPTY_STRING;
    }

    @Override
    public String getCorrectBody() {
        fileManager.getFileContents();
        return EMPTY_STRING;
    }
}
