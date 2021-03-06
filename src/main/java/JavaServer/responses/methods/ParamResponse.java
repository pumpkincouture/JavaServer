package JavaServer.responses.methods;

import JavaServer.responses.FileAdmin;
import JavaServer.helpers.StringMaker;

import java.io.IOException;
import java.util.Map;

public class ParamResponse extends Response {
    private FileAdmin fileAdmin;
    private Map<String, String> requestData;

    public ParamResponse(Map<String, String> requestData, FileAdmin fileAdmin) {
        this.requestData = requestData;
        this.fileAdmin = fileAdmin;
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
        fileAdmin.setDataInResource(getFormattedString());
        fileAdmin.getDataFileContents();
        return EMPTY_STRING;
    }

    private String getFormattedString() {
        return new StringMaker().turnDataIntoString(requestData, EQUALITY_WITH_SPACES);
    }
}
