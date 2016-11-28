package commandLineInterface;

import java.util.logging.Logger;

public class CommandLineInterface {

    private InputCommandManager icm = new InputCommandManager();
    private static final Logger log = Logger.getLogger(CommandLineInterface.class.getName());

    // Constructor
    public CommandLineInterface() {
        runCommandLineInterface();
    }

    private void runCommandLineInterface() {
        log.info("Command Line Interface Started");
        System.out.println("Welcome");
        icm.getInputFromUser();
        log.info("Command Line Interface Finished its run");
    }
}
