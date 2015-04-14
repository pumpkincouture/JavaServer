package JavaServer.MethodHandlers;

import JavaServer.RequestHandlers.Request;
import JavaServer.ResponseHandlers.ResponseCodeBuilder;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class PostManager extends RequestManager {
    private ResponseCodeBuilder responseCodeBuilder;

    public PostManager(ResponseCodeBuilder responseCodeBuilder) {
        this.responseCodeBuilder = responseCodeBuilder;
    }

    @Override
    public String handle(Request request) {
        if (request.getPath().equals("/form")) {
            saveData(request.getData());

            return responseCodeBuilder.createValidResponse();
        }

        return responseCodeBuilder.returnFourOhFour();
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
