package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.FileManager;
import JavaServer.responses.StringMaker;

import java.io.IOException;

public class PatchResponse extends Response {
    private Request request;
    private FileManager fileManager;

    public PatchResponse(FileManager fileManager, Request request) {
        this.fileManager = fileManager;
        this.request = request;
    }

    @Override
    public String getCorrectStatus() {
        if (containsEtagAuthorization()) {
            try {
                fileManager.patchFileWithNewData(new StringMaker().turnDataIntoString(request.getData()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return getCodes().get("204");
        }
        return getCodes().get("200");
    }

    @Override
    public String getCorrectHeaders() {
        return EMPTY_STRING;
    }

    @Override
    public String getCorrectBody() throws IOException {
        return EMPTY_STRING;
    }

    private boolean containsEtagAuthorization() {
        return request.getHeaders().get("If-Match") != null;
    }
}
