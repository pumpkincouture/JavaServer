package JavaServer.responses.methods;

import JavaServer.responses.FileManager;
import JavaServer.helpers.StringMaker;

import java.util.Map;

public class PutResponse extends Response {
    private FileManager fileManager;
    private String requestPath;
    private Map<String, String> requestData;

    public PutResponse(FileManager fileManager, Map<String, String> requestData, String requestPath) {
        this.fileManager = fileManager;
        this.requestPath = requestPath;
        this.requestData = requestData;
    }

    @Override
    public String getCorrectStatus() {
        if (requestPath.equals("/form")) {
            fileManager.setDataInResource(new StringMaker().turnDataIntoString(requestData, EQUAL_SIGN));
            return getCodes().get("200");
        } else if (requestPath.equals("/file1")) {
            return getCodes().get("405");
        }
        return getCodes().get("404");
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
