package JavaServer.responses.methods;

import java.io.IOException;

public class UnauthorizedResponse extends Response{

    @Override
    public String getCorrectStatus() {
        return getCodes().get("401");
    }

    @Override
    public String getCorrectHeaders() {
        return EMPTY_STRING;
    }

    @Override
    public String getCorrectBody() throws IOException {
        return "Authentication required";
    }
}
