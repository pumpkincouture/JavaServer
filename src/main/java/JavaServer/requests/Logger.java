package JavaServer.requests;

public class Logger {

    private String loggedRequest= "";

    public String getLogs() {
        return loggedRequest;
    }

    public void logRequest(String request) {
        loggedRequest += request + "\r\n";
    }
}
