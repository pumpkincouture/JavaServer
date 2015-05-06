package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.FileWriter;
import org.junit.Test;


import java.io.*;

import static org.junit.Assert.assertEquals;

public class DeleteResponseTest {
    private Request request;
    private RequestParser requestParser;
    private Response requestHandler;
    private FileWriter fileWriter;
    private File path;

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
        requestHandler = new DeleteResponse(fileWriter, request.getPath());
    }

    @Test
    public void returns200Response() throws UnsupportedEncodingException {
        createRequestAndFilePath("DELETE /form HTTP/1.1", "form");

        assertEquals("HTTP/1.1 200 OK", requestHandler.getCorrectStatus());
    }

    @Test
    public void returnsEmptyStringAsHeader() throws UnsupportedEncodingException {
        createRequestAndFilePath("DELETE /form HTTP/1.1", "form");

        assertEquals("", requestHandler.getCorrectHeaders());
    }

    @Test
    public void returnsEmptyStringAsBody() throws IOException {
        createRequestAndFilePath("DELETE /form HTTP/1.1", "form");

        assertEquals("", requestHandler.getCorrectBody());
    }
}
