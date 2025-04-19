
import static org.junit.Assert.*;
import java.util.*;
import org.junit.Test;
import org.junit.Before;


/**
 * Testing the User class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Team 1 Lab 23
 * @version April 6, 2025
 */

public class TestUser {
    private UserInterface user1;

    @Before
    public void setUp() {
        user1 = new User("User1", "password1", 1000.0);
    }

    // Test accessors and modifiers
    @Test
    public void testGettersSetters() {
        // initial
        assertEquals("User1", user1.getUsername());
        assertEquals("password1", user1.getPassword());
        assertEquals(1000.0, user1.getBalance(), 0.001);
        // modifying
        user1.setUsername("UserG");
        user1.setPassword("passwordG");
        user1.setBalance(2000.0);
        // verification
        assertEquals("UserG", user1.getUsername());
        assertEquals("passwordG", user1.getPassword());
        assertEquals(2000.0, user1.getBalance(), 0.001);
    }

    // Test Item listing manipulation
    @Test
    public void testItemManipulation() throws ItemNotFoundException {
        // adding and getting
        ItemInterface item1 = user1.addItem("Item1", 50.0, "Description1");
        assertEquals(item1, user1.getItem("Item1"));
        // modification
        user1.setItem(item1, "ItemG", 75.0, "Description2");
        assertEquals("ItemG", item1.getName());
        assertEquals(75.0, item1.getPrice(), 0.001);
        assertEquals("Description2", item1.getDescription());
        // buying and selling
        user1.buyItem(item1);
        assertEquals(1000.0 - 75.0, user1.getBalance(), 0.001);
        user1.sellItem(item1);
        assertEquals(1000.0, user1.getBalance(), 0.001);
        // deletion
        user1.deleteItem(item1);
        assertTrue(user1.getItemsList().isEmpty());
    }

    // Test user messaging
    @Test
    public void testMessage() throws UserNotFoundException {
        UserInterface user2 = new User("User2", "password2", 500.0);
        MessageInterface message = user1.sendMessage(user2, "Message");
        assertTrue(user1.getMessagesSent().contains(message)); // list sent
        ArrayList<MessageInterface> received = user2.getMessageFromUser("User1"); // received
        assertTrue(received.contains(message));
        ArrayList<MessageInterface> sent = user1.getMessageToUser("User2"); // message sent
        assertTrue(sent.contains(message));
    }
}
