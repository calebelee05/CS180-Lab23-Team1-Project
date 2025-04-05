import java.util.*;

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
    ArrayList<ItemInterface> getItems();
    ArrayList<MessageInterface> getMessagesSent();
    List<MessageInterface> getMessagesReceived();
    void setUsername(String username);
    void setPassword(String password);
    void setBalance(double balance);
    void setItems(ArrayList<ItemInterface> itemsList);
    void setMessagesSent(ArrayList<MessageInterface> messagesSent);
    void setMessagesReceived(List<MessageInterface> messagesReceived);
    boolean equals(UserInterface user);

    // TODO: Need to implement

    // User Account
    void deleteUser(); // Remove user from userList, delete all items user has listed
    // Consider how to deal with messages sent by user; delete completely? or display as "DeletedUser" to message recipients?

    // Item Listing
    void addItem(String name, double price, String description); // Shouldn't allow users to add more than one items with same name?
    ItemInterface getItem(String name); // Return item with this name
    void deleteItem(ItemInterface item); // Delete item from listing (and from database)
    void setItem(ItemInterface item, String name, double price, String description); // Edit item in the listing with this name

    // Balance Tracking
    void buyItem(ItemInterface item); // user bought item; decrease balance by item price
    void sellItem(ItemInterface item); // user sold item; increase balance by item price

    // Messaging
    void sendMessage(UserInterface recipient, String content); // Send message to recipient

}
