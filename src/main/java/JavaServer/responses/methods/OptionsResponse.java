package JavaServer.responses.methods;

public class OptionsResponse extends Response {

    @Override
    public String getCorrectStatus() {
        return getCodes().get("200");
    }

    @Override
    public String getCorrectHeaders() {
        return getHeaders().get("options");
    }

    @Override
    public String getCorrectBody() {
        return EMPTY_STRING;
    }
}
