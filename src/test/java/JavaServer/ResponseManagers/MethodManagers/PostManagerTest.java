package JavaServer.ResponseManagers.MethodManagers;

import JavaServer.RequestManagers.Request;
import JavaServer.RequestManagers.RequestParser;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class PostManagerTest {

    private Request request;
    private RequestParser requestParser;
    private RequestManager requestHandler;

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
    public void returns200ResponseIfRequestPathValidAndChecksIfDataWasWrittenToFile() throws FileNotFoundException {
        requestParser = new RequestParser("POST /form HTTP/1.1\n"+
                                          "first_name=sam\n");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());

        requestHandler= new PostManager();

        assertEquals("HTTP/1.1 200 OK", requestHandler.manage(request));
        assertEquals("first_name=sam", getDataFromFile());
    }

    @Test
    public void returns200ResponseIfRequestValidAndUpdatesFileData() throws FileNotFoundException {
        requestParser = new RequestParser("POST /form HTTP/1.1\n"+
                                          "last_name=smith\n");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        requestHandler= new PostManager();

        assertEquals("HTTP/1.1 200 OK", requestHandler.manage(request));
        assertEquals("last_name=smith", getDataFromFile());
    }

    @Test
    public void returns404ResponseIfPathIsNotRecognized() throws FileNotFoundException {
        requestParser = new RequestParser("POST / HTTP/1.1");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        requestHandler= new PostManager();

        assertEquals("HTTP/1.1 404 Not Found", requestHandler.manage(request));
    }
}
