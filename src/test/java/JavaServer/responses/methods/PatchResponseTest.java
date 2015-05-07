package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.FileAdminMock;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PatchResponseTest {
    private Request request;
    private RequestParser requestParser;
    private Response response;
    private FileAdminMock fileWriterMock;

    private void createRequestAndResponse(String requestLine) throws UnsupportedEncodingException {
        requestParser = new RequestParser(requestLine);
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        fileWriterMock = new FileAdminMock();
        response = new PatchResponse(fileWriterMock, request);
    }

    @Test
    public void returns204ResponseIfNoEtagPresent() throws IOException {
        createRequestAndResponse("PATCH /patch-content.txt HTTP/1.1");

        assertEquals("HTTP/1.1 204 No Content", response.getCorrectStatus());
    }

    @Test
    public void callsFileWriterToWriteToFileWithPatchedContent() throws IOException {
        createRequestAndResponse("PATCH /logs HTTP/1.1\n" +
                                 "If-Match: dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec\n" +
                                 "Host: localhost:5000\n" +
                                 "\n" +
                                 "patched content");

        response.getCorrectStatus();

        assertTrue(fileWriterMock.isPatchFileWithNewDataMessageCalled());
    }

    @Test
    public void returnsEmptyStringAsHeader() throws UnsupportedEncodingException {
        createRequestAndResponse("PATCH /patch-content.txt HTTP/1.1");

        assertEquals("", response.getCorrectHeaders());
    }

    @Test
    public void returnsEmptyStringAsBody() throws IOException {
        createRequestAndResponse("PATCH /logs HTTP/1.1\n" +
                                 "If-Match: dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec\n" +
                                 "Host: localhost:5000\n" +
                                 "\n" +
                                 "patched content");

        assertEquals("", response.getCorrectBody());
    }
}
