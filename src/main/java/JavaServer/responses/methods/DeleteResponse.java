package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.DataManager;

import java.io.IOException;

public class DeleteResponse extends Response {
    private DataManager dataManager;

    public DeleteResponse(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public String getCorrectStatus(Request request) {
        if (request.getPath().equals("/form")) {
            dataManager.resetData();
        }
        return getCodes().get("200");
    }

    @Override
    public String getCorrectHeaders(Request request) {
        return "";
    }

    @Override
    public String getCorrectBody(Request request) {
        return "";
    }
}
