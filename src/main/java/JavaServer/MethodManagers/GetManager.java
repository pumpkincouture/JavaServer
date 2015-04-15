package JavaServer.MethodManagers;

import JavaServer.RequestManagers.Request;

public class GetManager extends RequestManager {
    private static final String SIMPLE_PATH = "/";

    @Override
    public String manage(Request request) {
        return getCorrectResponse(request.getPath(), SIMPLE_PATH);
    }
}
