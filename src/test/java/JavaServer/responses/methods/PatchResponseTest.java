package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.FileWriter;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class PatchResponseTest {
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

    private void createRequestAndResponse(String requestLine, String filepath) throws UnsupportedEncodingException {
        requestParser = new RequestParser(requestLine);
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/" + filepath);
        fileWriter = new FileWriter(path, mockDataStream());
        response = new PatchResponse(fileWriter, request);
    }

    private String readFromFile() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        String fileLines = "";

        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            fileLines += line;
        }
        bufferedReader.close();
        return fileLines;
    }

    @Test
    public void returns204ResponseIfNoEtagPresent() throws IOException {
        createRequestAndResponse("PATCH /patch-content.txt HTTP/1.1", "patch-content.txt");

        assertEquals("HTTP/1.1 204 No Content", response.getCorrectStatus());
        assertEquals("default content ", readFromFile());
    }

    @Test
    public void returnsEmptyStringAsHeader() throws UnsupportedEncodingException {
        createRequestAndResponse("PATCH /patch-content.txt HTTP/1.1", "patch-content.txt");

        assertEquals("", response.getCorrectHeaders());
    }

    @Test
    public void returnsEmptyStringAsBody() throws IOException {
        createRequestAndResponse("PATCH /logs HTTP/1.1\n" +
                "If-Match: dc50a0d27dda2eee9f65644cd7e4c9cf11de8bec\n" +
                "Host: localhost:5000\n" +
                "\n" +
                "patched content", "patch-content.txt");

        assertEquals("", response.getCorrectBody());
    }
}
