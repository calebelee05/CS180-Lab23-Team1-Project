import java.io.*;

/**
 * A User class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Caleb Lee
 * @version March 31, 2025
 */

public class User implements Serializable /*, Writable */ {

    private String username;
    private String password;
    private double balance;
    // private Item[] itemsList;
    private Message[] messagesSent;
    private Message[] messagesReceived;

    public User(String username, String password, double balance, /* Item[] itemsList, */ Message[] messagesSent, Message[] messageReceieved) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        // this.itemsList = itemsList;
        this.messagesSent = messagesSent;
        this.messagesReceived = messageReceieved;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    /* public Item[] getItems() {
        return itemsList;
    } */
    
    public Message[] getMessagesSent() {
        return messagesSent;
    }

    public Message[] getMessagesReceived() {
        return messagesReceived;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /* public void setItems(Item[] itemsList) {
        this.itemsList = itemsList;
    }
    */

    public void setMessagesSent(Message[] messagesSent) {
        this.messagesSent = messagesSent;
    }

    public void setMessagesReceived(Message[] messagesReceived) {
        this.messagesReceived = messagesReceived;
    }

    public String toString() {
        return String.format("Username: %s\nPassword: %s\nBalance: %.2f\n", username, password, balance);
    }
}
