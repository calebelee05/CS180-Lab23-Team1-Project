
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.ZonedDateTime;
import java.util.List;
import org.junit.Before;

/**
 * A JUnit test class for the Message Class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Team 1 Lab 23
 * @version April 06, 2025
 */
public class TestMessage {

    // fields used for testing
    private MessageInterface message1;
    private MessageInterface message2;

    @Before
    public void setUp() {
        Database.getMessageList().clear();
        message1 = new Message("Sender1", "Recipient1", "Contents1");
        message2 = new Message("Sender2", "Recipient2", "Contents2");
        Database.getMessageList().add(message1);
        Database.getMessageList().add(message2);
    }

    // Testing Mutators and Accessors
    @Test
    public void testGettersSetters() {
        assertEquals("Sender1", message1.getSenderID());
        assertEquals("Recipient1", message1.getRecipientID());
        assertEquals("Contents1", message1.getContents());
        message1.setSenderID("SenderG");
        message1.setRecipientID("RecipientG");
        message1.setContents("ContentsG");
        ZonedDateTime now = ZonedDateTime.now();
        message1.setTimestamp(now);
        assertEquals("SenderG", message1.getSenderID());
        assertEquals("RecipientG", message1.getRecipientID());
        assertEquals("ContentsG", message1.getContents());
        assertEquals(now, message1.getTimestamp());
    }

    // Testing equals and toString methods
    @Test
    public void testEqualsAndToString() {
        message2.setTimestamp(message1.getTimestamp());
        MessageInterface copyMessage = new Message("SenderG", "RecipientG", "ContentsG");
        ((Message) copyMessage).setTimestamp(message1.getTimestamp());
        copyMessage.setSenderID(message1.getSenderID());
        copyMessage.setRecipientID(message1.getRecipientID());
        assertTrue(message1.equals(copyMessage));
        assertFalse(message1.equals(message2));

        String expectedMessage = String.format("Sender: %s\nRecipient: %s\nContent: %s\n",
                message1.getSenderID(),
                message1.getRecipientID(), "Contents1");
        assertEquals(expectedMessage, message1.toString());
    }
}
