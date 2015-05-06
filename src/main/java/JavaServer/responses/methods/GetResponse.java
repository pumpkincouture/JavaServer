package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.FileWriter;

import java.io.IOException;

public class GetResponse extends Response {
    private FileWriter fileWriter;
    private Request request;

    public GetResponse(FileWriter fileWriter, Request request) {
        this.fileWriter = fileWriter;
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
        if (fileWriter.doesFileExist()) {
            return fileWriter.getDirectoryLinks();
        } else if (request.getPath().equals("/form")) {
            fileWriter.getDataFileContents();
        }
        return EMPTY_STRING;
    }
}
