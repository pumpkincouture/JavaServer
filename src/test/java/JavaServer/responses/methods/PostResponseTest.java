package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.FileWriter;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class PostResponseTest {

    private Request request;
    private RequestParser requestParser;
    private Response response;
    private FileWriter fileWriter;
    private File path;

    private DataOutputStream mockDataStream() throws UnsupportedEncodingException {
        ByteArrayOutputStream mockInputStream = new ByteArrayOutputStream();

        DataOutputStream out = new DataOutputStream(mockInputStream);
        return out;
    }

    @Test
    public void returns200ResponseIfPathIsRecognized() throws FileNotFoundException, UnsupportedEncodingException {
        requestParser = new RequestParser("POST /form HTTP/1.1");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/form");
        fileWriter = new FileWriter(path, mockDataStream());
        response = new PostResponse(fileWriter, request.getPath(), request.getData());

        assertEquals("HTTP/1.1 200 OK", response.getCorrectStatus());
    }

    @Test
    public void returns404ResponseIfPathIsNotRecognized() throws FileNotFoundException, UnsupportedEncodingException {
        requestParser = new RequestParser("POST / HTTP/1.1");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/form");
        fileWriter = new FileWriter(path, mockDataStream());
        response = new PostResponse(fileWriter, request.getPath(), request.getData());

        assertEquals("HTTP/1.1 404 Not Found", response.getCorrectStatus());
    }

    @Test
    public void returns405ResponseIfMethodNotAllowed() throws FileNotFoundException, UnsupportedEncodingException {
        requestParser = new RequestParser("POST /text-file.txt HTTP/1.1");

        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/form");
        fileWriter = new FileWriter(path, mockDataStream());
        response = new PostResponse(fileWriter, request.getPath(), request.getData());

        assertEquals("HTTP/1.1 405 Method Not Allowed", response.getCorrectStatus());
    }
}
