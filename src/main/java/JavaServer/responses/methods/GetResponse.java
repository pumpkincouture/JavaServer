package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.DataManager;
import JavaServer.responses.FileManager;

public class GetResponse extends Response {
    private FileManager fileManager;
    private DataManager dataManager;
    public static final String EMPTY_STRING = "";

    public GetResponse(FileManager fileManager, DataManager dataManager) {
        this.fileManager = fileManager;
        this.dataManager = dataManager;
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
        if (fileManager.doesFileExist()) {
            return fileManager.getDirectoryLinks();
        } else if (request.getPath().equals("/form")) {
            return dataManager.getRequestData();
        }
        return EMPTY_STRING;
    }
}
