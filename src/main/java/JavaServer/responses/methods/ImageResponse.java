package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.FileManager;

import java.io.IOException;

public class ImageResponse extends Response {
    private FileManager fileManager;

    public ImageResponse(FileManager fileManager) {
        this.fileManager = fileManager;
    }
    @Override
    public String getCorrectStatus(Request request) {
        return getCodes().get("200");
    }

    @Override
    public String getCorrectHeaders(Request request) {
        return "";
    }

    @Override
    public String getCorrectBody(Request request) throws IOException {
        fileManager.getImageContents();
        return "";
    }
}
