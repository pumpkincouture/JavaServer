package JavaServer.responses.methods;

import JavaServer.responses.DataManager;

public class PutResponse extends Response {
    private DataManager dataManager;

    public PutResponse(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public String getCorrectStatus() {
        if (request.getPath().equals("/form")) {
            dataManager.resetData();
            dataManager.updateRequestData(request.getData());
            return getCodes().get("200");
        } else if (request.getPath().equals("/file1")) {
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
