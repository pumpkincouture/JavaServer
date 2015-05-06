package JavaServer.requests;

import java.util.ArrayList;
import java.util.List;

public class RouteValidator {
    public static final String PATCH_CONTENT = "/patch-content.txt";
    public static final String PARTIAL = "/partial_content.txt";
    public static final String HEAD = "HEAD";
    private Request request;
    private List<String> validMethods;
    private List<String> allValidPaths = new ArrayList<>();
    private static final String AUTHORIZATION = "Basic YWRtaW46aHVudGVyMg==";
    public static final String FORM = "/form";
    public static final String PARAMETERS = "/parameters";
    public static final String PATCH = "PATCH";
    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String PUT = "PUT";
    private static final String DELETE = "DELETE";
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
        allValidPaths.add(PATCH_CONTENT);
        allValidPaths.add(PARTIAL);
    }

    public boolean methodEqualsGet() {
        return request.getMethod().equals(GET);
    }

    public boolean methodEqualsPost() {
        return request.getMethod().equals(POST);
    }

    public boolean methodEqualsPut() {
        return request.getMethod().equals(PUT);
    }

    public boolean methodEqualsDelete() {
        return request.getMethod().equals(DELETE);
    }

    public boolean methodEqualsPatch() {
        return request.getMethod().equals(PATCH);
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
        return request.getPath().equals(PARAMETERS) && request.getMethod().equals(GET);
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
        return request.hasAuthorization() && getAuthorizationCode().contains(AUTHORIZATION);
    }

    private String getAuthorizationCode() {
        if (request.hasAuthorization()) {
            return request.getHeaders().get("Authorization");
        }
        return "";
    }

    private List<String> getValidMethods() {
        validMethods = new ArrayList<>();

        validMethods.add(GET);
        validMethods.add(OPTIONS);
        validMethods.add(HEAD);
        validMethods.add(POST);
        validMethods.add(PUT);
        validMethods.add(DELETE);
        validMethods.add(PATCH);

        return validMethods;
    }
}
