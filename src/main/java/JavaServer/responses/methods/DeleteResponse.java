package JavaServer.responses.methods;

import JavaServer.responses.FileManager;

public class DeleteResponse extends Response {
    private FileManager fileManager;
    private String requestPath;

    public DeleteResponse(FileManager fileManager, String requestPath) {
        this.fileManager = fileManager;
        this.requestPath = requestPath;
    }

    @Override
    public String getCorrectStatus() {
        if (requestPath.equals("/form")) {
            fileManager.deleteFromResource();
        }
        return getCodes().get("200");
    }

    @Override
    public String getCorrectHeaders() {
        return EMPTY_STRING;
    }

    @Override
    public String getCorrectBody() {
        return EMPTY_STRING;
    }
}
