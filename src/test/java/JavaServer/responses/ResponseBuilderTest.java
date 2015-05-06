package JavaServer.responses;

import JavaServer.requests.Logger;
import JavaServer.requests.Request;
import JavaServer.requests.RequestParser;
import JavaServer.responses.methods.ResponseFactory;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;

import static org.junit.Assert.assertEquals;

public class ResponseBuilderTest {
    private Request request;
    private RequestParser requestParser;
    private ResponseBuilder responseCodeBuilder;
    private ResponseFactory managerFactory;
    private File path;
    private FileWriter fileWriter;
    private Logger logger;

    private DataOutputStream mockDataStream() throws UnsupportedEncodingException {
        ByteArrayOutputStream mockInputStream = new ByteArrayOutputStream();

        DataOutputStream out = new DataOutputStream(mockInputStream);
        return out;
    }

    @Test
    public void parseAndStoreMoreThanOnePostParamAndReturnResponseWithNoHeaders() throws UnsupportedEncodingException {
        requestParser = new RequestParser("GET /form\n"+
                                          "Content-Type: application/x-www-form-url-encoded\n"+
                                          "Host: https://sylwiaolak.com\n"+
                                          "\n"+
                                          "first_name=sylwia\n"+
                                          "last_name=olak\n"+
                                          "age=26");
        request = new Request(requestParser.getMethod(), requestParser.getPath(), requestParser.getHeaders(), requestParser.getData());
        path = new File("/Users/test/code/JavaServer/public/image.gif");
        fileWriter = new FileWriter(path, mockDataStream());
        logger = new Logger();
        managerFactory = new ResponseFactory(request, fileWriter, logger);

        responseCodeBuilder = new ResponseBuilder(managerFactory.createResponse());

        assertEquals("HTTP/1.1 404 Not Found\n" + "\r\n", responseCodeBuilder.getResponseHeaders());
    }
}
