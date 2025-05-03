import java.util.*;

/**
 * A User class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Team 1 Lab 23
 * @version March 31, 2025
 */
public class User implements UserInterface {

    // Fields
    private String username;
    private String password;
    private double balance;
    private List<ItemInterface> itemsList = new ArrayList<>();
    private List<MessageInterface> messagesSent = Collections.synchronizedList(new ArrayList<>());
    private List<MessageInterface> messagesReceived = Collections.synchronizedList(new ArrayList<>());
    public static final String FILEPATH = "UserData.txt";

    // Constructor
    public User(String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
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

    public List<ItemInterface> getItemsList() {
        return itemsList;
    }

    public List<MessageInterface> getMessagesSent() {
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

    public void setItemsList(List<ItemInterface> itemsList) {
        this.itemsList = itemsList;
    }

    public void setMessagesSent(List<MessageInterface> messagesSent) {
        this.messagesSent = messagesSent;
    }

    public void setMessagesReceived(List<MessageInterface> messagesReceived) {
        this.messagesReceived = messagesReceived;
    }

    // Item Listing
    public ItemInterface addItem(String name, double price, String description, String imageString) {
        ItemInterface newItem = new Item(name, price, description, this.username, imageString);
        itemsList.add(newItem);
        System.out.println(itemsList);
        return newItem;
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
    } // Delete item from listing (and from database)

    public void setItem(ItemInterface item, String name, double price, String description) {
        item.setName(name);
        item.setPrice(price);
        item.setDescription(description);
    } // Edit item in the listing

    // Balance Tracking
    public synchronized void buyItem(ItemInterface item) throws Exception {
        if (balance >= item.getPrice()) {
            balance -= item.getPrice();
            item.setBuyerID(username);
        } else {
            throw new Exception("Not enough balance!");
        }
    } // user bought item; decrease balance by item price

    public synchronized void sellItem(ItemInterface item) {
        balance += item.getPrice();
    } // user sold item; increase balance by item price

    // Messaging
    public MessageInterface sendMessage(UserInterface recipient, String content) {
        MessageInterface message = new Message(username, recipient.getUsername(), content);
        messagesSent.add(message);
        recipient.receiveMessage(message);
        return message;
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

    public void deleteMessageSent(MessageInterface message) {
        messagesSent.remove(message);
    }

    public void deleteMessageReceived(MessageInterface message) {
        messagesReceived.remove(message);
    }

    @Override
    public boolean equals(Object object) {
        try {
            UserInterface user = (User) object;
            return (username.equals(user.getUsername()) && password.equals(user.getPassword()));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("Username: %s\nPassword: %s\nBalance: %.2f\n", username, password, balance);
    }
}
