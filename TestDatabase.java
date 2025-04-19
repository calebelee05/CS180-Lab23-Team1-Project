import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class TestDatabase {

    // fields used for testing
    private Database database;

    @Before
    public void setUp() {
        List<UserInterface>  userList = new ArrayList<>();
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
        UserInterface user1 = database.createAccount("User1", "Password1");
        assertTrue(Database.getUserList().contains(user1));
        assertTrue(Database.userExists("User1"));
        UserInterface logIn = Database.logIn("User1", "Password1");
        assertEquals("User1", logIn.getUsername());
        database.deleteAccount(user1);
        assertFalse(Database.userExists("User1"));
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
        UserInterface User1 = database.createAccount("User1", "Password1");
        UserInterface User2 = database.createAccount("User2", "Password2");
        MessageInterface message = database.sendMessage(User1, User2, "message");
        assertTrue(Database.getMessageList().contains(message));
        assertTrue(User1.getMessagesSent().contains(message));
        assertTrue(User2.getMessagesReceived().contains(message));
        ItemInterface item = database.addItem(User1, "Item1", 20.0, "description");
        double balance1 = User1.getBalance();
        double balance2 = User2.getBalance();
        database.transaction(User2, User1, item);
        assertEquals(balance1 - 20.0, User2.getBalance(), 0.001);
        assertEquals(balance2 + 20.0, User1.getBalance(), 0.001);
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

