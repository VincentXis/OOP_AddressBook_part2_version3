package contactList;

import contact.Contact;

import java.util.List;

public class ContactList {
    private List<Contact> contactList;
    // Load list from file or new ArrayList
    public ContactList() {
    }
    // get list
    public List<Contact> getContactList(){
        return contactList;
    }
    public void updateContactList(List<Contact> contactList){
        this.contactList = contactList;
    }
    // add contact
    public void addContactToList(Contact contact){
        contactList.add(contact);
    }
    // delete contact
    public void deleteContactFromList(int indexOfContactToDelete){
        contactList.remove(indexOfContactToDelete);
    }
    public void showContact(Contact contact){
        System.out.format("\nContact UUID: %s\n  First name: %s\n   Last name: %s\n\t  E-mail: %s\n",
                contact.getUuid().toString(),
                contact.getFirstName(),
                contact.getLastName(),
                contact.geteMail()
        );
    }

}
