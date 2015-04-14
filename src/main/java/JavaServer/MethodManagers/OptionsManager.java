package JavaServer.MethodManagers;

import JavaServer.RequestManagers.Request;

public class OptionsManager extends RequestManager {

    @Override
    public String manage(Request request) {
        return getCorrectResponse(request.getPath(), "/method_options");
    }
}
