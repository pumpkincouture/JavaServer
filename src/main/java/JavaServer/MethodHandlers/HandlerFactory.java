package JavaServer.MethodHandlers;

import JavaServer.RequestHandlers.Request;
import JavaServer.ResponseHandlers.*;

public class HandlerFactory {
    private Request request;
    private ResponseCodeBuilder responseCodeBuilder;

    public HandlerFactory(Request request) {
        this.request = request;
        responseCodeBuilder = new ResponseCodeBuilder(request);
    }

    public RequestHandler createMethodHandler() {
        String requestMethod = request.getMethod();

        switch (requestMethod) {
            case "GET":
                RequestHandler gethandler = new GetHandler(responseCodeBuilder);
                return gethandler;
            case "POST":
                RequestHandler postHandler = new PostHandler(responseCodeBuilder);
                return postHandler;
            case "PUT":
                RequestHandler putHandler = new PutHandler(responseCodeBuilder);
                return putHandler;
            case "OPTIONS":
                RequestHandler optionsHandler = new OptionsHandler(responseCodeBuilder);
                return optionsHandler;

        }
        RequestHandler gethandler = new GetHandler(responseCodeBuilder);
        return gethandler;
    }

}
