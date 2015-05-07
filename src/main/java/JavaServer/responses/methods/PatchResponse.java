package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.helpers.StringMaker;
import JavaServer.responses.ResourceWriter;

import java.io.IOException;

public class PatchResponse extends Response {
    private Request request;
    private ResourceWriter resourceWriter;

    public PatchResponse(ResourceWriter resourceWriter, Request request) {
        this.resourceWriter = resourceWriter;
        this.request = request;
    }

    @Override
    public String getCorrectStatus() throws IOException {
        if (containsEtagAuthorization()) {
            resourceWriter.patchFileWithNewData(getFormattedString());
        }
        return getCodes().get("204");
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

    private String getFormattedString() {
        return new StringMaker().turnDataIntoString(request.getData());
    }
}
