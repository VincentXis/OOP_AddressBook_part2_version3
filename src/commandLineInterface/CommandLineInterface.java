package commandLineInterface;

import addressBook.AddressBook;

import java.util.Scanner;

public class CommandLineInterface {
    private AddressBook addressBook = new AddressBook();
    private boolean run = true;

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
        if (userInput[0].toLowerCase().equals("end"))
            userInput[0] = "quit";
        switch (userInput[0]) {
            case "add":
                addressBook.inputCommandAdd(userInput[1], userInput[2], userInput[3]);
                break;
            case "list":
                addressBook.inputCommandList();
                break;
            case "search":
                addressBook.inputCommandSearch(userInput[1]);
                break;
            case "delete":
                addressBook.inputCommandDelete(userInput[1]);
                break;
            case "help":
                inputCommandHelp();
                break;
            case "quit":
                inputCommandQuit();
                break;
            default:
                System.out.println("Invalid input command: " + userInput[0] +
                        "\nPlease try again, or type: \"help\" for a list of available commands");
                break;
        }
    }

    private void inputCommandHelp() {
        System.out.format("%s\n%s\n\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n",
                "The input for a command has to be lowercase to register", "List of all available commands:",
                "add:    add a new contact to list", "list:   show all contacts in list", "delete: remove a contact from list",
                "search: find contact/s in list ", "help:   to get here, lists all available commands", "quit:   exit the application"
        );
    }

    private boolean inputCommandQuit() {
        System.out.println("Shutting down application, this may take a few seconds\nwaiting for active processes to finish:");
        return run = !run;
    }

    private void autoSaveThread() {
        new Thread(() -> {
            while (run) {
                try {
                    Thread.sleep(5_000);
                    addressBook.saveContactList();
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
            System.out.println("the last processes has finished running,\nready for the main process to finish.\nGood bye.");
        }).start();
    }
}
