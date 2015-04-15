package JavaServer.MethodManagers;

import JavaServer.RequestManagers.Request;

public class ManagerFactory {
    private Request request;
    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";
    private static final String PUT_METHOD = "PUT";
    private static final String OPTIONS_METHOD = "OPTIONS";

    public ManagerFactory(Request request) {
        this.request = request;
    }

    public RequestManager createMethodHandler() {
        String requestMethod = request.getMethod();

        switch (requestMethod) {
            case GET_METHOD:
                RequestManager gethandler = new GetManager();
                return gethandler;
            case POST_METHOD:
                RequestManager postHandler = new PostManager();
                return postHandler;
            case PUT_METHOD:
                RequestManager putHandler = new PutManager();
                return putHandler;
            case OPTIONS_METHOD:
                RequestManager optionsHandler = new OptionsManager();
                return optionsHandler;

        }
        RequestManager gethandler = new GetManager();
        return gethandler;
    }

}
