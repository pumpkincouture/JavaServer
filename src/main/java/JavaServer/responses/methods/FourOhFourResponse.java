package JavaServer.responses.methods;

public class FourOhFourResponse extends Response {

    @Override
    public String getCorrectStatus() {
        return getCodes().get("404");
    }

    @Override
    public String getCorrectHeaders() {
        return EMPTY_STRING;
    }

    @Override
    public String getCorrectBody() {
        return EMPTY_STRING;
    }
}
