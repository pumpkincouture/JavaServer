package JavaServer.MethodManagers;

import JavaServer.RequestManagers.Request;

public class OptionsManager extends RequestManager {
    private static final String OPTIONS_PATH = "/method_options";

    @Override
    public String manage(Request request) {
        return getCorrectResponse(request.getPath(), OPTIONS_PATH);
    }
}
