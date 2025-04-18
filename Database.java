
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A Database class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Team 1 Lab 23
 * @version April 15, 2025
 */
public class Database implements DatabaseInterface {

    private String userFile;
    private String itemFile;
    private String messageFile;
    private static List<UserInterface> userList = Collections.synchronizedList(new ArrayList<>());
    private static List<ItemInterface> itemList = Collections.synchronizedList(new ArrayList<>());
    private static List<MessageInterface> messageList = Collections.synchronizedList(new ArrayList<>());

    public Database(String userFile, String itemFile, String messageFile) {
        this.userFile = userFile;
        this.itemFile = itemFile;
        this.messageFile = messageFile;
    }

    public static void main(String[] args) {
        String username = "User1";
        String password = "password";
        Database database = new Database(User.FILEPATH, Item.FILEPATH, Message.FILEPATH);
        UserInterface user1 = database.createAccount(username, password);
        if (userList.contains(user1)) {
            System.out.println("true");
        }
        try {
            UserInterface user = Database.logIn("User1", "password");
            System.out.printf("Successfully logged in: %s\n", user.getUsername());
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
        ItemInterface item1 = database.addItem(user1, "Item1", 10.0, "Test item 1");
        if (itemList.contains(item1)) {
            System.out.println("true");
        }
        database.deleteItem(user1, item1);
        if (itemList.contains(item1)) {
            System.out.println("false");
        }
        UserInterface user2 = database.createAccount("User2", "password2");
        database.sendMessage(user1, user2, "hi");
    }

    public String getUserFile() {
        return userFile;
    }

    public String getItemFile() {
        return itemFile;
    }

    public String getMessageFile() {
        return messageFile;
    }

    public static List<UserInterface> getUserList() {
        return userList;
    }

    public static List<ItemInterface> getItemList() {
        return itemList;
    }

    public static List<ItemInterface> searchItemList(String textQuery,
            double lowPriceQuery,
            double highPriceQuery,
            String sellerQuery) {
        List<ItemInterface> filteredList = new ArrayList<>();

        for (ItemInterface item : itemList) {
            if (item.getPrice() >= lowPriceQuery &&
                item.getPrice() <= highPriceQuery) {
                    if (item.getName().toLowerCase().contains(textQuery.toLowerCase()) ||
                        item.getDescription().toLowerCase().contains(textQuery.toLowerCase())) {
                            if (sellerQuery != null) {
                                if (item.getSellerID().contains(sellerQuery)) { // no lowercase; case sensitive
                                    filteredList.add(item);
                                }
                            } else {
                                filteredList.add(item);
                            }
                    }
                }
        }
        /*
        if (textQuery != null) {
            for (ItemInterface item : itemList) { // item name has precedence; added first
                if (item.getName().toLowerCase().contains(textQuery.toLowerCase())) {
                    if (filteredList.indexOf(item) == -1) {
                        filteredList.add(item);
                    }
                }
            }
            for (ItemInterface item : itemList) {
                if (item.getDescription().toLowerCase().contains(textQuery.toLowerCase())) {
                    if (filteredList.indexOf(item) == -1) {
                        filteredList.add(item);
                    }
                }
            }
        }

        if (lowPriceQuery != -1) {
            if (!filteredList.isEmpty()) {
                for (ItemInterface item : filteredList) {
                    if (!(item.getPrice() >= lowPriceQuery)) {
                        filteredList.remove(item);
                    }
                }
            } else {
                for (ItemInterface item : itemList) {
                    if (item.getPrice() >= lowPriceQuery) {
                        if (filteredList.indexOf(item) == -1) {
                            filteredList.add(item);
                        }
                    }
                }
            }
        }

        if (highPriceQuery != -1) {
            if (!filteredList.isEmpty()) {
                for (ItemInterface item : filteredList) {
                    if (!(item.getPrice() <= highPriceQuery)) {
                        filteredList.remove(item);
                    }
                }
            } else {
                for (ItemInterface item : itemList) {
                    if (item.getPrice() <= highPriceQuery) {
                        if (filteredList.indexOf(item) == -1) {
                            filteredList.add(item);
                        }
                    }
                }
            }
        }

        if (sellerQuery != null) {
            if (!filteredList.isEmpty()) {
                for (ItemInterface item : filteredList) {
                    if (!(item.getSellerID().contains(sellerQuery))) { // no lowercase; case sensitive
                        filteredList.remove(item);
                    }
                }
            } else {
                for (ItemInterface item : itemList) {
                    if (item.getSellerID().contains(sellerQuery)) { // no lowercase; case sensitive
                        if (filteredList.indexOf(item) == -1) {
                            filteredList.add(item);
                        }
                    }
                }
            }
        }
        */
        return filteredList;
    }

    public static List<MessageInterface> getMessageList() {
        return messageList;
    }

    public void setUserFile(String userFile) {
        this.userFile = userFile;
    }

    public void setItemFile(String itemFile) {
        this.itemFile = itemFile;
    }

    public void setmessageFile(String messageFile) {
        this.messageFile = messageFile;
    }

    public static void setUserList(List<UserInterface> list) {
        userList = list;
    }

    public static void setItemList(List<ItemInterface> list) {
        itemList = list;
    }

    public static void setMessageList(List<MessageInterface> list) {
        messageList = list;
    }

    public void writeUser() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(userFile))) {
            oos.writeObject(userList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void writeItem() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(itemFile))) {
            oos.writeObject(itemList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void writeMessage() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(messageFile))) {
            oos.writeObject(messageList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public void readUser() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile))) {
            userList = Collections.synchronizedList((List<UserInterface>) ois.readObject());
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (IOException ioe) {
            userList = Collections.synchronizedList(new ArrayList<UserInterface>());
        }
    }

    public void readItem() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile))) {
            itemList = Collections.synchronizedList((List<ItemInterface>) ois.readObject());
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (IOException ioe) {
            itemList = Collections.synchronizedList(new ArrayList<ItemInterface>());
        }
    }

    public void readMessage() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(userFile))) {
            messageList = Collections.synchronizedList((List<MessageInterface>) ois.readObject());
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (IOException ioe) {
            messageList = Collections.synchronizedList(new ArrayList<MessageInterface>());
        }
    }

    public void write() {
        writeUser();
        writeItem();
        writeMessage();
    }

    public void read() {
        readUser();
        readItem();
        readMessage();
    }

    public synchronized void update() {
        write();
        read();
    }

    public UserInterface createAccount(String username, String password) {
        UserInterface newUser = new User(username, password, 0.0);
        userList.add(newUser);
        update();
        return newUser;
    }

    public void deleteAccount(UserInterface user) {
        user.deleteUser();
        update();
    }

    public static UserInterface logIn(String username, String password) throws UserNotFoundException {
        for (UserInterface user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        throw new UserNotFoundException("Wrong username or password");
    }

    public static boolean userExists(String username) {
        for (UserInterface user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public ItemInterface addItem(UserInterface user, String itemName, double price, String description) {
        ItemInterface item = user.addItem(itemName, price, description);
        itemList.add(item);
        update();
        return item;
    }

    public void deleteItem(UserInterface user, ItemInterface item) {
        user.deleteItem(item);
        update();
    }

    public MessageInterface sendMessage(UserInterface sender, UserInterface recipient, String content) {
        MessageInterface message = sender.sendMessage(recipient, content);
        messageList.add(message);
        update();
        return message;
    }

    public void transaction(UserInterface buyer, UserInterface seller, ItemInterface item) {
        buyer.buyItem(item);
        seller.sellItem(item);
        update();
    }
}
