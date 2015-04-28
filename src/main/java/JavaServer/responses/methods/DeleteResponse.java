package JavaServer.responses.methods;

import JavaServer.responses.DataManager;

public class DeleteResponse extends Response {
    private DataManager dataManager;
    private String requestPath;

    public DeleteResponse(DataManager dataManager, String requestPath) {
        this.dataManager = dataManager;
        this.requestPath = requestPath;
    }

    @Override
    public String getCorrectStatus() {
        if (requestPath.equals("/form")) {
            dataManager.resetData();
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
