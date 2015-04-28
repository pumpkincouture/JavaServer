package JavaServer.responses;

import java.util.Map;

public class DataManager {
    private String requestDataString = "";

    public void updateRequestData(Map<String, String> updatedData, String typeOfEqualitySign) {
        this.requestDataString = turnDataIntoString(updatedData, typeOfEqualitySign);
    }

    public String getRequestData() {
        return this.requestDataString;
    }

    public void resetData() {
        this.requestDataString = "";
    }

    private String turnDataIntoString(Map<String, String> updatedData, String typeOfEqualitySign) {
        String dataString = "";

        for (Map.Entry<String, String> entry : updatedData.entrySet()) {
            dataString += entry.getKey() + typeOfEqualitySign + entry.getValue();
        }
        return dataString;
    }
}
