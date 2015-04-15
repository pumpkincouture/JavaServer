package JavaServer.MethodManagers;

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
        saveData(request.getData());
        return getCorrectResponse(request.getPath(), FORM_PATH);
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
            System.out.println(i);
            writer.write(i);
        }
        writer.close();
    }
}
