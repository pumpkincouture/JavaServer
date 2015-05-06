package JavaServer.responses.methods;

import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.FileWriter;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class ParamResponseTest {
    private Request request;
    private Response requestHandler;
    private FileWriter fileWriter;
    private File path;
    DataOutputStream dataOutputStream;

    private void mockDataStream() throws UnsupportedEncodingException {
        ByteArrayOutputStream mockInputStream = new ByteArrayOutputStream();

        dataOutputStream = new DataOutputStream(mockInputStream);
    }

    private Request createRequestWithParams(String requestString) {
        RequestParser parser = new RequestParser(requestString);

        request = new Request(parser.getMethod(), parser.getPath(), parser.getHeaders(), parser.getData());

        return request;
    }

    @Test
    public void returns200ResponseAndReturnsParamsAsAString() throws IOException {
        request = createRequestWithParams("GET /parameters/parameters?variable_1=Operators%20%3C%2C%20%3E%2C%20%3D%2C%20!%3D%3B%20%2B%2C%20-%2C%20*%2C%20%26%2C%20%40%2C%20%23%2C%20%24%2C%20%5B%2C%20%5D%3A%20%22is%20that%20all%22%3F&variable_2=stuff HTTP/1.1");
        path = new File("/Users/test/code/JavaServer/public/parameters");
        mockDataStream();
        fileWriter = new FileWriter(path, dataOutputStream);
        requestHandler= new ParamResponse(request.getData(), fileWriter);

        assertEquals("HTTP/1.1 200 OK", requestHandler.getCorrectStatus());
//        assertEquals("variable_1 = Operators <, >, =, !=; +, -, *, &, @, #, $, [, ]: \"is that all\"?variable_2 = stuff", dataOutputStream.toString());
    }
}
