package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.FileManager;

import java.io.IOException;

public class ContentResponse extends Response {
    private FileManager fileManager;
    private Request request;

    public ContentResponse(FileManager fileManager, Request request) {
        this.fileManager = fileManager;
        this.request = request;
    }

    @Override
    public String getCorrectStatus() {
        if (hasRange()) {
            return getCodes().get("206");
        }
        return getCodes().get("200");
    }

    @Override
    public String getCorrectHeaders() {
        return EMPTY_STRING;
    }

    @Override
    public String getCorrectBody() throws IOException {
        if (hasRange()) {
            fileManager.getPartialFileContents(request.getHeaders());
        } else {
            fileManager.getFileContents();
        }
        return EMPTY_STRING;
    }

    private boolean hasRange() {
        return request.getHeaders().get("Range") != null;
    }
}
