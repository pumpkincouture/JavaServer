package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.FileAdmin;

import java.io.IOException;

public class GetResponse extends Response {
    private FileAdmin fileAdmin;
    private Request request;

    public GetResponse(FileAdmin fileAdmin, Request request) {
        this.fileAdmin = fileAdmin;
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
        if (fileAdmin.doesFileExist()) {
            return fileAdmin.getDirectoryLinks();
        } else if (request.getPath().equals("/form")) {
            fileAdmin.getDataFileContents();
        }
        return EMPTY_STRING;
    }
}
