package JavaServer.ResponseManagers.MethodManagers;

import JavaServer.RequestManagers.Request;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class PostManager extends RequestManager {
    private static final String FORM_PATH = "/form";
    private static final String PATH_TO_FILE = "public/postData.txt";
    private static final String ENCODING = "UTF-8";

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

    private void saveData(HashMap<String, String> data) {
        for (int i = 0; i < data.size(); i++) {
            System.out.println(i);
        }
            PrintWriter writer = null;

        try {
            writer = new PrintWriter(PATH_TO_FILE, ENCODING);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < data.size(); i++) {
            writer.write(i);
        }
        writer.close();
    }
}
