
import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import org.junit.Test;

/**
 * Testing the User class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Team 1 Lab 23
 * @version April 6, 2025
 */

public class TestUser {

    // Set the input
    String username = "User1";
    String password = "abcd1234";
    double balance = 1000.0;

    String itemName = "Item 1";
    String itemDescription = "Test Item 1";
    double itemPrice = 15.0;
    ItemInterface item = new Item(itemName, itemPrice, itemDescription, username);
    ArrayList<ItemInterface> itemList = new ArrayList<>();

    String sellerID = "User2";
    String itemBoughtName = "Item bought";
    String itemBoughtDescription = "Test buyItem";
    double itemBoughtPrice = 12.0;
    ItemInterface itemBought = new Item(itemBoughtName, itemBoughtPrice, itemBoughtDescription, sellerID);

    MessageInterface messageSent = new Message(username, "User2", "Hello");
    MessageInterface messageReceived = new Message("User2", username, "Hi");
    ArrayList<MessageInterface> messagesSent = new ArrayList<>();
    ArrayList<MessageInterface> messagesReceived = new ArrayList<>();

    UserInterface user1 = new User(username, password, balance);

    // Test accessors and modifiers
    @Test
    public void TestGettersSetters() {
        String newUsername = "Modified user";
        String newPassword = "Modified password";
        double newBalance = 2000.0;

        user1.setUsername(newUsername);
        user1.setBalance(newBalance);
        user1.setPassword(newPassword);

        itemList.add(item);
        messagesSent.add(messageSent);
        messagesReceived.add(messageReceived);
        user1.setItems(itemList);
        user1.setMessagesSent(messagesSent);
        user1.setMessagesReceived(messagesReceived);

        UserInterface user1Copy = new User(newUsername, newPassword, newBalance);

        assertEquals(itemList, user1.getItems()); // Test getItems()
        assertEquals(messagesSent, user1.getMessagesSent()); // Test getMessagesSent()
        assertEquals(messagesReceived, user1.getMessagesReceived()); // Test getMessagesReceived()
        assertEquals(newUsername, user1.getUsername()); // Test getUsername()
        assertEquals(newBalance, user1.getBalance(), 0.0001); // Test getBalance()
        assertEquals(newPassword, user1.getPassword()); // Test getPassword()
        assertEquals(true, user1.equals(user1Copy)); // Test equals() method
    }

    // Test Item listing manipulation
    @Test
    public void testItemManipulation() throws ItemNotFoundException {
        String newItemName = "Modified Item 2";
        String newItemDescription = "Modified Test Item 2";
        double newItemPrice = 10.0;

        user1.addItem(itemName, itemPrice, itemDescription);

        assertEquals(item, user1.getItem(itemName)); // Test addItem() and getItem()

        user1.setItem(user1.getItem(itemName), newItemName, newItemPrice, newItemDescription);
        ItemInterface item2 = new Item(newItemName, newItemPrice, newItemDescription, username);

        assertEquals(item2, user1.getItem(newItemName)); // Test setItem()

        user1.buyItem(itemBought);
        user1.sellItem(item2);

        // Test buyItem() and sellItem()
        assertEquals(balance - itemBoughtPrice + newItemPrice, user1.getBalance(), 0.001); 

        user1.deleteItem(user1.getItem(newItemName));

        assertEquals(0, user1.getItems().size()); // Test deleteItem()
    }

    // Test user messaging
    @Test
    public void testMessage() throws UserNotFoundException {
        String messageContent = "Hello";
        String username2 = "User2";
        UserInterface user2 = new User(username2, "qwer", 0.0);

        user1.sendMessage(user2, messageContent);

        // Test sendMessage(), receiveMessage(), getMessageFromUser()
        assertEquals(messageContent, user2.getMessageFromUser(username).get(0).getContents()); 
        // Test getMessageToUser()
        assertEquals(messageContent, user1.getMessageToUser(username2).get(0).getContents()); 
    }
}
