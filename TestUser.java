import static org.junit.Assert.assertEquals;

import java.awt.event.ItemListener;
import java.security.MessageDigest;
import java.util.ArrayList;
import org.junit.*;

public class TestUser {
    // Set the input
    String user1Input = "User1\nabcd1234\n1000.0\nItem1\n10.0\nTest Item1\nHello\nUser2\n";
        
    String user2Input = "User2\nqwer0000\n3500.0\nItem2\n15.0\nTest Item2\nHi\nUser1\n";

    String username = "User1";
    String password = "abcd1234";
    double balance = 1000.0;
    ItemInterface item = new Item("Item1",10.0,"Test Item",username);
    ArrayList<ItemInterface> itemList = new ArrayList<>();
    MessageInterface messageSent = new Message("User1", "User2", "Hello");
    MessageInterface messageReceived = new Message("User2", "User1", "Hi");
    ArrayList<MessageInterface> messagesSent = new ArrayList<>();
    ArrayList<MessageInterface> messagesReceived = new ArrayList<>();
    UserInterface user1 = new User("", "", 0.0);

    @Test
    public void testGettersSetters() {
        user1.setUsername(username);
        user1.setBalance(balance);
        user1.setPassword(password);
        itemList.add(item);
        messagesSent.add(messageSent);
        messagesReceived.add(messageReceived);
        user1.setItems(itemList);
        user1.setMessagesSent(messagesSent);
        user1.setMessagesReceived(messagesReceived);

        UserInterface user1Copy = new User("User1","abcd1234",1000.0);

        assertEquals(itemList,user1.getItems());
        assertEquals(messagesSent,user1.getMessagesSent());
        assertEquals(messagesReceived,user1.getMessagesReceived());
        assertEquals(username, user1.getUsername());
        assertEquals(balance, user1.getBalance());
        assertEquals(password, user1.getPassword());
        assertEquals(true,user1.equals(user1Copy));
    }

    @Test
    public void test
}
