# CS180-Team-Project
Spring 25' CS180 Lab section 23 Team 1 Project repository

Topic: A Market Place

Phase 1: Building the database, interfaces and test cases
This phase covers the creation of the database for the market place. This includes creating and deleting the user and retailer accounts. It also involves adding, deleting and searching for items. 

  Message.java
    Fields
    - private String senderID: The ID of the message sender.
    - private String recipientID: The ID of the message recipient.
    - private String contents: The content of the message.
    - private ZonedDateTime timestamp: The timestamp of when the message was created.
    - private static ArrayList<Message> message: A static ArrayList to store Message objects.
    - private static final String FILEPATH: A constant String representing the file path for storing Message objects ("MessageData.txt").

    Constructors
    - public Message(String senderID, String recipientID, String contents): Initializes a new Message object with the provided sender ID, recipient ID, and message content. It also sets the timestamp to the current time and adds the newly instatiated Message object to the arraylist. If the file specified by `FILEPATH` does not exist, it creates a new file.

    Methods
    - public String getSenderID(): Returns the sender ID.
    - public void setSenderID(String senderID): Sets the sender ID.
    - public String getRecipientID(): Returns the recipient ID.
    - public void setRecipientID(String recipientID): Sets the recipient ID.
    - public String getContents(): Returns the content.
    - public void setContents(String contents): Sets the content.
    - public ZonedDateTime getTimestamp(): Returns the timestamp for the message.
    - public void setTimeStamp(ZonedDateTime timestamp): Sets the timestamp for the message.
    - public static ArrayList<Message> getList(): Returns a copy of the message arraylist.
    - public synchronized void writeObject(ArrayList<Message> list): Writes the arraylist of Message objects to the file.
    - public synchronized ArrayList<Message> readObject(): Reads Message objects from the file and returns them as an ArrayList.
    - public String toString(): Returns a String representation of the Message object.
    
    Interfaces Implemented
    - Serializable: Serializes instances of the Message class.
    - MessageInterface: Defines the methods implemented by the Message class.
    - Writable<Message>: Used to read and write Message objects from and to a file.

  MessageInterface.java
    Methods:
    - String getSenderID(): returns the senderID
    - void setSenderID(String senderID): takes the senderID as a parameter and sets the value for the instance variable
    - String getRecipientID(): returns the recipientID
    - void setRecipientID(String recipientID): takes the recipientID as a parameter and sets the value for the instance variable
    - String getContents(): returns the contents
    - void setContents(String contents): takes the contents as a parameter and sets the value for the instance variable
    - ZonedDateTime getTimestamp(): returns the timestamp
    - void setTimeStamp(ZonedDateTime timestamp): takes the timestamp as a parameter and sets the value for the instance variable
    
  User.java
  UserInterface.java
  Item.java
  ItemInterface.java
  Writable.java
