package JavaServer.responses.methods;

import JavaServer.responses.FileManager;

public class GetResponse extends Response {
    private FileManager fileManager;
    private String requestPath;

    public GetResponse(FileManager fileManager, String requestPath) {
        this.fileManager = fileManager;
        this.requestPath = requestPath;
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
        if (fileManager.doesFileExist()) {
            return fileManager.getDirectoryLinks();
        } else if (requestPath.equals("/form")) {
            fileManager.getDataFileContents();
        }
        return EMPTY_STRING;
    }
}
