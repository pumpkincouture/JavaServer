package JavaServer.responses.methods;

import JavaServer.responses.FileAdmin;

public class DeleteResponse extends Response {
    private FileAdmin fileWriter;
    private String requestPath;

    public DeleteResponse(FileAdmin fileManager, String requestPath) {
        this.fileWriter = fileManager;
        this.requestPath = requestPath;
    }

    @Override
    public String getCorrectStatus() {
        if (requestPath.equals("/form")) {
            fileWriter.deleteDataFromResource();
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
