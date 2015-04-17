package JavaServer.ResponseManagers.MethodManagers;

import JavaServer.RequestManagers.Request;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class PutManager extends RequestManager {
    private static final String FORM_PATH = "/form";
    private static final String PATH_TO_FILE = "public/postData.txt";


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

    private void updateData(HashMap<String, String> data) throws IOException {
        File file = new File(PATH_TO_FILE);

        if (data.size() > 0) {
            FileWriter writer = new FileWriter(file, false);
            for (int i = 0; i < data.size(); i++) {
                writer.write(i);
            }
            writer.close();
        }
    }
}
