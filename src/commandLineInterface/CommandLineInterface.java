package commandLineInterface;

import addressBook.AddressBook;

import java.security.InvalidParameterException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandLineInterface {

    private AddressBook addressBook = new AddressBook();
    private boolean run = true;
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
        addressBook.loadContactList();
//        autoSave();
        getInputFromUser();
        addressBook.saveContactList();
        System.out.println("Good Bye");
    }

    private void getInputFromUser() {
        String input;
        String[] inputSplit;
        while (run) {
            input = readUserInput();
            inputSplit = input.split(" ");
            readInputCommands(inputSplit);
        }
    }

    private void readInputCommands(String[] userInput) {
        String errorMessage;
        if (userInput[0].toLowerCase().equals("end"))
            userInput[0] = "quit";
        try {
            switch (userInput[0]) {
                case "add":
                    if (userInput.length == 4) {
                        addressBook.add(userInput[1], userInput[2], userInput[3]);
                        break;
                    }
                    errorMessage = "add requires 4 parameters, received: " + userInput.length;
                    System.out.println(errorMessage);
                    throw new InvalidParameterException(errorMessage);
                case "list":
                    if (userInput.length == 1) {
                        addressBook.list();
                        break;
                    }
                    errorMessage = "list requires 1 parameter, received: " + userInput.length;
                    System.out.println(errorMessage);
                    throw new InvalidParameterException(errorMessage);
                case "search":
                    if (userInput.length == 2) {
                        addressBook.search(userInput[1]);
                        break;
                    }
                    errorMessage = "search requires 2 parameters, received: " + userInput.length;
                    System.out.println(errorMessage);
                    throw new InvalidParameterException(errorMessage);
                case "delete":
                    if (userInput.length == 2) {
                        addressBook.delete(userInput[1]);
                        break;
                    }
                    errorMessage = "delete requires 2 parameters, received: " + userInput.length;
                    System.out.println(errorMessage);
                    throw new InvalidParameterException(errorMessage);
                case "help":
                    if (userInput.length == 1) {
                        help();
                        break;
                    }
                    errorMessage = "help requires 1 parameter, received: " + userInput.length;
                    System.out.println(errorMessage);
                    throw new InvalidParameterException(errorMessage);
                case "quit":
                    if (userInput.length == 1) {
                        quit();
                        break;
                    }
                    errorMessage = "quit requires 1 parameter, received: " + userInput.length;
                    System.out.println(errorMessage);
                    throw new InvalidParameterException(errorMessage);
                default:
                    log.info("User failed to enter a valid command: " + userInput[0]);
                    System.out.println("Invalid input command: " + userInput[0] +
                            "\nPlease try again, or type: \"help\" for a list of available commands");
                    break;
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "User failed to enter parameter requirement for: " + userInput[0], e);
        }
    }

    private boolean quit() {
        System.out.println("Shutting down application, this may take a few seconds\nwaiting for active processes to finish:");
        return run = !run;
    }


    private void autoSave() {
        new Thread(() -> {
            log.info("AutoSave Thread, started.");
            while (run) {
                try {
                    Thread.sleep(5_000);
                    addressBook.saveContactList();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            log.info("AutoSave Thread, ended.");
            System.out.println("the last processes has finished running,\nready for the main process to finish.\nGood bye.");
        }).start();
    }

    private void help() {
        System.out.format("%s\n%s\n\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n", "The input for a command has to be lowercase to register",
                "List of all available commands:",
                "add:    add a new contact to list",
                "list:   show all contacts in list",
                "delete: remove a contact from list",
                "search: find contact/s in list ",
                "help:   to get here, lists all available commands",
                "quit:   exit the application"
        );
    }
}
