package JavaServer.requests;

import java.util.ArrayList;
import java.util.List;

public class RouteValidator {
    public static final String FORM = "/form";
    public static final String PARAMETERS = "/parameters";
    private Request request;
    private List<String> validMethods;
    private List<String> allValidPaths = new ArrayList<>();
    private static final String AUTHORIZATION = "Basic YWRtaW46aHVudGVyMg==";
    private static final String GET_METHOD = "GET";
    private static final String POST_METHOD = "POST";
    private static final String PUT_METHOD = "PUT";
    private static final String DELETE_METHOD = "DELETE";
    private static final String REDIRECT = "/redirect";
    private static final String LOGS = "/logs";
    private static final String OPTIONS = "/method_options";
    private static final String DIRECTORY = "/";

    public RouteValidator(Request request) {
        this.request = request;
    }

    public void addMethodsToValidPaths(List<String> validFilePaths) {
        this.allValidPaths = validFilePaths;
        allValidPaths.add(DIRECTORY);
        allValidPaths.add(OPTIONS);
        allValidPaths.add(REDIRECT);
        allValidPaths.add(FORM);
        allValidPaths.add(LOGS);
        allValidPaths.add(PARAMETERS);
    }

    public boolean methodEqualsGet() {
        return request.getMethod().equals(GET_METHOD);
    }

    public boolean methodEqualsPost() {
        return request.getMethod().equals(POST_METHOD);
    }

    public boolean methodEqualsPut() {
        return request.getMethod().equals(PUT_METHOD);
    }

    public boolean methodEqualsDelete() {
        return request.getMethod().equals(DELETE_METHOD);
    }

    public boolean isRedirectPath() {
        return request.getPath().equals(REDIRECT);
    }

    public boolean isOptionsPath() {
        return request.getPath().equals(OPTIONS);
    }

    public boolean isDirectory() {
        return request.getPath().equals(DIRECTORY);
    }

    public boolean isParameterPath() {
        return request.getPath().equals(PARAMETERS) && request.getMethod().equals(GET_METHOD);
    }

    public boolean isInvalidPath() {
        return !getValidMethods().contains(request.getPath()) && !allValidPaths.contains(request.getPath());
    }

    public boolean isMethodValid() {
        return getValidMethods().contains(request.getMethod());
    }

    public boolean requiresAuthorization() {
        return request.getPath().equals(LOGS);
    }

    public boolean requestHasCorrectAuthorization() {
        return request.hasAuthorization() && request.getAuthorizationCode().contains(AUTHORIZATION);
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
