package JavaServer.requests;

import java.util.ArrayList;
import java.util.List;

public class RouteValidator {
    private Request request;
    private List<String> validMethods;
    private static final String GET_METHOD = "GET";
    private List<String> allValidPaths = new ArrayList<>();

    public RouteValidator(Request request) {
        this.request = request;
    }

    public void addMethodsToValidPaths(List<String> validFilePaths) {
        this.allValidPaths = validFilePaths;
        allValidPaths.add("/");
        allValidPaths.add("/method_options");
        allValidPaths.add("/redirect");
        allValidPaths.add("/form");
    }
    
    public boolean isRedirectPath() {
        return request.getPath().equals("/redirect");
    }

    public boolean isOptionsPath() {
        return request.getPath().equals("/method_options");
    }

    public boolean isDirectory() {
        return request.getPath().equals("/");
    }

    public boolean isInvalidPath() {
        return !getValidMethods().contains(request.getPath()) && !allValidPaths.contains(request.getPath());
    }

    public boolean isMethodValid() {
        return getValidMethods().contains(request.getMethod());
    }

    private List<String> getValidMethods() {
        validMethods = new ArrayList<>();

        validMethods.add("GET");
        validMethods.add("OPTIONS");
        validMethods.add("HEAD");
        validMethods.add("POST");
        validMethods.add("PUT");
        validMethods.add("DELETE");

        return validMethods;
    }
}
