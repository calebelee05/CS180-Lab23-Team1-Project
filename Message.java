import java.time.ZonedDateTime;

/**
 * A simple Message class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Chris Souk
 * @version March 30, 2025
 */

public class Message {

    // Fields
    private String contents;
    private ZonedDateTime timestamp;

    // Constructors
    public Message(String contents) {
        this.contents = contents;
        this.timestamp = ZonedDateTime.now();
    }

    // Getters & Setters
    public String getContents() {
        return this.contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    public void setTimeStamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }
}