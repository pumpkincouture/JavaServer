package JavaServer.MethodManagers;

import JavaServer.RequestManagers.Request;

public class ManagerFactory {
    private Request request;

    public ManagerFactory(Request request) {
        this.request = request;
    }

    public RequestManager createMethodHandler() {
        String requestMethod = request.getMethod();

        switch (requestMethod) {
            case "GET":
                RequestManager gethandler = new GetManager();
                return gethandler;
            case "POST":
                RequestManager postHandler = new PostManager();
                return postHandler;
            case "PUT":
                RequestManager putHandler = new PutManager();
                return putHandler;
            case "OPTIONS":
                RequestManager optionsHandler = new OptionsManager();
                return optionsHandler;

        }
        RequestManager gethandler = new GetManager();
        return gethandler;
    }

}
