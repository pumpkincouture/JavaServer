package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.FileAdmin;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class GetResponseTest {
    private Request request;
    private RequestParser requestParser;
    private Response response;
    private FileAdmin fileAdmin;
    private File path;

    private DataOutputStream mockDataStream() throws UnsupportedEncodingException {
        ByteArrayOutputStream mockInputStream = new ByteArrayOutputStream();

        DataOutputStream out = new DataOutputStream(mockInputStream);
        return out;
    }

    private void createRequestAndResponse(String requestLine, String filepath) throws UnsupportedEncodingException {
        requestParser = new RequestParser(requestLine);
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/" + filepath);
        fileAdmin = new FileAdmin(path, mockDataStream());
        response = new GetResponse(fileAdmin, request);
    }

    @Test
    public void returns200ResponseCode() throws IOException {
        createRequestAndResponse("GET / HTTP/1.1", "");

        assertEquals("HTTP/1.1 200 OK", response.getCorrectStatus());
    }

    @Test
    public void returnsEmptyStringAsHeader() throws UnsupportedEncodingException {
        createRequestAndResponse("GET / HTTP/1.1", "");

        assertEquals("", response.getCorrectHeaders());
    }

    @Test
    public void returnsDirectoryLinksIfFileExistsAndIsRoute() throws IOException {
        createRequestAndResponse("GET / HTTP/1.1", "");

        assertEquals("<a href='/file1'>file1</a></br>\r\n" +
                     "<a href='/file2'>file2</a></br>\r\n" +
                     "<a href='/image.gif'>image.gif</a></br>\r\n" +
                     "<a href='/image.jpeg'>image.jpeg</a></br>\r\n" +
                     "<a href='/image.png'>image.png</a></br>\r\n" +
                     "<a href='/partial_content.txt'>partial_content.txt</a></br>\r\n" +
                     "<a href='/patch-content.txt'>patch-content.txt</a></br>\r\n" +
                     "<a href='/text-file.txt'>text-file.txt</a></br>\r\n", response.getCorrectBody());
    }

    @Test
    public void callsFileManagerToSendOutFileContentsAndReturnsAnEmptyString() throws IOException {
        createRequestAndResponse("GET /form HTTP/1.1", "form");

        assertEquals("", response.getCorrectBody());
    }
}
