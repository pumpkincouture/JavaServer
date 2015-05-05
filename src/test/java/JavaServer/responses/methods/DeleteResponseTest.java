package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.FileManager;
import org.junit.Test;


import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class DeleteResponseTest {
    private Request request;
    private RequestParser requestParser;
    private Response requestHandler;
    private FileManager fileManager;
    private File path;

    private DataOutputStream mockDataStream() throws UnsupportedEncodingException {
        ByteArrayOutputStream mockInputStream = new ByteArrayOutputStream();

        DataOutputStream out = new DataOutputStream(mockInputStream);
        return out;
    }

    @Test
    public void returns200ResponseAndUpdatesDataManagerDataToEmptyString() throws UnsupportedEncodingException {
        requestParser = new RequestParser("DELETE /form HTTP/1.1");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/form");
        fileManager = new FileManager(path, mockDataStream());
        requestHandler= new DeleteResponse(fileManager, request.getPath());

        assertEquals("HTTP/1.1 200 OK", requestHandler.getCorrectStatus());
    }
}
