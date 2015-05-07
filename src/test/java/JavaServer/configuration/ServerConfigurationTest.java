package JavaServer.configuration;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ServerConfigurationTest {
    private ServerConfiguration serverConfiguration;

    @Test
    public void return5000IfArgsDoNotContainPFlag() {
        String[] args = new String[3];
        args[0] = "Server";
        args[1] = "Hello";
        args[2] = "Why";

        serverConfiguration = new ServerConfiguration(args);

        assertEquals(5000, serverConfiguration.getPortNumber());
    }

    @Test
    public void return600IfArgsContainsPFlag() {
        String[] args = new String[3];
        args[0] = "-p";
        args[1] = "6000";
        args[2] = "Why";

        serverConfiguration = new ServerConfiguration(args);

        assertEquals(6000, serverConfiguration.getPortNumber());
    }

    @Test
    public void returnsUserDirectoryIfNoPublicDirectoryOtherwise() {
        String[] args = new String[4];
        args[0] = "java";
        args[1] = "-jar";
        args[2] = "-s";
        args[3] = "sizzurp";

        serverConfiguration = new ServerConfiguration(args);
        String directoryResult = System.getProperty("user.dir") + "/public";

        assertEquals(directoryResult, serverConfiguration.getDirectory());
    }

    @Test
    public void returnsDirectorySetByTheDFlag() {
        String[] args = new String[4];
        args[0] = "java";
        args[1] = "-jar";
        args[2] = "-d";
        args[3] = "/cob_spec/public";

        serverConfiguration = new ServerConfiguration(args);

        assertEquals("/cob_spec/public", serverConfiguration.getDirectory());
    }
}
