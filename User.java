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

public class User implements Serializable, UserInterface {

    // Fields
    private String username;
    private String password;
    private double balance;
    private ArrayList<Item> itemsList = new ArrayList<>();
    private ArrayList<Message> messagesSent = new ArrayList<>();
    private ArrayList<Message> messagesReceived = new ArrayList<>();
    private static ArrayList<User> userList = new ArrayList<>();
    private static File saveFile = null;

    // Constructor
    public User(String username, String password, double balance, ArrayList<Item> itemsList, ArrayList<Message> messagesSent, ArrayList<Message> messageReceieved, String filename) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        this.itemsList = itemsList;
        this.messagesSent = messagesSent;
        this.messagesReceived = messageReceieved;
        if (saveFile == null) {    
            try {
                saveFile = new File(filename);
                if (!saveFile.exists()) {
                    saveFile.createNewFile();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        userList.add(this);
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

    public ArrayList<Item> getItems() {
        return itemsList;
    }
    
    public ArrayList<Message> getMessagesSent() {
        return messagesSent;
    }

    public ArrayList<Message> getMessagesReceived() {
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

    public void setItems(ArrayList<Item> itemsList) {
        this.itemsList = itemsList;
    }

    public void setMessagesSent(ArrayList<Message> messagesSent) {
        this.messagesSent = messagesSent;
    }

    public void setMessagesReceived(ArrayList<Message> messagesReceived) {
        this.messagesReceived = messagesReceived;
    }

    public boolean equals(User user) {
        return username.equals(user.getUsername());
    }

    // TODO: Implement methods in UserInterface

    /* TODO: Find and return user with the username from the userList
     * public static User findUser(String username)
     */
    


    public static ArrayList<User> getList() {
        return userList;
    }

    // Implement File I/O methods
    public static synchronized void writeObject() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile))) {
            oos.writeObject(userList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static synchronized ArrayList<User> readObject() {
        ArrayList<User> objects = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile))) {
            userList = (ArrayList<User>) ois.readObject();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return userList;
    }

    @Override
    public String toString() {
        return String.format("Username: %s\nPassword: %s\nBalance: %.2f\n", username, password, balance);
    }
}
