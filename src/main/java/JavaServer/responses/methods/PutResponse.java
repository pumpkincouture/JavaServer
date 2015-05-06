package JavaServer.responses.methods;

import JavaServer.responses.FileWriter;
import JavaServer.helpers.StringMaker;

import java.util.Map;

public class PutResponse extends Response {
    private FileWriter fileWriter;
    private String requestPath;
    private Map<String, String> requestData;

    public PutResponse(FileWriter fileWriter, Map<String, String> requestData, String requestPath) {
        this.fileWriter = fileWriter;
        this.requestPath = requestPath;
        this.requestData = requestData;
    }

    @Override
    public String getCorrectStatus() {
        if (requestPath.equals("/form")) {
            fileWriter.setDataInResource(new StringMaker().turnDataIntoString(requestData, EQUAL_SIGN));
            return getCodes().get("200");
        } else if (requestPath.equals("/file1")) {
            return getCodes().get("405");
        }
        return getCodes().get("404");
    }

    @Override
    public String getCorrectHeaders() {
        return EMPTY_STRING;
    }

    @Override
    public String getCorrectBody() {
        return EMPTY_STRING;
    }
}
