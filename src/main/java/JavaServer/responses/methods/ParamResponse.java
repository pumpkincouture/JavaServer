package JavaServer.responses.methods;

import JavaServer.responses.FileWriter;
import JavaServer.helpers.StringMaker;

import java.io.IOException;
import java.util.Map;

public class ParamResponse extends Response {
    private FileWriter fileWriter;
    private Map<String, String> requestData;

    public ParamResponse(Map<String, String> requestData, FileWriter fileWriter) {
        this.requestData = requestData;
        this.fileWriter = fileWriter;
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
        fileWriter.setDataInResource(getFormattedString());
        fileWriter.getDataFileContents();
        return EMPTY_STRING;
    }

    private String getFormattedString() {
        return new StringMaker().turnDataIntoString(requestData, EQUALITY_WITH_SPACES);
    }
}
