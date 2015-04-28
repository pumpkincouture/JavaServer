package JavaServer.responses.methods;

import JavaServer.responses.DataManager;

public class DeleteResponse extends Response {
    private DataManager dataManager;

    public DeleteResponse(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public String getCorrectStatus() {
        if (request.getPath().equals("/form")) {
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
