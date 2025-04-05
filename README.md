# CS180-Team-Project
Spring 25' CS180 Lab section 23 Team 1 Project repository

Topic: A Market Place

Phase 1: Building the database, interfaces and test cases
This phase covers the creation of the database for the market place. This includes creating and deleting the user and retailer accounts. It also involves adding, deleting and searching for items. 

## Message.java
    Fields
    - private String senderID: The ID of the message sender.
    - private String recipientID: The ID of the message recipient.
    - private String contents: The content of the message.
    - private ZonedDateTime timestamp: The timestamp of when the message was created.
    - private static ArrayList<Message> message: A static ArrayList to store Message objects.
    - private static final String FILEPATH: A constant String representing the file path for storing Message objects ("MessageData.txt").

    Constructors
    - public Message(String senderID, String recipientID, String contents): Initializes a new Message object with the provided sender ID, recipient ID, and message content. Sets the timestamp to the current time and adds the newly instatiated Message object to the arraylist. Creates a new file when the first object is instantiated.

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

## MessageInterface.java
    Methods:
    - String getSenderID(): Returns the senderID.
    - void setSenderID(String senderID): Sets the senderID.
    - String getRecipientID(): Returns the recipientID.
    - void setRecipientID(String recipientID): Sets the recipientID.
    - String getContents(): Returns the contents.
    - void setContents(String contents): Sets the contents.
    - ZonedDateTime getTimestamp(): Returns the timestamp.
    - void setTimeStamp(ZonedDateTime timestamp): Sets the timestamp.
    
## User.java
    Fields
    - private String username: The username of the user.
    - private String password: The password of the user.
    - private double balance: The current balance of the user.
    - private ArrayList<ItemInterface> itemsList: An ArrayList to store the items listed by the user.
    - private ArrayList<MessageInterface> messagesSent: An ArrayList to store the messages sent by the user.
    - private List<MessageInterface> messagesReceived: A synchronized List to store the messages received by the user.
    - private static List<UserInterface> userList: A synchronized List to store all User objects.
    - private static final String FILEPATH: A constant String representing the file path for storing User objects ("UserData.txt").

    Constructors
    - public User(String username, String password, double balance): Initializes a new User object with the provided username, password, and balance. Creates a new file if it doesn't exist and adds the newly instantiated User object to the arraylist.

    Methods
    - public String getUsername(): Returns the username of the user.
    - public String getPassword(): Returns the password of the user.
    - public double getBalance(): Returns the current balance of the user.
    - public ArrayList<ItemInterface> getItems(): Returns the list of items listed by the user.
    - public ArrayList<MessageInterface> getMessagesSent(): Returns the list of messages sent by the user.
    - public List<MessageInterface> getMessagesReceived(): Returns the list of messages received by the user.
    - public void setUsername(String username): Sets the username of the user.
    - public void setPassword(String password): Sets the password of the user.
    - public void setBalance(double balance): Sets the balance of the user.
    - public void setItems(ArrayList<ItemInterface> itemsList): Sets the list of items listed by the user.
    - public void setMessagesSent(ArrayList<MessageInterface> messagesSent): Sets the list of messages sent by the user.
    - public void setMessagesReceived(List<MessageInterface> messagesReceived): Sets the list of messages received by the user.
    - public boolean equals(UserInterface user): Checks if this user is equal to another user based on the username.
    - public void deleteUser(): Removes the user from the `userList` and deletes all items listed by the user.
    - public void addItem(String name, double price, String description): Adds a new item to the user's item list.
    - public ItemInterface getItem(String name): Returns the item with the given name from the user's item list.
    - public void deleteItem(ItemInterface item): Deletes an item from the user's item list.
    - public void setItem(ItemInterface item, String name, double price, String description): Edits an item in the user's item list.
    - public void buyItem(ItemInterface item): Decreases the user's balance by the item's price.
    - public void sellItem(ItemInterface item): Increases the user's balance by the item's price.
    - public void sendMessage(UserInterface recipient, String content): Sends a message to the specified recipient.
    - public static List<UserInterface> getList(): Returns the list of all User objects.
    - public static synchronized void writeObject(): Writes the `userList` to the file.
    - public static synchronized List<UserInterface> readObject(): Reads User objects from the file and returns them as a list.
    - public String toString(): Returns a String representation of the User object.

    Interfaces Implemented
    - Serializable: Serializes instances of the User class.
    - UserInterface: Defines the methods implemented by the User class.
- 
  UserInterface.java
  Item.java
  ItemInterface.java
  Writable.java
