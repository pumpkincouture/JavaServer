package JavaServer.MethodManagers;

import JavaServer.RequestManagers.Request;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PutManager extends RequestManager {

    @Override
    public String manage(Request request) {
        try {
            updateData(request.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return getCorrectResponse(request.getPath(), "/form");
    }

    private void updateData(String data) throws IOException {
        File file = new File("public/postData.txt");

        if (data != null) {
            FileWriter writer = new FileWriter(file, false);
            writer.write(data);
            writer.close();
        }
    }
}
