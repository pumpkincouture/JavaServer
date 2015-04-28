package JavaServer.responses.methods;

import JavaServer.responses.DataManager;

import java.util.Map;

public class PostResponse extends Response {
    private DataManager dataManager;
    private String requestPath;
    private Map<String, String> requestData;

    public PostResponse(DataManager dataManager, String requestPath, Map<String, String> requestData) {
        this.dataManager = dataManager;
        this.requestPath = requestPath;
        this.requestData = requestData;
    }

    @Override
    public String getCorrectStatus() {
        if (requestPath.equals("/form")) {
            dataManager.updateRequestData(requestData);
            return getCodes().get("200");
        } else if (requestPath.equals("/text-file.txt")) {
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
