package JavaServer.MethodHandlers;

import JavaServer.RequestHandlers.Request;
import JavaServer.ResponseHandlers.*;

public class ManagerFactory {
    private Request request;
    private ResponseCodeBuilder responseCodeBuilder;

    public ManagerFactory(Request request) {
        this.request = request;
        responseCodeBuilder = new ResponseCodeBuilder(request);
    }

    public RequestManager createMethodHandler() {
        String requestMethod = request.getMethod();

        switch (requestMethod) {
            case "GET":
                RequestManager gethandler = new GetManager(responseCodeBuilder);
                return gethandler;
            case "POST":
                RequestManager postHandler = new PostManager(responseCodeBuilder);
                return postHandler;
            case "PUT":
                RequestManager putHandler = new PutManager(responseCodeBuilder);
                return putHandler;
            case "OPTIONS":
                RequestManager optionsHandler = new OptionsManager(responseCodeBuilder);
                return optionsHandler;

        }
        RequestManager gethandler = new GetManager(responseCodeBuilder);
        return gethandler;
    }

}
