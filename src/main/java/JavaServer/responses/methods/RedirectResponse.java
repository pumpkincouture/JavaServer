package JavaServer.responses.methods;

import java.io.IOException;

public class RedirectResponse extends Response {

    @Override
    public String getCorrectStatus() {
        return getCodes().get("302");
    }

    @Override
    public String getCorrectHeaders() {
        return getHeaders().get("location");
    }

    @Override
    public String getCorrectBody() throws IOException {
        return EMPTY_STRING;
    }
}
