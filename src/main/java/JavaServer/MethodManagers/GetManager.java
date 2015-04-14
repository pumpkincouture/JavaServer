package JavaServer.MethodManagers;

import JavaServer.RequestManagers.Request;

public class GetManager extends RequestManager {

    @Override
    public String manage(Request request) {
        return getCorrectResponse(request.getPath(), "/");
    }
}
