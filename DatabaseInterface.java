
/**
 * A DatabaseInterface interface
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Team 1 Lab 23
 * @version April 15, 2025
 */
public interface DatabaseInterface {

    String getUserFile();

    String getItemFile();

    String getMessageFile();

    void setUserFile(String userFile);

    void setItemFile(String itemFile);

    void setMessageFile(String messageFile);

    void writeUser();

    void writeItem();

    void writeMessage();

    void readUser();

    void readItem();

    void readMessage();

    void write();

    void read();

    void update();

    UserInterface createAccount(String username, String password);

    void deleteAccount(UserInterface user);

    ItemInterface addItem(UserInterface user, String itemName, double price, String description, String imageString);

    void deleteItem(ItemInterface item);

    MessageInterface sendMessage(UserInterface sender, UserInterface recipient, String content);

    void transaction(UserInterface buyer, UserInterface seller, ItemInterface item) throws Exception;
}
