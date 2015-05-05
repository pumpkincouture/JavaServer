package JavaServer.helpers;

import java.util.Map;

public class StringMaker {

    public String turnDataIntoString(Map<String, String> updatedData, String typeOfEqualitySign) {
        String requestDataString = "";
        for (Map.Entry<String, String> entry : updatedData.entrySet()) {
            requestDataString += entry.getKey() + typeOfEqualitySign + entry.getValue();
        }
        return requestDataString;
    }

    public String turnDataIntoString(Map<String, String> patchData) {
        String patchDataString = "";
        for (Map.Entry<String, String> entry : patchData.entrySet()) {
            patchDataString += entry.getValue() + " ";
        }
        return patchDataString;
    }
}
