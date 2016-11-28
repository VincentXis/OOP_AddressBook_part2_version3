package contactList;

import contact.Contact;

import java.util.ArrayList;
import java.util.List;

public class ContactList {

    private List<Contact> contactList;

    // Load Contact List from file
    public void loadExistingContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
    public void createNewContactList(){
        this.contactList = new ArrayList<>();
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
