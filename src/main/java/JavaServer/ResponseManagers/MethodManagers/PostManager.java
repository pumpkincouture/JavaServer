package JavaServer.ResponseManagers.MethodManagers;

import JavaServer.RequestManagers.Request;

public class PostManager extends RequestManager {
    private static final String FORM_PATH = "/form";

    @Override
    public String manage(Request request) {
        if (request.getPath().equals(FORM_PATH) || (request.getPath().equals("/method_options"))) {
            return getCodes().get("200");
        } else if (request.getPath().equals("/text-file.txt")) {
            return getCodes().get("405");
//            return getCodes().get("404");
        }
        return getCodes().get("404");
//        saveData(request.getData());
//        return getCorrectResponse(request.getPath(), FORM_PATH);
    }

    @Override
    public String getCorrectHeaders() {
        return getHeaders().get("options");
    }
}
