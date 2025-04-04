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
    private ArrayList<ItemInterface> itemsList = new ArrayList<>();
    private ArrayList<MessageInterface> messagesSent = new ArrayList<>();
    private ArrayList<MessageInterface> messagesReceived = new ArrayList<>();
    private static List<UserInterface> userList = Collections.synchronizedList(new ArrayList<>());
    private static final String FILEPATH = "UserData.txt";

    // Constructor
    public User(String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
        File saveFile = new File(FILEPATH);
        try {
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
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

    public ArrayList<ItemInterface> getItems() {
        return itemsList;
    }
    
    public ArrayList<MessageInterface> getMessagesSent() {
        return messagesSent;
    }

    public ArrayList<MessageInterface> getMessagesReceived() {
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

    public void setItems(ArrayList<ItemInterface> itemsList) {
        this.itemsList = itemsList;
    }

    public void setMessagesSent(ArrayList<MessageInterface> messagesSent) {
        this.messagesSent = messagesSent;
    }

    public void setMessagesReceived(ArrayList<MessageInterface> messagesReceived) {
        this.messagesReceived = messagesReceived;
    }

    public boolean equals(UserInterface user) {
        return username.equals(user.getUsername());
    }

    // TODO: Implement methods in UserInterface

    /* TODO: Find and return user with the username from the userList
     * public static User findUser(String username)
     */

    public void deleteUser() {} // Remove user from userList, delete all items user has listed
    // Consider how to deal with messages sent by user; delete completely? or display as "DeletedUser" to message recipients?

    // Item Listing
    public void addItem(String name, double price, String description) {} // Shouldn't allow users to add more than one items with same name?
    public ItemInterface getItem(String name) {return null;} // Return item with this name
    public void deleteItem(ItemInterface item) {} // Delete item from listing (and from database)
    public void setItem(ItemInterface item, String name, double price, String description) {} // Edit item in the listing with this name

    // Balance Tracking
    public void buyItem(ItemInterface item) {} // user bought item; decrease balance by item price
    public void sellItem(ItemInterface item) {} // user sold item; increase balance by item price

    // Messaging
    public void sendMessage(UserInterface recipient, String content) {} // Send message to recipient


    public static List<UserInterface> getList() {
        return userList;
    }

    // Implement File I/O methods
    public static synchronized void writeObject() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILEPATH))) {
            oos.writeObject(userList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static synchronized List<UserInterface> readObject() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILEPATH))) {
            List<UserInterface> objects = (List<UserInterface>) ois.readObject();
            userList = Collections.synchronizedList(objects);
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
