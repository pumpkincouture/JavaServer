package JavaServer.configuration;

import java.util.Arrays;
import java.util.List;

public class ServerConfiguration {
    private String[] requestArgs;

    public ServerConfiguration(String[] requestArgs) {
        this.requestArgs = requestArgs;
    }

    public int getPortNumber() {
        String portCommand = "-p";

        if (convertArgs().contains(portCommand)) {
            return Integer.valueOf(convertArgs().get(convertArgs().indexOf(portCommand) + 1));
        }
        return 5000;
    }

    public String getDirectory() {
        String directoryCommand = "-d";

        if (convertArgs().contains(directoryCommand)) {
            return convertArgs().get(convertArgs().indexOf(directoryCommand) + 1);
        }
        return System.getProperty("user.dir") + "/public";
    }

    private List<String> convertArgs() {
        List<String> argsList = Arrays.asList(requestArgs);
        return argsList;
    }
}
