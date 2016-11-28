package addressBook;

import contact.Contact;
import contactList.ContactList;
import addressBookFileHandler.AddressBookFileHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AddressBook {
    private ContactList cl = new ContactList();
    private AddressBookFileHandler fileHandler = new AddressBookFileHandler();

    // Input functions
    // add
    public void inputCommandAdd(String firstName, String lastName, String eMail) {
        cl.addContactToList(new Contact(firstName, lastName, eMail));
    }

    // list
    public void inputCommandList() {
        int listSize = sortByFirstName().size();
        if (listSize > 0) {
            System.out.println("Listing all contacts:");
            sortByFirstName().forEach(this::showContact);
        }
        System.out.printf("%s %d %s\n", "There are currently:", listSize, "saved contact/s in your Address Book");
    }

    // Search
    public void inputCommandSearch(String query) {
        int count = sortByFirstName().stream()
                .filter(contact ->
                        contact.getFirstName().toLowerCase().startsWith(query) ||
                                contact.getLastName().toLowerCase().startsWith(query))
                .mapToInt(contact -> (1))
                .sum();
        System.out.println(count + " contact/s matched your search:");
        if (count > 0) {
            sortByFirstName().stream()
                    .filter(contact ->
                            contact.getFirstName().toLowerCase().startsWith(query) ||
                                    contact.getLastName().toLowerCase().startsWith(query))
                    .forEach(this::showContact);
        }
    }

    // delete
    public void inputCommandDelete(String idStringToMatch) {
        for (int i = 0; i < cl.getContactList().size(); i++) {
            if (cl.getContactList().get(i).getUuid().toString().equals(idStringToMatch)) {
                System.out.format("Match found: %s\n%s %s, will be deleted from your Address Book.\n",
                        cl.getContactList().get(i).getUuid().toString(), cl.getContactList().get(i).getFirstName(), cl.getContactList().get(i).getLastName()
                );
                cl.deleteContactFromList(i);
                break;
            }
        }
    }

    // save
    public void saveContactList() {
        fileHandler.saveListToDisk(new ArrayList<>(cl.getContactList()));
    }

    // load
    public void loadContactList() {
        if (fileHandler.loadListFromDisk() != null){
            cl.loadExistingContactList(fileHandler.loadListFromDisk());
        } else {
            cl.createNewContactList();
        }
    }

    // show contact
    private void showContact(Contact contact) {
        System.out.format("Contact UUID: %s\n  First name: %s\n   Last name: %s\n\t  E-mail: %s\n\n",
                contact.getUuid().toString(), contact.getFirstName(), contact.getLastName(), contact.geteMail()
        );
    }

    // sort
    private List<Contact> sortByFirstName() {
        List<Contact> sortedContactList = new ArrayList<>(cl.getContactList());
        sortedContactList.sort(Comparator.comparing(contact -> contact.getFirstName().toLowerCase()));
        return sortedContactList;
    }
}
