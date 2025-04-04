import java.util.ArrayList;

/**
 * A UserInterface interface
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Caleb Lee
 * @version April 3, 2025
 */
public interface UserInterface {
    // Accessors and Modifiers
    String getUsername();
    String getPassword();
    double getBalance();
    ArrayList<Item> getItems();
    ArrayList<Message> getMessagesSent();
    ArrayList<Message> getMessagesReceived();
    void setUsername(String username);
    void setPassword(String password);
    void setBalance(double balance);
    void setItems(ArrayList<Item> itemsList);
    void setMessagesSent(ArrayList<Message> messagesSent);
    void setMessagesReceived(ArrayList<Message> messagesReceived);
    boolean equals(User user);

    // TODO: Need to implement

    // User Account
    void deleteUser(); // Remove user from userList, delete all items user has listed
    // Consider how to deal with messages sent by user; delete completely? or display as "DeletedUser" to message recipients?

    // Item Listing
    void addItem(String name, double price, String description); // Shouldn't allow users to add more than one items with same name?
    Item getItem(String name); // Return item with this name
    void deleteItem(Item item); // Delete item from listing (and from database)
    void setItem(Item item, String name, double price, String description); // Edit item in the listing with this name

    // Balance Tracking
    void buyItem(Item item); // user bought item; decrease balance by item price
    void sellItem(Item item); // user sold item; increase balance by item price

    // Messaging
    void sendMessage(User recipient, String content); // Send message to recipient

}
