package JavaServer.ResponseHandlers;

import JavaServer.RequestHandlers.Request;

public class HandlerFactory {
    private Request request;

    public HandlerFactory(Request request) {
        this.request = request;
    }

    public String createMethodHandler() {
        String requestMethod = request.getMethod();

        switch (requestMethod) {
            case "GET":
                return "GetMethodHandler";
            case "POST":
                return "PostMethodHandler";

            case "PUT":
                return "PutMethodHandler";
        }
        return "";
    }

}
