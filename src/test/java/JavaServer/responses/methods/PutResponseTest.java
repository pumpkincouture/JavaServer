package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.FileAdmin;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class PutResponseTest {
    private Request request;
    private RequestParser requestParser;
    private Response response;
    private File path;
    private FileAdmin fileWriter;

    private DataOutputStream mockDataStream() throws UnsupportedEncodingException {
        ByteArrayOutputStream mockInputStream = new ByteArrayOutputStream();

        DataOutputStream out = new DataOutputStream(mockInputStream);
        return out;
    }

    private void createRequestAndResponse(String requestLine, String filepath) throws UnsupportedEncodingException {
        requestParser = new RequestParser(requestLine);
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public" + filepath);
        fileWriter = new FileAdmin(path, mockDataStream());
        response = new PutResponse(fileWriter, request.getData(), filepath);
    }

    @Test
    public void returns200ResponseIfPathIsRecognized() throws IOException {
        createRequestAndResponse("PUT /form HTTP/1.1", "/form");

        assertEquals("HTTP/1.1 200 OK", response.getCorrectStatus());
    }

    @Test
    public void returns404ResponseIfPathIsNotRecognized() throws IOException {
        createRequestAndResponse("PUT / HTTP/1.1", "/");

        assertEquals("HTTP/1.1 404 Not Found", response.getCorrectStatus());
    }

    @Test
    public void returns405ResponseIfMethodNotAllowed() throws IOException {
        createRequestAndResponse("PUT /file1 HTTP/1.1", "/file1");

        assertEquals("HTTP/1.1 405 Method Not Allowed", response.getCorrectStatus());
    }

    @Test
    public void returnEmptyStringAsHeader() throws UnsupportedEncodingException {
        createRequestAndResponse("PUT /form HTTP/1.1", "/form");

        assertEquals("", response.getCorrectHeaders());
    }

    @Test
    public void returnEmptyStringAsBody() throws IOException {
        createRequestAndResponse("PUT /form HTTP/1.1", "/form");

        assertEquals("", response.getCorrectBody());
    }
}

