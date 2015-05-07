package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.FileAdmin;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class ContentResponseTest {
    private Request request;
    private RequestParser requestParser;
    private Response response;
    private File path;
    private FileAdmin fileAdmin;
    private String userDirectory = System.getProperty("user.dir") + "/public";

    private DataOutputStream mockDataStream() throws UnsupportedEncodingException {
        ByteArrayOutputStream mockInputStream = new ByteArrayOutputStream();

        return new DataOutputStream(mockInputStream);
    }

    private void createRequestAndResponse(String requestLine, String filepath) throws UnsupportedEncodingException {
        requestParser = new RequestParser(requestLine);
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File(userDirectory + filepath);
        fileAdmin = new FileAdmin(path, mockDataStream());
        response = new ContentResponse(fileAdmin, request);
    }

    @Test
    public void returns200Response() throws IOException {
        createRequestAndResponse("GET /image.gif HTTP/1.1", "/image.gif");

        assertEquals("HTTP/1.1 200 OK", response.getCorrectStatus());
    }

    @Test
    public void returns206IfRequestHasRange() throws IOException {
        createRequestAndResponse("GET /partial_content.txt HTTP/1.1\n" +
                                 "Range: bytes=-6\n" +
                                 "Host: localhost:5000\n" +
                                 "\n", "/partial_content.txt");

        assertEquals("HTTP/1.1 206 Partial Content", response.getCorrectStatus());
    }

    @Test
    public void returnsEmptyHeader() throws UnsupportedEncodingException {
        createRequestAndResponse("GET /text-file.txt HTTP/1.1", "/text-file.txt");

        assertEquals("", response.getCorrectHeaders());
    }

    @Test
    public void callsFileWriterAndReturnsAnEmptyStringIfRequestDoesNotHaveRange() throws IOException {
        createRequestAndResponse("GET /file1 HTTP/1.1", "/file1");

        assertEquals("", response.getCorrectBody());
    }

    @Test
    public void callsFileWriterAndReturnsAnEmptyStringIfRequestHasRange() throws IOException {
        createRequestAndResponse("GET /partial_content.txt HTTP/1.1\n" +
                                 "Range: bytes=0-6\n" +
                                 "Host: localhost:5000\n" +
                                 "\n", "/partial_content.txt");

        assertEquals("", response.getCorrectBody());
    }
}
