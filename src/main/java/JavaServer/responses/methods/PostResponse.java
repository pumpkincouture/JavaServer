package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.DataManager;

public class PostResponse extends Response {
    private DataManager dataManager;

    public PostResponse(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public String getCorrectStatus(Request request) {
        if (request.getPath().equals("/form")) {
            dataManager.updateRequestData(request.getData());
            return getCodes().get("200");
        } else if (request.getPath().equals("/text-file.txt")) {
            return getCodes().get("405");
        }
        return getCodes().get("404");
    }

    @Override
    public String getCorrectHeaders(Request request) {
        return EMPTY_STRING;
    }

    @Override
    public String getCorrectBody(Request request) {
        return EMPTY_STRING;
    }
}
