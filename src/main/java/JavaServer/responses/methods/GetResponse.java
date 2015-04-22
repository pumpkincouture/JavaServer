package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.responses.FileManager;
import JavaServer.responses.methods.validators.GetPathValidator;
import JavaServer.responses.methods.validators.PathValidator;

public class GetResponse extends Response {
    private FileManager fileManager;
    private PathValidator pathValidator;
    public static final String EMPTY_STRING = "";

    public GetResponse(FileManager fileManager) {
        this.fileManager = fileManager;
        pathValidator = new GetPathValidator();
    }

    @Override
    public String getCorrectStatus(Request request) {
        return pathValidator.returnCorrectStatus(request.getPath());
    }

    @Override
    public String getCorrectHeaders(Request request) {
       return pathValidator.returnCorrectHeader(request.getPath());
    }

    @Override
    public String getCorrectBody(Request request) {
        if (fileManager.doesFileExist() && pathValidator.correctMethodForBodyContents(request.getPath())) {
            return fileManager.getDirectoryLinks();
        } else if (pathValidator.doesPathEqualFile(request.getPath())) {
            return fileManager.getFileContents();
        }
        return EMPTY_STRING;
    }

}
