package JavaServer.responses.methods;

import JavaServer.responses.FileAdmin;
import JavaServer.helpers.StringMaker;

import java.io.IOException;
import java.util.Map;

public class PostResponse extends Response {
    private FileAdmin fileWriter;
    private String requestPath;
    private Map<String, String> requestData;

    public PostResponse(FileAdmin fileWriter, String requestPath, Map<String, String> requestData) {
        this.fileWriter = fileWriter;
        this.requestPath = requestPath;
        this.requestData = requestData;
    }

    @Override
    public String getCorrectStatus() throws IOException {
        if (requestPath.equals("/form")) {
            fileWriter.setDataInResource(getFormattedString());
            return getCodes().get("200");
        } else if (requestPath.equals("/text-file.txt")) {
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

    private String getFormattedString() {
        return new StringMaker().turnDataIntoString(requestData, EQUAL_SIGN);
    }
}
