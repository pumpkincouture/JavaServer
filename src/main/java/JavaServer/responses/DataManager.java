package JavaServer.responses;

import java.util.Map;

public class DataManager {
    private String requestDataString = "";
    private String patchDataString = "";

    public void updateRequestData(Map<String, String> updatedData, String typeOfEqualitySign) {
        turnDataIntoString(updatedData, typeOfEqualitySign);
    }

    public void updateRequestData(Map<String, String> patchedData) {
        turnDataIntoString(patchedData);
    }

    public String getPatchedData() {
        return this.patchDataString;
    }

    public String getRequestData() {
        return this.requestDataString;
    }

    public void resetData() {
        this.requestDataString = "";
    }

    private void turnDataIntoString(Map<String, String> patchData) {
        for (Map.Entry<String, String> entry : patchData.entrySet()) {
            patchDataString += entry.getValue() + " ";
        }
    }

    private void turnDataIntoString(Map<String, String> updatedData, String typeOfEqualitySign) {
        for (Map.Entry<String, String> entry : updatedData.entrySet()) {
            requestDataString += entry.getKey() + typeOfEqualitySign + entry.getValue();
        }
    }
}
