package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.FileAdmin;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class PostResponseTest {

    private Request request;
    private RequestParser requestParser;
    private Response response;
    private FileAdmin fileAdmin;
    private File path;
    private String directory = System.getProperty("user.dir") + "/public";


    private DataOutputStream mockDataStream() throws UnsupportedEncodingException {
        ByteArrayOutputStream mockInputStream = new ByteArrayOutputStream();

        DataOutputStream out = new DataOutputStream(mockInputStream);
        return out;
    }

    private void createRequestAndResponse(String requestLine, String filepath) throws UnsupportedEncodingException {
        requestParser = new RequestParser(requestLine);
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File(directory + filepath);
        fileAdmin = new FileAdmin(path, mockDataStream());
        response = new PostResponse(fileAdmin, request.getPath(), request.getData());
    }

    @Test
    public void returns200ResponseIfPathIsRecognized() throws IOException {
        createRequestAndResponse("POST /form HTTP/1.1", "/form");

        assertEquals("HTTP/1.1 200 OK", response.getCorrectStatus());
    }

    @Test
    public void returns404ResponseIfPathIsNotRecognized() throws IOException {
        createRequestAndResponse("POST / HTTP/1.1", "/form");

        assertEquals("HTTP/1.1 404 Not Found", response.getCorrectStatus());
    }

    @Test
    public void returns405ResponseIfMethodNotAllowed() throws IOException {
        createRequestAndResponse("POST /text-file.txt HTTP/1.1", "/text-file.txt");

        assertEquals("HTTP/1.1 405 Method Not Allowed", response.getCorrectStatus());
    }
}
