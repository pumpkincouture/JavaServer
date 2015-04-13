package JavaServer.ResponseHandlers;

import JavaServer.RequestHandlers.Request;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PutHandler implements RequestHandler {
    private ResponseCodeBuilder responseCodeBuilder;
    public PutHandler(ResponseCodeBuilder responseCodeBuilder) {
        this.responseCodeBuilder = responseCodeBuilder;
    }

    @Override
    public String handle(Request request) {
        if (request.getPath().equals("/form")) {
            return responseCodeBuilder.createValidResponse();
        }

        return  responseCodeBuilder.returnFourOhFour();
    }

    private void updateData(String data) throws IOException {
        File file = new File("public/postData.txt");

        FileWriter writer = new FileWriter(file, false);
        writer.write(data);
        writer.close();
    }
}
