
import java.io.Serializable;
import java.util.*;

/**
 * A UserInterface interface
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Team 1 Lab 23
 * @version April 3, 2025
 */
public interface UserInterface extends Serializable {

    // Accessors and Modifiers
    String getUsername();

    String getPassword();

    double getBalance();

    ArrayList<ItemInterface> getItemsList();

    List<MessageInterface> getMessagesSent();

    List<MessageInterface> getMessagesReceived();

    void setUsername(String username);

    void setPassword(String password);

    void setBalance(double balance);

    void setItemsList(ArrayList<ItemInterface> itemsList);

    void setMessagesSent(List<MessageInterface> messagesSent);

    void setMessagesReceived(List<MessageInterface> messagesReceived);

    // User Account
    void deleteUser(); // Remove user from userList, delete all items user has listed

    // Item Listing
    ItemInterface addItem(String name, double price, String description, String imageString);

    ItemInterface getItem(String name) throws ItemNotFoundException; // Return item with this name

    void deleteItem(ItemInterface item); // Delete item from listing (and from database)

    void setItem(ItemInterface item, String name, double price, String description); // Edit item with this name

    // Balance Tracking
    void buyItem(ItemInterface item) throws Exception; // user bought item; decrease balance by item price

    void sellItem(ItemInterface item); // user sold item; increase balance by item price

    // Messaging
    MessageInterface sendMessage(UserInterface recipient, String content); // Send message to recipient

    void receiveMessage(MessageInterface message); // Receive message

    ArrayList<MessageInterface> getMessageFromUser(String senderID); // Find message sent by username

    ArrayList<MessageInterface> getMessageToUser(String recipientID); // Find message sent to username

}
