package JavaServer.MethodManagers;

import JavaServer.RequestManagers.Request;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class PostManager extends RequestManager {
    private static final String FORM_PATH = "/form";
    private static final String PATH_TO_FILE = "public/postData.txt";
    private static final String ENCODING = "UTF-8";

    @Override
    public String manage(Request request) {
        saveData(request.getData());
        return getCorrectResponse(request.getPath(), FORM_PATH);
    }

    private void saveData(String data) {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(PATH_TO_FILE, ENCODING);
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        writer.println(data);
        writer.close();
    }
}
