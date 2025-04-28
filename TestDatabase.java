
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * A JUnit test class for the Database Class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 2
 *
 * @author Team 1 Lab 23
 * @version April 19, 2025
 */
public class TestDatabase {

    // fields used for testing
    private Database database;

    @Before
    public void setUp() {
        List<UserInterface> userList = new ArrayList<>();
        List<ItemInterface> itemList = new ArrayList<>();
        List<MessageInterface> messageList = new ArrayList<>();
        Database.setUserList(userList);
        Database.setItemList(itemList);
        Database.setMessageList(messageList);
        database = new Database(User.FILEPATH, Item.FILEPATH, Message.FILEPATH);
    }

    // tests account creation, deletion, login
    @Test
    public void testUserAccountHandling() throws UserNotFoundException {
        UserInterface user1 = database.createAccount("user1", "Password1");
        assertTrue(Database.getUserList().contains(user1));
        assertTrue(Database.userExists("user1"));
        UserInterface logIn = Database.logIn("user1", "Password1");
        assertEquals("user1", logIn.getUsername());
        database.deleteAccount(user1);
        assertFalse(Database.userExists("user1"));
    }

    // tests item addition and deletion
    @Test
    public void testItemAdditionDeletion() throws ItemNotFoundException {
        UserInterface seller = database.createAccount("Seller", "Password");
        ItemInterface item = database.addItem(seller, "Item1", 5.0, "description");
        assertTrue(Database.getItemList().contains(item));
        assertTrue(seller.getItemsList().contains(item));
        database.deleteItem(seller, item);
        assertFalse(Database.getItemList().contains(item));
        assertTrue(seller.getItemsList().isEmpty());
    }

    // tests message sending and transactions
    @Test
    public void testMessageSendingAndTransactions() {
        UserInterface user1 = database.createAccount("User1", "Password1");
        UserInterface user2 = database.createAccount("User2", "Password2");
        MessageInterface message = database.sendMessage(user1, user2, "message");
        assertTrue(Database.getMessageList().contains(message));
        assertTrue(user1.getMessagesSent().contains(message));
        assertTrue(user2.getMessagesReceived().contains(message));
        ItemInterface item = database.addItem(user1, "Item1", 20.0, "description");
        double balance1 = user1.getBalance();
        double balance2 = user2.getBalance();
        try {
            database.transaction(user2, user1, item);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        assertEquals(balance1 - 20.0, user2.getBalance(), 0.001);
        assertEquals(balance2 + 20.0, user1.getBalance(), 0.001);
    }

    // tests item searching
    @Test
    public void testSearchItemList() {
        UserInterface user = database.createAccount("User", "Password");
        database.addItem(user, "pencil", 1.0, "grey");
        database.addItem(user, "book", 2.0, "black");
        List<ItemInterface> results = Database.searchItemList("pen", 0.5, 1.5, null);
        assertEquals(1, results.size());
        assertEquals("pencil", results.get(0).getName());
    }
}
