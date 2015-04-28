package JavaServer.responses;

import java.util.Map;

public class DataManager {
    private String requestDataString = "";
    private String patchDataString = "";

    public void updateRequestData(Map<String, String> updatedData, String typeOfEqualitySign) {
        this.requestDataString = turnDataIntoString(updatedData, typeOfEqualitySign);
    }

    public void updatePatchedContent(Map<String, String> patchedData) {
        this.patchDataString = getPatchString(patchedData);
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

    private String getPatchString(Map<String, String> patchData) {
        String patchedDataString = "";

        for (Map.Entry<String, String> entry : patchData.entrySet()) {
            patchedDataString += entry.getValue() + " ";
        }
        return patchedDataString;
    }

    private String turnDataIntoString(Map<String, String> updatedData, String typeOfEqualitySign) {
        String dataString = "";

        for (Map.Entry<String, String> entry : updatedData.entrySet()) {
            dataString += entry.getKey() + typeOfEqualitySign + entry.getValue();
        }
        return dataString;
    }
}
