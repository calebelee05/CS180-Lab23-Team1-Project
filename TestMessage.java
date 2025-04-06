import org.junit.Test;
import static org.junit.Assert.*;
import java.time.ZonedDateTime;

public class TestMessage {

    String senderID = "Sender1";
    String recipientID = "Recipient1";
    String contents = "Hello";
    MessageInterface message = new Message(senderID, recipientID, contents);


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

    @Test
    public void testEqualsAndToString() {
        MessageInterface copyMessage = new Message(senderID, recipientID, "Different Contents");

    }
}
