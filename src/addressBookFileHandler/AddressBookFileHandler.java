package addressBookFileHandler;

import contact.Contact;

import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddressBookFileHandler {
    private File savedContactsFile = new File("src/savedContactList.data");
    private static final Logger log = Logger.getLogger(AddressBookFileHandler.class.getName());

    public synchronized void saveListToDisk(List<Contact> listOfContacts) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(savedContactsFile))) {
            oos.writeObject(listOfContacts);
        } catch (IOException e) {
            log.log(Level.SEVERE, "List could not be saved, error: ", e);
            return;
        }
        log.info("list was saved to file");
    }

    /**
     * @ SuppressAnger
     * ┻━┻︵ \(°¤°)/ ︵ ┻━┻
     */
    @SuppressWarnings("unchecked")
    public List<Contact> loadListFromDisk() {
        if (savedContactsFile.exists() && savedContactsFile.isFile()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedContactsFile))) {
                return (List<Contact>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                log.log(Level.SEVERE, "Saved contact list was unable to load from file, error: ", e);
            }
        }
        return null;
    }
}
