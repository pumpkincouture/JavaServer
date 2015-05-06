package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.FileWriter;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class ContentResponseTest {
    private Request request;
    private RequestParser requestParser;
    private Response response;
    private File path;
    private FileWriter fileWriter;

    private DataOutputStream mockDataStream() throws UnsupportedEncodingException {
        ByteArrayOutputStream mockInputStream = new ByteArrayOutputStream();

        DataOutputStream out = new DataOutputStream(mockInputStream);
        return out;
    }

    private void createRequestAndFilePath(String requestLine, String filepath) throws UnsupportedEncodingException {
        requestParser = new RequestParser(requestLine);
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/" + filepath);
        fileWriter = new FileWriter(path, mockDataStream());
        response = new ContentResponse(fileWriter, request);
    }

    @Test
    public void returns200Response() throws IOException {
        createRequestAndFilePath("GET /image.gif HTTP/1.1", "image.gif");

        assertEquals("HTTP/1.1 200 OK", response.getCorrectStatus());
    }

    @Test
    public void returns206IfRequestHasRange() throws IOException {
        createRequestAndFilePath("GET /partial_content.txt HTTP/1.1\n"+
                                 "Range: bytes=-6\n"+
                                 "Host: localhost:5000\n"+
                                 "\n", "partial_content.txt");

        assertEquals("HTTP/1.1 206 Partial Content", response.getCorrectStatus());
    }

    @Test
    public void returnsEmptyHeader() throws UnsupportedEncodingException {
        createRequestAndFilePath("GET /text-file.txt HTTP/1.1", "text-file.txt");

        assertEquals("", response.getCorrectHeaders());
    }

    @Test
    public void callsFileWriterAndReturnsAnEmptyStringIfRequestDoesNotHaveRange() throws IOException {
        createRequestAndFilePath("GET /file1 HTTP/1.1", "file1");

        assertEquals("", response.getCorrectBody());
    }

    @Test
    public void callsFileWriterAndReturnsAnEmptyStringIfRequestHasRange() throws IOException {
        createRequestAndFilePath("GET /partial_content.txt HTTP/1.1\n"+
                                 "Range: bytes=0-6\n"+
                                 "Host: localhost:5000\n"+
                                 "\n", "partial_content.txt");

        assertEquals("", response.getCorrectBody());
    }
}
