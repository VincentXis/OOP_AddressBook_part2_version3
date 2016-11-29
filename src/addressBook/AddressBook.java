package addressBook;

import contact.Contact;
import contactList.ContactList;
import addressBookFileHandler.AddressBookFileHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public class AddressBook {
    private ContactList cl = new ContactList();
    private AddressBookFileHandler fileHandler = new AddressBookFileHandler();
    private static final Logger log = Logger.getLogger(AddressBook.class.getName());

    // add - logged - feedback done
    public void add(String firstName, String lastName, String eMail) {
        cl.addContactToList(new Contact(firstName, lastName, eMail));
        System.out.printf("New contact: %s %s, was added to your Address Book\n", firstName, lastName);

        log.info("User added new contact to list");
    }

    // list - logged - feedback done
    public void list() {
        int listSize = sortByFirstName().size();
        if (listSize > 0) {
            System.out.println("Listing all contacts in Address Book:\n");
            sortByFirstName().forEach(this::showContact);
        }
        System.out.printf("%s %d %s\n", "There are currently:", listSize, "saved contact/s in your Address Book\n");

        log.info("User requested to see all contacts in list.");
    }

    // Search - logged - feedback done
    public void search(String query) {
        log.info("User requested to search contacts in list.");
        System.out.printf("%s %s\n\n", "Searching for contact with names starting with:", query);

        int resultCount = sortByFirstName().stream().filter(contact -> contact.getFirstName().toLowerCase().startsWith(query) || contact.getLastName().toLowerCase().startsWith(query)
        ).mapToInt(contact -> (1)).sum();

        sortByFirstName().stream().filter(contact -> contact.getFirstName().toLowerCase().startsWith(query) || contact.getLastName().toLowerCase().startsWith(query)
        ).forEach(this::showContact);

        if (resultCount > 0) {
            log.info("User found match on search.");
        } else {
            log.info("User found no match on search.");
        }

        System.out.println(resultCount + " contact/s matched your input.\n");
    }

    public void delete(String idStringToMatch) {
        log.info("User requested to delete a contact in the list");

        for (int i = 0; i < cl.getContactList().size(); i++) {
            if (cl.getContactList().get(i).getUuid().toString().equals(idStringToMatch)) {
                System.out.format("\nMatch found: %s\n%s %s, will be deleted from your Address Book.\n\n",
                        cl.getContactList().get(i).getUuid().toString(), cl.getContactList().get(i).getFirstName(), cl.getContactList().get(i).getLastName()
                );
                cl.deleteContactFromList(i);
                log.info("User deleted contact from list");
                return;
            }
        }

        System.out.println("No contact matched the provided UUID in your Address Book: No contact was deleted.");

        log.info("User tried to delete a contact from the list, no contact matched provided id string.");
    }

    public void saveContactList() {
        fileHandler.saveListToDisk(new ArrayList<>(cl.getContactList()));
    }

    public void loadContactList() {
        if (fileHandler.loadListFromDisk() != null) {
            cl.loadExistingContactList(fileHandler.loadListFromDisk());
        } else {
            cl.createNewContactList();
        }
    }

    private void showContact(Contact contact) {
        System.out.format("Contact UUID: %s\n  First name: %s\n   Last name: %s\n\t  E-mail: %s\n\n",
                contact.getUuid().toString(), contact.getFirstName(), contact.getLastName(), contact.getEmail()
        );
    }

    private List<Contact> sortByFirstName() {
        List<Contact> sortedContactList = new ArrayList<>(cl.getContactList());
        sortedContactList.sort(Comparator.comparing(contact -> contact.getFirstName().toLowerCase()));
        return sortedContactList;
    }
}
