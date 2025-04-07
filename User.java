
import java.io.*;
import java.util.*;

/**
 * A User class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Team 1 Lab 23
 * @version March 31, 2025
 */
public class User implements Serializable, UserInterface {

    // Fields
    private String username;
    private String password;
    private double balance;
    private ArrayList<ItemInterface> itemsList = new ArrayList<>();
    private ArrayList<MessageInterface> messagesSent = new ArrayList<>();
    private List<MessageInterface> messagesReceived = Collections.synchronizedList(new ArrayList<>());
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

    public List<MessageInterface> getMessagesReceived() {
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

    public void setMessagesReceived(List<MessageInterface> messagesReceived) {
        this.messagesReceived = messagesReceived;
    }

    public void deleteUser() {

        userList.remove(this);

        for (int i = 0; i < itemsList.size(); i++) {
            itemsList.get(i).deleteItem();
        }

        for (int j = 0; j < messagesSent.size(); j++) {
            messagesSent.get(j).deleteMessage();
        }

        for (int k = 0; k < messagesReceived.size(); k++) {
            messagesReceived.get(k).deleteMessage();
        }

        writeObject();

    } // Remove user from userList, delete all items user has listed

    // Item Listing
    public void addItem(String name, double price, String description) {
        itemsList.add(new Item(name, price, description, this.username));
    } // Shouldn't allow users to add more than one items with same name?

    public ItemInterface getItem(String name) throws ItemNotFoundException {
        for (int i = 0; i < itemsList.size(); i++) {
            if (itemsList.get(i).getName().equals(name)) {
                return itemsList.get(i);
            }
        }
        throw new ItemNotFoundException("Item does not exist");
    } // Return item with this name

    public void deleteItem(ItemInterface item) {
        itemsList.remove(item);
        item.deleteItem();
    } // Delete item from listing (and from database)

    public void setItem(ItemInterface item, String name, double price, String description) {
        item.setName(name);
        item.setPrice(price);
        item.setDescription(description);
    } // Edit item in the listing with this name

    // Balance Tracking
    public void buyItem(ItemInterface item) {
        this.balance -= item.getPrice();
    } // user bought item; decrease balance by item price

    public void sellItem(ItemInterface item) {
        this.balance += item.getPrice();
    } // user sold item; increase balance by item price

    // Messaging
    public void sendMessage(UserInterface recipient, String content) {
        MessageInterface message = new Message(username, recipient.getUsername(), content);
        messagesSent.add(message);
        recipient.receiveMessage(message);
    } // Send message to recipient

    public void receiveMessage(MessageInterface message) {
        messagesReceived.add(message);
    }

    public ArrayList<MessageInterface> getMessageFromUser(String senderID) {
        ArrayList<MessageInterface> messagesFromUser = new ArrayList<>();
        for (int i = 0; i < messagesReceived.size(); i++) {
            if (messagesReceived.get(i).getSenderID().equals(senderID)) {
                messagesFromUser.add(messagesReceived.get(i));

            }
        }
        return messagesFromUser;
    }

    public ArrayList<MessageInterface> getMessageToUser(String recipientID) {
        ArrayList<MessageInterface> messagesToUser = new ArrayList<>();
        for (int i = 0; i < messagesSent.size(); i++) {
            if (messagesSent.get(i).getRecipientID().equals(recipientID)) {
                messagesToUser.add(messagesSent.get(i));
            }
        }
        return messagesToUser;
    }

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public boolean equals(Object object) {
        try {
            UserInterface user = (User) object;
            return username.equals(user.getUsername());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("Username: %s\nPassword: %s\nBalance: %.2f\n", username, password, balance);
    }
}
