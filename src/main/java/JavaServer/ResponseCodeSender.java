package JavaServer;

public class ResponseCodeSender {
    Request request;

    public ResponseCodeSender(Request request) {
        this.request = request;
    }


    public String returnResponseCode() {
        if (request.getMethod().contains("GET") && request.getPath().contains("/redirect")) {
            return "302";
        }
        else if (request.getMethod().contains("PUT") && request.getPath().equals("/file1")) {
            return "405";
        }
        else if (request.getMethod().contains("GET") && request.getPath().equals("/")) {
            return "200";
        }
        return "404";
    }
}
