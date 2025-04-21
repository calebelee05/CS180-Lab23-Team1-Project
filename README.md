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
    - public boolean equals(Object object): Returns true if the object is a Message object and the senderID, recipientID, timestamp is the same.
    
    Interfaces Implemented
    - MessageInterface: Defines the methods implemented by the Message class.

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

    Extends Serializable interface: Serializes instances of MessageInterface.
    
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
    - public void deleteUser(): Removes the user from the `userList` and deletes all items listed by the user.
    - public void addItem(String name, double price, String description): Adds a new item to the user's item list.
    - public ItemInterface getItem(String name) throws ItemNotFoundException: Returns the item with the given name from the user's item list. Throws ItemNotFoundException if item with the name does not exist in the list.
    - public void deleteItem(ItemInterface item): Deletes an item from the user's item list.
    - public void setItem(ItemInterface item, String name, double price, String description): Edits an item in the user's item list.
    - public void buyItem(ItemInterface item): Decreases the user's balance by the item's price.
    - public void sellItem(ItemInterface item): Increases the user's balance by the item's price.
    - public void sendMessage(UserInterface recipient, String content): Sends a message to the specified recipient.
    - public void receiveMessage(MessageInterface message): Receives a message sent.
    - public MessageInterface getMessageFromUser(String senderID) throws UserNotFoundException: Returns messages sent by the specified sender.
    - public MessageInterface getMessageToUser(String recipient) throws UserNotFoundException: Returns messages sent to the specified recipient.
    - public static List<UserInterface> getList(): Returns the list of all User objects.
    - public static synchronized void writeObject(): Writes the `userList` to the file.
    - public static synchronized List<UserInterface> readObject(): Reads User objects from the file and returns them as a list.
    - public String toString(): Returns a String representation of the User object.
    - public boolean equals(Object object): Checks if this user is equal to another user based on the username.

    Interfaces Implemented
    - UserInterface: Defines the methods implemented by the User class.

## UserInterface.java
    Methods
    - String getUsername(): Returns the username of the user.
    - String getPassword(): Returns the password of the user.
    - double getBalance(): Returns the current balance of the user.
    - ArrayList<ItemInterface> getItems(): Returns the list of items listed by the user.
    - ArrayList<MessageInterface> getMessagesSent(): Returns the list of messages sent by the user.
    - List<MessageInterface> getMessagesReceived(): Returns the list of messages received by the user.
    - void setUsername(String username): Sets the username of the user.
    - void setPassword(String password): Sets the password of the user.
    - void setBalance(double balance): Sets the balance of the user.
    - void setItems(ArrayList<ItemInterface> itemsList): Sets the list of items listed by the user.
    - void setMessagesSent(ArrayList<MessageInterface> messagesSent): Sets the list of messages sent by the user.
    - void setMessagesReceived(List<MessageInterface> messagesReceived): Sets the list of messages received by the user.
    - boolean equals(UserInterface user): Checks if this user is equal to another user based on the username.
    - void deleteUser(): Removes the user from the userList and deletes all items listed by the user.
    - void addItem(String name, double price, String description): Adds a new item to the user's item list.
    - ItemInterface getItem(String name): Returns the item with the given name from the user's item list.
    - void deleteItem(ItemInterface item): Deletes an item from the user's item list.
    - void setItem(ItemInterface item, String name, double price, String description): Edits an item in the user's item list.
    - void buyItem(ItemInterface item): Decreases the user's balance by the item's price.
    - void sellItem(ItemInterface item): Increases the user's balance by the item's price.
    - void sendMessage(UserInterface recipient, String content): Sends a message to the specified recipient.

    Extends Serializable interface: Serializes instances of UserInterface.

## Item.java
    Fields
    - private String name: The name of the item.
    - private double price: The price of the item.
    - private String description: The description of the item.
    - private String sellerID: The ID of the seller of the item.
    - private static List<ItemInterface> itemList: A synchronized List to store all Item objects.
    - private static final String FILEPATH: A constant String representing the file path for storing Item objects (ItemData.txt).

    Constructors
    - public Item(String name, double price, String description, String sellerID): Initializes a new Item object with the provided name, price, description, and seller ID. It also creates a new file if it doesn't exist and adds the newly instantiated Item object to the `itemList`.

    Methods
    - public String getName(): Returns the name of the item.
    - public double getPrice(): Returns the price of the item.
    - public String getDescription(): Returns the description of the item.
    - public String getSellerID(): Returns the seller ID of the item.
    - public void setSellerID(String sellerID): Sets the seller ID of the item.
    - public void setPrice(double price): Sets the price of the item.
    - public void setDescription(String description): Sets the description of the item.
    - public void setName(String itemName): Sets the name of the item.
    - public boolean equals(ItemInterface item): Checks if this item is equal to another item based on the seller ID and name.
    - public void deleteItem(): Removes the item from the `itemList`.
    - public static List<ItemInterface> getList(): Returns the list of all Item objects.
    - public static synchronized void writeObject(): Writes the `itemList` to the file.
    - public static synchronized List<ItemInterface> readObject(): Reads Item objects from the file and returns them as a list.
    - public String toString(): Returns a String representation of the Item object.

    Interfaces Implemented
    - ItemInterface: Defines the methods implemented by the Item class.

## ItemInterface.java
    Methods
    - String getName(): Returns the name of the item.
    - double getPrice(): Returns the price of the item.
    - String getDescription(): Returns the description of the item.
    - String getSellerID(): Returns the seller ID of the item.
    - void setSellerID(String sellerID): Sets the seller ID of the item.
    - void setPrice(double price): Sets the price of the item.
    - void setDescription(String description): Sets the description of the item.
    - void setName(String itemName): Sets the name of the item.
    - boolean equals(ItemInterface item): Checks if this item is equal to another item.
    - void deleteItem(): Removes the item from the itemList.

    Interfaces Implemented
    - Serializable: Serializes instances of ItemInterface.

## ItemNotFoundException.java
    Constructors
    - public ItemNotFoundException(String message): Instantiates a new ItemNotFoundException with the provided message.

## UserNotFoundException.java
    Constructors
    - public UserNotFoundException(String message): Instantiates a new UserNotFoundException with the provided message.

## Test Case Files
    - RunLocalTest.java: Contains the main method to run all the test case classes.
    - TestItem.java: Tests the functionality of the Item class.
    - TestMessage.java: Tests the functionality of the Message class.
    - TestUser.java: Tests the functionality of the User class.

Phase 2: Building the Network I/O
This phase covers the creation of the client-server connectivity and implements Network I/O.

## Client.java
    Fields
    - private final Socket socket: The socket connection to the server.
    - private final PrintWriter writer: Used to send text-based requests to the server.
    - private final ObjectInputStream ois: Used to read object-based responses from the server.

    Constructors
    - public Client(String host, int port) throws IOException: Initializes a new Client object by establishing a socket connection to the specified host and port. It also creates a `PrintWriter` for sending text and an `ObjectInputStream` for receiving objects.

    Methods
    - public Object sendRequest(String... request) throws IOException: Sends one or more lines of text as a request to the server. It then attempts to read a single `Object` as the server's response. Throws an `IOException` if there is an error during communication.
    - public void close() throws IOException: Closes the socket connection and the associated `PrintWriter` and `ObjectInputStream`. Throws an `IOException` if an error occurs during the closing process.
    
    Interfaces Implemented
    - ClientInterface: Defines the methods implemented by the Client class.

## ClientInterface.java
    Methods
    - Object sendRequest(String... request) throws IOException: Defines the method signature for sending a request to a server. Throws an `IOException` if there is an error during the request or response process.
    - void close() throws IOException: Defines the method signature for closing the connection to the server. Throws an `IOException` if an error occurs during the closing process.

## Server.java
    Fields
    - private static final DatabaseInterface DATABASE: An instance of the `Database` class. Gives access to the database operations.
    - private Socket socket: The socket connection established with a client.
    - private UserInterface user: Represents the logged-in user associated with the current client connection.

    Constructors
    - public Server(Socket socket): Initializes a new `Server` object for a specific client connection, using the provided socket.

    Methods
    - public void run(): Implements the `Runnable` interface, defining the execution logic for each client connection thread. It handles the communication flow with the client:
        - Sets up input and output streams for the socket.
        - Implements a login/signup phase where the client can choose to log in to an existing account or create a new one.
        - Enters a home screen loop after successful login, where the server listens for various client commands related to item listings, searching, messages, logout, account deletion, and displaying the user's balance.
        - Handles client logout and account deletion, potentially breaking out of the inner loops to await a new login/signup attempt.
    - public static void main(String[] args): The main entry point for the server application:
        - Defines the port number the server will listen on (8888).
        - Reads data from the database using `DATABASE.read()`.
        - Creates a `ServerSocket` to listen for incoming client connections.
        - Enters an infinite loop, accepting new client connections.
        - For each new connection, it instantiates a new `Server` object and starts it in a new `Thread`, allowing multiple clients to be handled concurrently.
        
    Interfaces Implemented
    - Runnable: Enables the `Server` class to be executed as a separate thread, allowing for multiple concurrent client interactions.
    - Communicator: Defines constants used for communication between the client and server
    - ServerInterface: Defines the methods that the `Server` class implements.

## ServerInterface.java
    -  Defines the methods that the `Server` class implements.

## Communicator.java
    Fields
    - String ERROR_MESSAGE: A constant `String` representing an error message for communication between the client and server.
    - String SUCCESS_MESSAGE: A constant `String` indicating a successful state.
    - String LOG_IN: A constant `String` for the client to request the login screen.
    - String SIGN_UP: A constant `String` for the client to request the signup screen.
    - String LOGGED_IN: A constant `String` sent by the server to indicate a successful login.
    - String ACCOUNT_CREATED: A constant `String` sent by the server to confirm successful account creation.
    - String ITEM_LISTING: A constant `String` for the client to request a list of items.
    - String ITEM_SEARCH: A constant `String` for the client to initiate a search for items.
    - String MESSAGES: A constant `String` for the client to request their messages.
    - String LOG_OUT: A constant `String` for the client to request to log out.
    - String DELETE_ACCOUNT: A constant `String` for the client to request the deletion of their account.
    - String DISPLAY_BALANCE: A constant `String` for the client to request displaying their account balance.

## Database.java
    Fields
    - private String userFile: The file path for storing user data.
    - private String itemFile: The file path for storing item data.
    - private String messageFile: The file path for storing message data.
    - private static List<UserInterface> userList: A List to store all `UserInterface` objects.
    - private static List<ItemInterface> itemList: A List to store all `ItemInterface` objects.
    - private static List<MessageInterface> messageList: A List to store all `MessageInterface` objects.
    
    Constructors
    - public Database(String userFile, String itemFile, String messageFile): Initializes a new `Database` object with the specified file paths for user, item, and message data.
    
    Methods
    - public static void main(String[] args): A main method for basic testing of the `Database` class functionalities.
    - public String getUserFile(): Returns the file path for user data.
    - public String getItemFile(): Returns the file path for item data.
    - public String getMessageFile(): Returns the file path for message data.
    - public static List<UserInterface> getUserList(): Returns a list of all `UserInterface` objects.
    - public static List<ItemInterface> getItemList(): Returns a list of all `ItemInterface` objects.
    - public static List<ItemInterface> searchItemList(String textQuery, double lowPriceQuery, double highPriceQuery, String sellerQuery): Searches the `itemList` for items that match the given field values.
    - public static List<MessageInterface> getMessageList(): Returns a list of all `MessageInterface` objects.
    - public void setUserFile(String userFile): Sets the file path for user data.
    - public void setItemFile(String itemFile): Sets the file path for item data.
    - public void setmessageFile(String messageFile): Sets the file path for message data.
    - public static void setUserList(List<UserInterface> list): Sets the list of all `UserInterface` objects.
    - public static void setItemList(List<ItemInterface> list): Sets the list of all `ItemInterface` objects.
    - public static void setMessageList(List<MessageInterface> list): Sets the list of all `MessageInterface` objects.
    - public void writeUser(): Writes the `userList` to the user data file.
    - public void writeItem(): Writes the `itemList` to the item data file.
    - public void writeMessage(): Writes the `messageList` to the message data file.
    - public void readUser(): Reads the user data file into the `userList`. Creates a new empty list if the file is not found or an error occurs.
    - public void readItem(): Reads the item data file into the `itemList`. Creates a new empty list if the file is not found or an error occurs.
    - public void readMessage(): Reads the message data file into the `messageList`. Creates a new empty list if the file is not found or an error occurs.
    - public void write(): Writes and saves data to the files.
    - public void read(): Reads from the files.
    - public synchronized void update(): Updates the files.
    - public UserInterface createAccount(String username, String password): Creates and returns a new `User` object with the given username and password.
    - public void deleteAccount(UserInterface user): Calls the `deleteUser()` method on the provided `UserInterface` object.
    - public static UserInterface logIn(String username, String password) throws UserNotFoundException: Searches the `userList` for a user with the matching username and password. If found, returns the `UserInterface` object, otherwise, throws a `UserNotFoundException`.
    - public static boolean userExists(String username): Checks if a user with the given username exists in the `userList`.
    - public ItemInterface addItem(UserInterface user, String itemName, double price, String description): Adds a new item for a specified user.
    - public void deleteItem(UserInterface user, ItemInterface item): Removes an item for a specified user.
    - public MessageInterface sendMessage(UserInterface sender, UserInterface recipient, String content): Create a new message and adds it to the `messageList`. Returns the newly created `MessageInterface` object.
    - public void transaction(UserInterface buyer, UserInterface seller, ItemInterface item): Calls the `buyItem()` method on the `buyer` and the `sellItem()` method on the `seller` to conduct a transaction.
    
    Interfaces Implemented
    - DatabaseInterface: Defines the methods implemented by the `Database` class.

## DatabaseInterface.java
    Methods
    - String getUserFile(): Retrieves the file path used for storing user data.
    - String getItemFile(): Retrieves the file path used for storing item data.
    - String getMessageFile(): Retrieves the file path used for storing message data.
    - void setUserFile(String userFile): Sets the file path used for storing user data.
    - void setItemFile(String itemFile): Sets the file path used for storing item data.
    - void setmessageFile(String messageFile): sets the file path used for storing message data.
    - void writeUser(): Writes user data to the storage file.
    - void writeItem(): Writes item data to the storage file.
    - void writeMessage(): Writes message data to the storage file.
    - void readUser(): Reads user data from the storage file.
    - void readItem(): Reads item data from the storage file.
    - void readMessage(): Reads message data from the storage file.
    - void write(): Writes all data (user, item, message) to their respective storage files.
    - void read(): Reads all data (user, item, message) from their respective storage files.
    - void update(): Updates the stored data.
    - UserInterface createAccount(String username, String password): Creates a new user account with the given username and password.
    - void deleteAccount(UserInterface user): Deletes an existing user account.
    - ItemInterface addItem(UserInterface user, String itemName, double price, String description): Adds a new item to the system, associated with a specific user.
    - void deleteItem(UserInterface user, ItemInterface item): Deletes an existing item from the system.
    - MessageInterface sendMessage(UserInterface sender, UserInterface recipient, String content): Sends a message from one user to another with the given content.
    - void transaction(UserInterface buyer, UserInterface seller, ItemInterface item): Processes a transaction between a buyer and a seller for a specific item.

## Test Case Files
    - TestClient.java: Tests the functionality of the Client class.
    - TestServer.java: Tests the functionality of the Server class.

## How to Run
1. Compile Server.java and GUI.java classes with `javac Server.java` and `javac GUI.java`.
2. Initialize the server first with `java Server`, then run the client side with `java GUI`; this will start the GUI.
3. When the GUI pops up, you can choose to either log in or create a new account.
4. An error message will pop up if you enter incorrect login information, or if you try to create an account with a username that already exists.
5. The GUI has not yet been fully implemented, so no functionality is visible in the GUI after loggin in. However, the Server class will print in the terminal which button has been chosen.