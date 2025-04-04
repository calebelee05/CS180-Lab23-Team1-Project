import java.io.*;
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

public class Message implements Serializable, MessageInterface {

    // Fields
    private String senderID;
    private String recipientID;
    private String contents;
    private ZonedDateTime timestamp;
    private static List<MessageInterface> messageList = Collections.synchronizedList(new ArrayList<>());
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
        messageList.add(this);
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

    public static List<MessageInterface> getList() {
        return messageList;
    }

    // Implement File I/O methods
    public static synchronized void writeObject() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILEPATH))) {
                oos.writeObject(messageList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static synchronized List<MessageInterface> readObject() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILEPATH))) {
            List<MessageInterface> objects = (List<MessageInterface>) ois.readObject();
            messageList = Collections.synchronizedList(objects);
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
        return messageList;
    }

    @Override
    public String toString() {
        return String.format("Sender: %s\nRecipient: %s\nContent: %s\n", senderID, recipientID, contents);
    }
}
