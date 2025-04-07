import org.junit.Test;
import static org.junit.Assert.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * A JUnit test class for the Message Class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Zaid Al Awartani
 * @version April 06, 2025
 */

public class TestMessage {
    // Instance variables used for testing with generic messages
    String senderID = "Sender1";
    String recipientID = "Recipient1";
    String contents = "Hello";
    MessageInterface message = new Message(senderID, recipientID, contents);

    // Testing Mutators and Accessors
    @Test
    public void testGettersSetters() {
        assertEquals(senderID, message.getSenderID());
        assertEquals(recipientID, message.getRecipientID());
        assertEquals(contents, message.getContents());

        String newSender = "ModifiedSender";
        String newRecipient = "ModifiedRecipient";
        String newContents = "Updated Content";
        message.setSenderID(newSender);
        message.setRecipientID(newRecipient);
        message.setContents(newContents);
        ZonedDateTime newTimestamp = ZonedDateTime.now();
        message.setTimeStamp(newTimestamp);
        assertEquals(newSender, message.getSenderID());
        assertEquals(newRecipient, message.getRecipientID());
        assertEquals(newContents, message.getContents());
        assertEquals(newTimestamp, message.getTimestamp());
    }

    // Testing equals and toString methods
    @Test
    public void testEqualsAndToString() {
        MessageInterface copyMessage = new Message(senderID, recipientID, "Different Contents");
        ((Message) copyMessage).setTimeStamp(message.getTimestamp());
        assertTrue(message.equals(copyMessage));

        MessageInterface newMessage = new Message(senderID, recipientID, contents);
        String expectedToString = String.format("Sender: %s\\nRecipient: %s\\nContent: %s\\n\", senderID, recipientID, contents");
        assertEquals(expectedToString, newMessage.toString());
    }

    // Testing the method of deleting a message
    @Test
    public void testDeleteMessage() {
        ArrayList<MessageInterface> currentMessages = new ArrayList<>(Message.getList());
        for (MessageInterface m : currentMessages) {
            ((Message) m).deleteMessage();
        }
        MessageInterface message1 = new Message("DeleteSender1", "DelRecipient1", "Deleted");
        MessageInterface message2 = new Message("KeptSender", "KeptRecipient", "Keep");
        List<MessageInterface> messages = Message.getList();
        assertTrue(messages.contains(message1));
        assertTrue(messages.contains(message2));
        ((Message) message1).deleteMessage();
        messages = Message.getList();
        assertFalse(messages.contains(message1));
        assertTrue(messages.contains(message2));
    }

    // Testing File IO Operations
    @Test
    public void testObjectReadAndWrite() {
        ArrayList<MessageInterface> everyMessage = new ArrayList<>(Message.getList());
        for (MessageInterface m : everyMessage) {
            ((Message) m).deleteMessage();
        }

        MessageInterface continuedMessage1 = new Message("ContinuedSender1", "ContinuedRecipient1", "Continued Message 1");
        MessageInterface continuedMessage2 = new Message("ContinuedSender2", "ContinuedRecipient2", "Continued Message 2");
        Message.writeObject(new ArrayList<>(Message.getList()));
        everyMessage  = new ArrayList<>(Message.getList());
        for (MessageInterface m : everyMessage) {
            ((Message) m).deleteMessage();
        }
        assertEquals(0, Message.getList().size());

        List<MessageInterface> readMessages = Message.readObject();
        assertEquals(2, readMessages.size());

        boolean foundMessage1 = false;
        boolean foundMessage2 = false;
        for (MessageInterface m : readMessages) {
            if ((m.getContents().equals("Continued Message 1") && m.getRecipientID().equals("ContinuedRecipient1")) && m.getSenderID().equals("ContinuedSender1")) {
                foundMessage1 = true;
            }
            if ((m.getContents().equals("Continued Message 2") && m.getRecipientID().equals("ContinuedRecipient2")) && m.getSenderID().equals("ContinuedSender2")) {
                foundMessage2 = true;
            }
        }
        assertTrue(foundMessage1 && foundMessage2);
    }
}


