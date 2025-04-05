import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.*;

/**
 * A simple Message class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Chris Souk
 * @version March 30, 2025
 */

public class Message implements Serializable, MessageInterface, Writable<Message> {

    // Fields
    private String senderID;
    private String recipientID;
    private String contents;
    private ZonedDateTime timestamp;
    private static ArrayList<Message> message = new ArrayList<>();
    private static final String FILEPATH = "MessageData.txt";

    // Constructors
    public Message(String senderID, String recipientID, String contents) {
        this.senderID = senderID;
        this.recipientID = recipientID;
        this.contents = contents;
        this.timestamp = ZonedDateTime.now();
        File saveFile = new File(FILEPATH);
        try {
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        message.add(this);
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

    public void setTimeStamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public static ArrayList<Message> getList() {
        return new ArrayList<>(message);
    }

    // Implement Writable interface
    public synchronized void writeObject(ArrayList<Message> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILEPATH))) {
                oos.writeObject(list);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public synchronized ArrayList<Message> readObject() {
        ArrayList<Message> objects = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILEPATH))) {
            objects = (ArrayList<Message>) ois.readObject();
            return objects;
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
        return objects;
    }

    @Override
    public String toString() {
        return String.format("Sender: %s\nRecipient: %s\nContent: %s\n", senderID, recipientID, contents);
    }
}
