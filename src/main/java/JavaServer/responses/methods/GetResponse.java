package JavaServer.responses.methods;

import JavaServer.responses.DataManager;
import JavaServer.responses.FileManager;

public class GetResponse extends Response {
    private FileManager fileManager;
    private DataManager dataManager;
    private String requestPath;

    public GetResponse(FileManager fileManager, DataManager dataManager, String requestPath) {
        this.fileManager = fileManager;
        this.dataManager = dataManager;
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
            return dataManager.getRequestData();
        }
        return EMPTY_STRING;
    }
}
