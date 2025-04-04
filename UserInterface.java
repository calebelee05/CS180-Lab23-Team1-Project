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
}
