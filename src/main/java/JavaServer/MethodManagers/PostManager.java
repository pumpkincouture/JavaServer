package JavaServer.MethodManagers;

import JavaServer.RequestManagers.Request;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class PostManager extends RequestManager {

    @Override
    public String manage(Request request) {
        saveData(request.getData());
        return getCorrectResponse(request.getPath(), "/form");
    }

    private void saveData(String data) {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter("public/postData.txt", "UTF-8");
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        writer.println(data);
        writer.close();
    }
}
