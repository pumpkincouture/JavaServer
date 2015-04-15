package JavaServer.MethodManagers;

import JavaServer.RequestManagers.Request;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PutManager extends RequestManager {
    private static final String FORM_PATH = "/form";
    private static final String PATH_TO_FILE = "public/postData.txt";


    @Override
    public String manage(Request request) {
        try {
            updateData(request.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getCorrectResponse(request.getPath(), FORM_PATH);
    }

    private void updateData(String data) throws IOException {
        File file = new File(PATH_TO_FILE);

        if (data != null) {
            FileWriter writer = new FileWriter(file, false);
            writer.write(data);
            writer.close();
        }
    }
}
