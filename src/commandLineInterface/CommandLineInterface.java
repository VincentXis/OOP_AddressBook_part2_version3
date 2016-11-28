package commandLineInterface;

import addressBook.AddressBook;

import java.util.Scanner;
import java.util.logging.Level;

public class CommandLineInterface {
    private AddressBook addressBook = new AddressBook();
    private boolean run = true;
    // Constructor
    public CommandLineInterface(){
        runCommandLineInterface();
    }
    // get input string
    private String readUserInput(){
        return new Scanner(System.in).nextLine();
    }
    private void runCommandLineInterface(){
        addressBook.loadContactList();
        String input;
        String[] inputSplit;
        while (run) {
            input = readUserInput();
            inputSplit = input.split(" ");
            readInputCommands(inputSplit);
        }
        addressBook.saveContactList();
    }
    private void readInputCommands(String[] userInput){
        switch (userInput[0]){
            case "add":
                addressBook.inputAdd(userInput[1],userInput[2],userInput[3]);
                    break;
            case "list":
                addressBook.inputList();
                break;
            case "search":
                addressBook.inputSearch(userInput[1]);
                break;
            case "delete":
                addressBook.inputDelete(userInput[1]);
                break;
            case "help":
                inputHelp();
                break;
            case "quit":
                flipSwitch();
                break;
            default:
                System.out.println("Invalid input command: " + userInput[0] +
                        "\nPlease try again, or type: \"help\" for a list of available commands");
                break;
        }
    }
    private void inputHelp(){
        System.out.format("%s\n%s\n\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n",
                "The input for a command has to be lowercase to register","List of all available commands:",
                "add:    add a new contact to list", "list:   show all contacts in list", "delete: remove a contact from list",
                "search: find contact/s in list ", "help:   to get here, lists all available commands", "quit:   exit the application"
        );
    }
    private boolean flipSwitch() {
        System.out.println("Shutting down application, this may take a few seconds\nwaiting for active processes to finish:");
        return run = !run;
    }
    private void autoSave(){
        new Thread(() -> {
            while (run) {
                try {
                    Thread.sleep(5_000);
                    addressBook.saveContactList();
                } catch (Exception e) {
                }
            }
            System.out.println("the last processes has finished running,\nready for the main process to finish.\nGood bye.");
        }).start();
    }
}
