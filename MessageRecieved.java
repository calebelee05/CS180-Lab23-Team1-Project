/**
 * A simple MessageRecieved class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Chris Souk
 * @version March 30, 2025
 */

public class MessageRecieved extends Message {

    // Fields
    private String sender;

    // Constructor
    public MessageRecieved(String contents, String sender) {
        super(contents);
        this.sender = sender;
    }

    // Getters & Setters
    public String getSender() {
        return this.sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}