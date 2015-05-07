package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.FileAdmin;
import JavaServer.helpers.RangeFinder;

import java.io.IOException;

public class ContentResponse extends Response {
    private FileAdmin fileAdmin;
    private Request request;

    public ContentResponse(FileAdmin fileManager, Request request) {
        this.fileAdmin = fileManager;
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
            fileAdmin.getPartialFileContents(new RangeFinder(request.getHeaders()));
        } else {
            fileAdmin.getFileContents();
        }
        return EMPTY_STRING;
    }

    private boolean hasRange() {
        return request.getHeaders().get("Range") != null;
    }
}
