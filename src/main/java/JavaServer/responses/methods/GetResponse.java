package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.FileManager;

import java.io.IOException;

public class GetResponse extends Response {
    private FileManager fileManager;
    private Request request;

    public GetResponse(FileManager fileManager, Request request) {
        this.fileManager = fileManager;
        this.request = request;
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
        if (fileManager.doesFileExist()) {
            return fileManager.getDirectoryLinks();
        } else if (request.getPath().equals("/form")) {
            fileManager.getDataFileContents();
        }
        return EMPTY_STRING;
    }
}
