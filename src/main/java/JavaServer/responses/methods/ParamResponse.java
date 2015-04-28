package JavaServer.responses.methods;

import JavaServer.responses.DataManager;

import java.io.IOException;
import java.util.Map;

public class ParamResponse extends Response {
    private DataManager dataManager;
    private Map<String, String> requestData;

    public ParamResponse(DataManager dataManager, Map<String, String> requestData) {
        this.dataManager = dataManager;
        this.requestData = requestData;
    }

    @Override
    public String getCorrectStatus() {
        return EMPTY_STRING;
    }

    @Override
    public String getCorrectHeaders() {
        return EMPTY_STRING;
    }

    @Override
    public String getCorrectBody() throws IOException {
        dataManager.updateRequestData(requestData);
        System.out.println(dataManager.getRequestData());
        return dataManager.getRequestData();
    }
}
