package commandLineInterface;

import java.util.Scanner;
import java.util.logging.Logger;

public class CommandLineInterface {

    private InputCommandManager icm = new InputCommandManager();
    private static final Logger log = Logger.getLogger(CommandLineInterface.class.getName());

    // Constructor
    public CommandLineInterface() {
        runCommandLineInterface();
    }

    // get input string
    private String readUserInput() {
        return new Scanner(System.in).nextLine();
    }

    private void runCommandLineInterface() {
        System.out.println("Welcome");
        icm.addressBook.loadContactList();
//        autoSave();
        getInputFromUser();
        icm.addressBook.saveContactList();
        System.out.println("Good Bye");
    }

    private void getInputFromUser() {
        String input;
        String[] inputSplit;
        while (icm.accessRun()) {
            input = readUserInput();
            inputSplit = input.split(" ");
            icm.readInputCommands(inputSplit);
        }
    }

    private void autoSave() {
        new Thread(() -> {
            log.info("AutoSave Thread, started.");
            while (icm.accessRun()) {
                try {
                    Thread.sleep(5_000);
                    icm.addressBook.saveContactList();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            log.info("AutoSave Thread, ended.");
            System.out.println("the last processes has finished running,\nready for the main process to finish.\nGood bye.");
        }).start();
    }
}
