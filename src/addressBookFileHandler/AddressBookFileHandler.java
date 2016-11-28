package addressBookFileHandler;

import contact.Contact;

import java.io.*;
import java.util.List;

public class AddressBookFileHandler {
    private File savedContactsFile = new File("src/savedContactList.data");

    public void saveListToDisk(List<Contact> listOfContacts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savedContactsFile))) {
            oos.writeObject(listOfContacts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * ┻━┻︵ \(°¤°)/ ︵ ┻━┻
     */
    @SuppressWarnings("unchecked")
    public List<Contact> loadListFromDisk() {
        if (savedContactsFile.exists() && savedContactsFile.isFile()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedContactsFile))) {
                return (List<Contact>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
