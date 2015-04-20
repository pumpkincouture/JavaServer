package JavaServer.responses.methods;

public class ResponseFactory {
    private String requestMethod;
    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";
    private static final String PUT_METHOD = "PUT";
    private static final String OPTIONS_METHOD = "OPTIONS";

    public ResponseFactory(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public Response createMethodManager() {
        switch (requestMethod) {
            case GET_METHOD:
                Response gethandler = new GetResponse();
                return gethandler;
            case POST_METHOD:
                Response postHandler = new PostResponse();
                return postHandler;
            case PUT_METHOD:
                Response putHandler = new PutResponse();
                return putHandler;
            case OPTIONS_METHOD:
                Response optionsHandler = new OptionsResponse();
                return optionsHandler;

        }
        Response gethandler = new GetResponse();
        return gethandler;
    }

}
