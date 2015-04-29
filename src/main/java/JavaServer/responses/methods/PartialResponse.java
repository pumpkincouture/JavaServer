package JavaServer.responses.methods;

import JavaServer.responses.FileManager;

import java.io.IOException;

public class PartialResponse extends Response {
    private FileManager fileManager;
    private String byteRange;

    public PartialResponse(FileManager fileManager, String byteRange) {
        this.fileManager = fileManager;
        this.byteRange = byteRange;
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
        return EMPTY_STRING;
    }
}
