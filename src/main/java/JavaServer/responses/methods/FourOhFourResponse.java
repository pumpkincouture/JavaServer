package JavaServer.responses.methods;

import java.io.IOException;

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
    public String getCorrectBody() throws IOException {
        return EMPTY_STRING;
    }
}
