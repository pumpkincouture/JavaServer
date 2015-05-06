package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.FileWriter;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class ParamResponseTest {
    private Request request;
    private Response response;
    private FileWriter fileWriter;
    private File path;
    private RequestParser requestParser;

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
        response = new ParamResponse(request.getData(), fileWriter);
    }

    @Test
    public void returns200Response() throws UnsupportedEncodingException {
        createRequestAndResponse("GET /parameters/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1", "parameters");

        assertEquals("HTTP/1.1 200 OK", response.getCorrectStatus());
    }

    @Test
    public void returnsEmptyStringAsHeader() throws UnsupportedEncodingException {
        createRequestAndResponse("GET /parameters/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1", "parameters");

        assertEquals("", response.getCorrectHeaders());
    }

    @Test
    public void returnsEmptyStringAsBody() throws IOException {
        createRequestAndResponse("GET /parameters/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1", "parameters");

        assertEquals("", response.getCorrectBody());
    }
}
