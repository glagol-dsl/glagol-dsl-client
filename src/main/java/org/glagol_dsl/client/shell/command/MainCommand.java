package org.glagol_dsl.client.shell.command;

import org.glagol_dsl.client.ConsoleStream;
import org.glagol_dsl.client.Version;
import org.glagol_dsl.client.socket.Client;
import picocli.CommandLine.Option;

import java.io.IOException;

@picocli.CommandLine.Command(
    name = "Glagol DSL Client",
    version = {Version.VERSION}
)
public class MainCommand implements Command {

    private static final String DEFAULT_HOST = "127.0.0.1";

    private static final Integer DEFAULT_PORT = 51151;

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "Display this help message")
    private boolean usageHelpRequested;

    @Option(names = {"-v", "--version"}, versionHelp = true, description = "Print version information and exit")
    private boolean versionRequested;

    @Option(names = {"-H", "--host"}, description = "Host on which the Glagol DSL Server is running")
    private String host;

    @Option(names = {"-p", "--port"}, description = "Host port on which the Glagol DSL Server is running")
    private Integer port;

    public MainCommand() {
        host = lookupHost(DEFAULT_HOST);
        port = lookupPort(DEFAULT_PORT);
    }

    private String lookupHost(String defaultValue) {
        String value = System.getenv("GLAGOL_DSL_HOST");
        return isEmpty(value) ? defaultValue : value;
    }

    private Integer lookupPort(Integer defaultValue) {
        String value = System.getenv("GLAGOL_DSL_PORT");
        return isEmpty(value) ? defaultValue : Integer.valueOf(value);
    }

    Client createClient() {
        return new Client(host, port);
    }

    private boolean isEmpty(String host) {
        return host == null || host.length() == 0;
    }

    /**
     * This method is only used for double-dispatching
     *
     * @param command Previously executed command
     */
    @Override
    public void execute(CompileCommand command, ConsoleStream consoleStream) throws IOException {
        command.execute(this, consoleStream);
    }
    /**
     * This method is only used for double-dispatching
     *
     * @param command Previously executed command
     */
    @Override
    public void execute(CleanCommand command, ConsoleStream consoleStream) throws IOException {
        command.execute(this, consoleStream);
    }
}