package JavaServer.RequestHandlers;

public class Request {
    String method;
    String path;
    String HTTPVersion;

    public Request(String method, String path, String HTTPVersion) {
        this.method = method;
        this.path = path;
        this.HTTPVersion = HTTPVersion;
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public String getHTTP() {
        return HTTPVersion;
    }
}
