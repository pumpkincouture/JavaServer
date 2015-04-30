package JavaServer.responses.methods;

import JavaServer.responses.DataManager;
import JavaServer.responses.FileManager;

import java.io.IOException;
import java.util.Map;

public class PatchResponse extends Response {
    private DataManager dataManager;
    private Map<String, String> requestdata;
    private FileManager fileManager;

    public PatchResponse(DataManager dataManager, Map<String, String> requestData, FileManager fileManager) {
        this.dataManager = dataManager;
        this.requestdata = requestData;
        this.fileManager = fileManager;
    }

    @Override
    public String getCorrectStatus() {
        dataManager.updateRequestData(requestdata);
        try {
            fileManager.patchFile(dataManager.getPatchedData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getCodes().get("204");
    }

    @Override
    public String getCorrectHeaders() {
        return EMPTY_STRING;
    }

    @Override
    public String getCorrectBody() throws IOException {
        return EMPTY_STRING;
    }
}
