package addressBook;

import contact.Contact;
import contactList.ContactList;
import contantListFileHandler.ContactListFileHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AddressBook {
    private ContactList cl = new ContactList();
    private ContactListFileHandler clf = new ContactListFileHandler();
    // Input functions
    // add
    public void inputAdd(String firstName,String lastName,String eMail){
        cl.addContactToList(new Contact(firstName, lastName, eMail));
    }
    // list
    public void inputList(){
        sortByFirstName().forEach(contact -> cl.showContact(contact));
    }
    public void inputSearch(String query){
        int count = sortByFirstName().stream().filter(contact ->
                contact.getFirstName().toLowerCase().startsWith(query) || contact.getLastName().toLowerCase().startsWith(query))
                .mapToInt(contact -> (1))
                .sum();

        System.out.println(count + " contact/s matched your search");
        if (count > 0){
            sortByFirstName().stream().filter(contact ->
                    contact.getFirstName().toLowerCase().startsWith(query) || contact.getLastName().toLowerCase().startsWith(query))
                    .forEach(contact -> cl.showContact(contact));
        }
    }
    // delete
    public void inputDelete(String idStringToMatch){
        for (int i = 0; i < cl.getContactList().size(); i++) {
            if (cl.getContactList().get(i).getUuid().toString().equals(idStringToMatch)){
                cl.deleteContactFromList(i);
                break;
            }
        }
    }
    public void saveContactList(){
        clf.saveListToDisk(new ArrayList<>(cl.getContactList()));
    }
    public void loadContactList(){
        cl.updateContactList(clf.loadListFromDisk());
    }
    private List<Contact> sortByFirstName(){
        List<Contact> sortedContactList = new ArrayList<>(cl.getContactList());
        sortedContactList.sort(Comparator.comparing(contact -> contact.getFirstName().toLowerCase()));
        return sortedContactList;
    }
}
