package JavaServer.requests;

import java.util.ArrayList;
import java.util.List;

public class RouteValidator {
    private Request request;
    private List<String> validMethods;
    private List<String> validPaths;

    public RouteValidator(Request request) {
        this.request = request;
    }

    public boolean isMethodValid() {
        return getValidMethods().contains(request.getMethod());
    }

    public boolean isPathValid() {
        return getValidPaths().contains(request.getPath());
    }

    private List<String> getValidMethods() {
        validMethods = new ArrayList<>();

        validMethods.add("GET");
        validMethods.add("OPTIONS");
        validMethods.add("HEAD");
        validMethods.add("POST");
        validMethods.add("PUT");

        return validMethods;
    }

    private List<String> getValidPaths() {
        validPaths = new ArrayList<>();

        validPaths.add("/");
        validPaths.add("/method_options");
        validPaths.add("/redirect");
        validPaths.add("/form");
        validPaths.add("/text-file.txt");
        validPaths.add("/file1");
        validPaths.add("/file2");

        return validPaths;
    }
}
