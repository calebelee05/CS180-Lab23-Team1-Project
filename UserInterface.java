
import java.io.Serializable;
import java.util.*;

/**
 * A UserInterface interface
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Caleb Lee
 * @version April 3, 2025
 */
public interface UserInterface extends Serializable {

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

    // User Account
    void deleteUser(); // Remove user from userList, delete all items user has listed

    // Item Listing
    void addItem(String name, double price, String description); // Don't allow users to add items with same name

    ItemInterface getItem(String name) throws ItemNotFoundException; // Return item with this name

    void deleteItem(ItemInterface item); // Delete item from listing (and from database)

    void setItem(ItemInterface item, String name, double price, String description); // Edit item with this name

    // Balance Tracking
    void buyItem(ItemInterface item); // user bought item; decrease balance by item price

    void sellItem(ItemInterface item); // user sold item; increase balance by item price

    // Messaging
    void sendMessage(UserInterface recipient, String content); // Send message to recipient

    void receiveMessage(MessageInterface message); // Receive message

    ArrayList<MessageInterface> getMessageFromUser(String senderID); // Find message sent by username

    ArrayList<MessageInterface> getMessageToUser(String recipientID); // Find message sent to username

}
