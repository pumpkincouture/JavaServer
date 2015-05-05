package JavaServer.responses.methods;

import JavaServer.responses.FileManager;

import java.io.IOException;
import java.util.Map;

public class PatchResponse extends Response {
    private Map<String, String> requestdata;
    private FileManager fileManager;

    public PatchResponse(Map<String, String> requestData, FileManager fileManager) {
        this.requestdata = requestData;
        this.fileManager = fileManager;
    }

    @Override
    public String getCorrectStatus() {
        try {
            fileManager.patchFileWithNewData(fileManager.turnDataIntoString(requestdata));
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
