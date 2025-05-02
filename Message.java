import java.time.ZonedDateTime;

/**
 * A simple Message class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Team 1 Lab 23
 * @version March 30, 2025
 */
public class Message implements MessageInterface {

    // Fields
    private String senderID;
    private String recipientID;
    private String contents;
    private ZonedDateTime timestamp;
    public static final String FILEPATH = "MessageData.txt";

    // Constructors
    public Message(String senderID, String recipientID, String contents) {
        this.senderID = senderID;
        this.recipientID = recipientID;
        this.contents = contents;
        this.timestamp = ZonedDateTime.now();
    }

    // Implement MessageInterface
    public String getSenderID() {
        return this.senderID;
    }

    public void setSenderID(String senderID) {
        this.senderID = senderID;
    }

    public String getRecipientID() {
        return this.recipientID;
    }

    public void setRecipientID(String recipientID) {
        this.recipientID = recipientID;
    }

    public String getContents() {
        return this.contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object object) {
        try {
            MessageInterface message = (Message) object;
            return (message.getSenderID().equals(senderID)
                    && message.getRecipientID().equals(recipientID)
                    && message.getTimestamp().equals(timestamp));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("Sender: %s\nRecipient: %s\nContent: %s\n",
                senderID,
                recipientID,
                contents);
    }
}
