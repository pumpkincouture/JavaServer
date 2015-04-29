package JavaServer.responses.methods;

import JavaServer.responses.FileManager;

import java.io.IOException;
import java.util.Map;

public class PartialResponse extends Response {
    private FileManager fileManager;
    private Map<String, String> requestHeaders;

    public PartialResponse(FileManager fileManager, Map<String, String> requestHeaders) {
        this.fileManager = fileManager;
        this.requestHeaders = requestHeaders;
    }

    @Override
    public String getCorrectStatus() {
        return getCodes().get("206");
    }

    @Override
    public String getCorrectHeaders() {
        return EMPTY_STRING;
    }

    @Override
    public String getCorrectBody() throws IOException {
        fileManager.getPartialFileContents(requestHeaders);
        return EMPTY_STRING;
    }
}
