package JavaServer.ResponseHandlers;

import JavaServer.RequestHandlers.Request;
import JavaServer.RequestHandlers.RequestParser;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class PutHandlerTest {
    private Request request;
    private RequestParser requestParser;
    private RequestHandler requestHandler;
    private ResponseCodeBuilder responseCodeBuilder;

    private String getDataFromFile() throws FileNotFoundException {
        String data = null;
        FileInputStream file = new FileInputStream("public/postData.txt");
        BufferedReader textReader = new BufferedReader(new InputStreamReader(file));

        try {
            data = textReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Test
    public void returns200ResponseIfRequestValidAndChecksIfDataWasWrittenToFile() throws FileNotFoundException {
        requestParser = new RequestParser("PUT /form HTTP/1.1\n"+
                                          "first_name=hello\n");

        request = new Request(requestParser.getAllRequestAttributes());
        responseCodeBuilder = new ResponseCodeBuilder(request);
        requestHandler= new PutHandler(responseCodeBuilder);

        assertEquals("HTTP/1.1 200 OK", requestHandler.handle(request));
        assertEquals("first_name=hello", getDataFromFile());
    }

    @Test
    public void returns200ResponseIfRequestValidAndUpdatesFileData() throws FileNotFoundException {
        requestParser = new RequestParser("PUT /form HTTP/1.1\n"+
                                          "last_name=whatever\n");

        request = new Request(requestParser.getAllRequestAttributes());
        responseCodeBuilder = new ResponseCodeBuilder(request);
        requestHandler= new PutHandler(responseCodeBuilder);

        assertEquals("HTTP/1.1 200 OK", requestHandler.handle(request));
        assertEquals("last_name=whatever", getDataFromFile());
    }

    @Test
    public void returns404ResponseIfPathIsNotRecognized() throws FileNotFoundException {
        requestParser = new RequestParser("PUT / HTTP/1.1");

        request = new Request(requestParser.getAllRequestAttributes());
        responseCodeBuilder = new ResponseCodeBuilder(request);
        requestHandler= new PutHandler(responseCodeBuilder);

        assertEquals("HTTP/1.1 404 Not Found", requestHandler.handle(request));
    }
}

