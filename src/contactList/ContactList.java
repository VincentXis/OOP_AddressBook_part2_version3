package contactList;

import contact.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ContactList {
    private static final Logger log = Logger.getLogger(ContactList.class.getName());
    private List<Contact> contactList;

    // Load Contact List from file
    public void loadExistingContactList(List<Contact> contactList) {
        this.contactList = contactList;
        log.info("Contact List was loaded from file");
    }
    // Create empty Contact List
    public void createNewContactList() {
        this.contactList = new ArrayList<>();
        log.log(Level.WARNING, "List was unable to be loaded from file, new ArrayList was created.");
    }

    // get list
    public List<Contact> getContactList() {
        return contactList;
    }

    // add contact
    public void addContactToList(Contact contact) {
        contactList.add(contact);
    }

    // delete contact
    public void deleteContactFromList(int indexOfContactToDelete) {
        contactList.remove(indexOfContactToDelete);
    }
}
