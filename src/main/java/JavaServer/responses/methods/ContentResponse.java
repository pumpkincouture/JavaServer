package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.FileWriter;
import JavaServer.helpers.RangeFinder;

import java.io.IOException;

public class ContentResponse extends Response {
    private FileWriter fileWriter;
    private Request request;

    public ContentResponse(FileWriter fileManager, Request request) {
        this.fileWriter = fileManager;
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
            fileWriter.getPartialFileContents(new RangeFinder(request.getHeaders()));
        } else {
            fileWriter.getFileContents();
        }
        return EMPTY_STRING;
    }

    private boolean hasRange() {
        return request.getHeaders().get("Range") != null;
    }
}
