package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.FileManager;

import java.util.Map;

public class GetResponse extends Response {
    private FileManager fileManager;
    public static final String EMPTY_STRING = "";

    public GetResponse(FileManager fileManager) {
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
        if (fileManager.doesFileExist()) {
            return fileManager.getDirectoryLinks();
        } else if (request.getPath().equals("/form")) {
            System.out.println(getRequestData());
            return getRequestData();
        }
        return EMPTY_STRING;
    }
}
