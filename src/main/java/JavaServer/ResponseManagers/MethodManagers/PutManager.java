package JavaServer.ResponseManagers.MethodManagers;

import JavaServer.RequestManagers.Request;

public class PutManager extends RequestManager {
    private static final String FORM_PATH = "/form";

    @Override
    public String manage(Request request) {
        if (request.getPath().equals(FORM_PATH) || (request.getPath().equals("/method_options"))) {
            return getCodes().get("200");
        } else if (request.getPath().equals("/file1")) {
            return getCodes().get("405");
//            return getCodes().get("404");
        }
        return getCodes().get("404");
//        try {
//            updateData(request.getData());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return getCorrectResponse(request.getPath(), FORM_PATH);
    }

    @Override
    public String getCorrectHeaders() {
        return getHeaders().get("options");
    }
}
