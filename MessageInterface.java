import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * A MessageInterface interface
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Caleb Lee
 * @version April 3, 2025
 */
public interface MessageInterface extends Serializable {
    // Accessors and Modifiers
    String getSenderID();
    void setSenderID(String senderID);
    String getRecipientID();
    void setRecipientID(String recipientID);
    String getContents();
    void setContents(String contents);
    ZonedDateTime getTimestamp();
    void setTimeStamp(ZonedDateTime timestamp);
    void deleteMessage();
}
