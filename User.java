import java.io.*;
import java.util.*;

/**
 * A User class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Caleb Lee
 * @version March 31, 2025
 */

public class User implements Serializable, UserInterface, Writable<User> {

    // Fields
    private String username;
    private String password;
    private double balance;
    private Item[] itemsList;
    private Message[] messagesSent;
    private Message[] messagesReceived;
    private static ArrayList<User> user = new ArrayList<>();
    private static File userData = null;

    // Constructor
    public User(String username, String password, double balance, Item[] itemsList, Message[] messagesSent, Message[] messageReceieved, String filename) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.itemsList = itemsList;
        this.messagesSent = messagesSent;
        this.messagesReceived = messageReceieved;
        user.add(this);
        if (userData == null) {    
            try {
                userData = new File(filename);
                if (!userData.exists()) {
                    userData.createNewFile();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
    }

    // Implement UserInterface
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public Item[] getItems() {
        return itemsList;
    }
    
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

    public void setItems(Item[] itemsList) {
        this.itemsList = itemsList;
    }

    public void setMessagesSent(Message[] messagesSent) {
        this.messagesSent = messagesSent;
    }

    public void setMessagesReceived(Message[] messagesReceived) {
        this.messagesReceived = messagesReceived;
    }

    public static ArrayList<User> getList() {
        return new ArrayList<>(user);
    }

    // Implement Writable interface
    public synchronized void writeObject(ArrayList<User> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userData))) {
                oos.writeObject(list);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public synchronized ArrayList<User> readObject() {
        ArrayList<User> objects = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userData))) {
            objects = (ArrayList<User>) ois.readObject();
            return objects;
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
        return objects;
    }

    @Override
    public String toString() {
        return String.format("Username: %s\nPassword: %s\nBalance: %.2f\n", username, password, balance);
    }
}
