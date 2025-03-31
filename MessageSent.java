/**
 * A simple MessageSent class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Chris Souk
 * @version March 30, 2025
 */

public class MessageSent extends Message {

    // Fields
    private String recipient;

    // Constructors
    public MessageSent(String contents, String recipient) {
        super(contents);
        this.recipient = recipient;
    }

    // Getters & Setters
    public String getRecipient() {
        return this.recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

}