package JavaServer.responses;

import java.util.Map;

public class DataManager {
    private String requestDataString = "";

    public void updateRequestData(Map<String, String> updatedData) {
        this.requestDataString = turnDataIntoString(updatedData);
    }

    public String getRequestData() {
        return this.requestDataString;
    }

    public void resetData() {
        this.requestDataString = "";
    }

    private String turnDataIntoString(Map<String, String> updatedData) {
        String dataString = "";

        for (Map.Entry<String, String> entry : updatedData.entrySet()) {
            dataString += entry.getKey() + "=" + entry.getValue();
        }
        return dataString;
    }

}
