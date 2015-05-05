package JavaServer.responses.methods;

import JavaServer.responses.FileManager;
import JavaServer.helpers.StringMaker;

import java.io.IOException;
import java.util.Map;

public class ParamResponse extends Response {
    private FileManager fileManager;
    private Map<String, String> requestData;

    public ParamResponse(Map<String, String> requestData, FileManager fileManager) {
        this.requestData = requestData;
        this.fileManager = fileManager;
    }

    @Override
    public String getCorrectStatus() {
        return getCodes().get("200");
    }

    @Override
    public String getCorrectHeaders() {
        return EMPTY_STRING;
    }

    @Override
    public String getCorrectBody() throws IOException {
        fileManager.setDataInResource(new StringMaker().turnDataIntoString(requestData, EQUALITY_WITH_SPACES));
        fileManager.getDataFileContents();
        return EMPTY_STRING;
    }
}
