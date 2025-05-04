# CS180-Team-Project
Spring 25' CS180 Lab section 23 Team 1 Project repository

Topic: A Market Place

## Instructions on How to Run
1. Compile Server.java and GUI.java classes with `javac Server.java` and `javac GUI.java`.
2. Initialize the server first with `java Server`, then run the client side with `java GUI`; this will start the GUI.
3. When the GUI pops up, you can choose to either log in or create a new account.
4. After you log in, you can navigate to whatever option you want: View Item List, Search Item, View Messages, Delete Account, Logout, or Account Info.
5. To quit, you can close the program tab.

Phase 1: Building the database, interfaces and test cases
This phase covers the creation of the database for the market place. This includes creating and deleting the user and retailer accounts. It also involves adding, deleting and searching for items. 

## Message.java
The Message class forms the structure of how messages exchanged between users are represented in the database.

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
    - String getSenderID();
    - void setSenderID(String senderID);
    - String getRecipientID();
    - void setRecipientID(String recipientID);
    - String getContents();
    - void setContents(String contents);
    - ZonedDateTime getTimestamp();
    - void setTimeStamp(ZonedDateTime timestamp);
    - static ArrayList<Message> getList();
    - synchronized void writeObject(ArrayList<Message> list);
    - synchronized ArrayList<Message> readObject();

    Extends Serializable interface: Serializes instances of MessageInterface.
    
## User.java
The User class forms the structure of how user accounts are represented in the database.

    Fields
    - private String username: The username of the user.
    - private String password: The password of the user.
    - private double balance: The current balance of the user.
    - private List<ItemInterface> itemsList: An synchronized to store the items listed by the user.
    - private List<MessageInterface> messagesSent: A synchronized List to store the messages sent by the user.
    - private List<MessageInterface> messagesReceived: A synchronized List to store the messages received by the user.
    - private static final String FILEPATH: A constant String representing the file path for storing User objects ("UserData.txt").

    Constructors
    - public User(String username, String password, double balance): Initializes a new User object with the provided username, password, and balance. Creates a new file if it doesn't exist and adds the newly instantiated User object to the arraylist.

    Methods
    - public String getUsername(): Returns the username of the user.
    - public String getPassword(): Returns the password of the user.
    - public double getBalance(): Returns the current balance of the user.
    - public List<ItemInterface> getItems(): Returns the list of items listed by the user.
    - public List<MessageInterface> getMessagesSent(): Returns the list of messages sent by the user.
    - public List<MessageInterface> getMessagesReceived(): Returns the list of messages received by the user.
    - public void setUsername(String username): Sets the username of the user.
    - public void setPassword(String password): Sets the password of the user.
    - public void setBalance(double balance): Sets the balance of the user.
    - public void setItems(List<ItemInterface> itemsList): Sets the list of items listed by the user.
    - public void setMessagesSent(List<MessageInterface> messagesSent): Sets the list of messages sent by the user.
    - public void setMessagesReceived(List<MessageInterface> messagesReceived): Sets the list of messages received by the user.
    - public void deleteUser(): Removes the user from the `userList` of the Database and deletes all items listed by the user.
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
    - public void deleteMessageSent(MessageInterface message): Deletes the message from messagesSent.
    - public void deleteMessageReceived(MessageInterface message): Deletes the message from messagesReceived.
    - public String toString(): Returns a String representation of the User object.
    - public boolean equals(Object object): Checks if this user is equal to another user based on the username.

    Interfaces Implemented
    - UserInterface: Defines the methods implemented by the User class.

## UserInterface.java
    Methods
    - String getUsername();
    - String getPassword();
    - double getBalance();
    - List<ItemInterface> getItems();
    - List<MessageInterface> getMessagesSent();
    - List<MessageInterface> getMessagesReceived();
    - void setUsername(String username);
    - void setPassword(String password);
    - void setBalance(double balance);
    - void setItems(List<ItemInterface> itemsList);
    - void setMessagesSent(List<MessageInterface> messagesSent);
    - void setMessagesReceived(List<MessageInterface> messagesReceived);
    - void deleteUser();
    - void addItem(String name, double price, String description);
    - ItemInterface getItem(String name) throws ItemNotFoundException;
    - void deleteItem(ItemInterface item);
    - void setItem(ItemInterface item, String name, double price, String description);
    - void buyItem(ItemInterface item);
    - void sellItem(ItemInterface item);
    - void sendMessage(UserInterface recipient, String content);
    - void receiveMessage(MessageInterface message);
    - MessageInterface getMessageFromUser(String senderID) throws UserNotFoundException;
    - MessageInterface getMessageToUser(String recipient) throws UserNotFoundException;
    - void deleteMessageSent(MessageInterface message);
    - void deleteMessageReceived(MessageInterface message);

    Extends Serializable interface: Serializes instances of UserInterface.

## Item.java
The Item class forms the structure of how items are represented in the database.

    Fields
    - private String name: The name of the item.
    - private double price: The price of the item.
    - private String description: The description of the item.
    - private String sellerID: The ID of the seller of the item.
    - private String buyerID: The ID of the user that bought the item.
    - private String imageString: the String representation of the image data added to the item.
    - private static final String FILEPATH: A constant String representing the file path for storing Item objects (ItemData.txt).

    Constructors
    - public Item(String name, double price, String description, String sellerID, String imageString): Initializes a new Item object with the provided name, price, description, and seller ID. It also creates a new file if it doesn't exist and adds the newly instantiated Item object to the `itemList`.

    Methods
    - public String getName(): Returns the name of the item.
    - public double getPrice(): Returns the price of the item.
    - public String getDescription(): Returns the description of the item.
    - public String getSellerID(): Returns the seller ID of the item.
    - public String getBuyerID(): Returns the buyer ID of the item.
    public String getImageString(): Returns the image String of the item.
    - public void setSellerID(String sellerID): Sets the seller ID of the item.
    - public void setBuyerID(String buyerID): Sets the buyer ID of the item.
    - public void setPrice(double price): Sets the price of the item.
    - public void setDescription(String description): Sets the description of the item.
    - public void setName(String itemName): Sets the name of the item.
    - public void setImageString(String imageString): Sets the image String of the item
    - public boolean equals(ItemInterface item): Checks if this item is equal to another item based on the seller ID and name.
    - public String toString(): Returns a String representation of the Item object.

    Interfaces Implemented
    - ItemInterface: Defines the methods implemented by the Item class.

## ItemInterface.java
    Methods
    - String getName();
    - double getPrice();
    - String getDescription();
    - String getSellerID();
    - String getBuyerID();
    - String getImageString();
    - void setSellerID(String sellerID);
    - void setBuyerID(String buyerID);
    - void setPrice(double price);
    - void setDescription(String description);
    - void setName(String itemName);
    - void setImageString(String imageString);

    Interfaces Implemented
    - Serializable: Serializes instances of ItemInterface.

## Database.java
The Database class handles all operations on User, Item, and Message objects, and stores the data into text files.

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
    - public static ItemInterface findItem(String itemName, String sellerName): Returns the item with the name "itemName" that is on sale by the user with the name "sellerName".
    - public static UserInterface findUser(String username): Returns the user with the name "username".
    - public static UserInterface logIn(String username, String password) throws UserNotFoundException: Searches the `userList` for a user with the matching username and password. If found, returns the `UserInterface` object, otherwise, throws a `UserNotFoundException`.
    - public static boolean userExists(String username): Checks if a user with the given username exists in the `userList`.
    - public static List<ItemInterface> getUserItems(UserInterface user): Returns the item listing of the user with the given username.
    - public static List<MessageInterface> getReceivedMessages(UserInterface user): Returns the messages received by the given user.
    - public static List<MessageInterface> getSentMessages(UserInterface user): Returns the messages sent by the given user.
    - public ItemInterface addItem(UserInterface user, String itemName, double price, String description): Adds a new item for a specified user.
    - public void deleteItem(ItemInterface item): Removes an item from itmeList.
    - public MessageInterface sendMessage(UserInterface sender, UserInterface recipient, String content): Create a new message and adds it to the `messageList`. Returns the newly created `MessageInterface` object.
    - public void deleteMessage(UserInterface sender, UserInterface recipient, MessageInterface message): Deletes the message sent from sender to recipient.
    - public void transaction(UserInterface buyer, UserInterface seller, ItemInterface item): Calls the `buyItem()` method on the `buyer` and the `sellItem()` method on the `seller` to conduct a transaction.
    
    Interfaces Implemented
    - DatabaseInterface: Defines the methods implemented by the `Database` class.


## DatabaseInterface.java
    Methods
    - String getUserFile();
    - String getItemFile();
    - String getMessageFile();
    - void setUserFile(String userFile);
    - void setItemFile(String itemFile);
    - void setmessageFile(String messageFile);
    - void writeUser();
    - void writeItem();
    - void writeMessage();
    - void readUser();
    - void readItem();
    - void readMessage();
    - void write();
    - void read();
    - synchronized void update();
    - UserInterface createAccount(String username, String password);
    - void deleteAccount(UserInterface user);
    - ItemInterface addItem(UserInterface user, String itemName, double price, String description);
    - void deleteItem(ItemInterface item);
    - MessageInterface sendMessage(UserInterface sender, UserInterface recipient, String content);
    - void deleteMessage(UserInterface sender, UserInterface recipient, MessageInterface message);
    - void transaction(UserInterface buyer, UserInterface seller, ItemInterface item);


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
    - TestDatabase.java: Tests the functionality of the combined Database class.

Phase 2: Building the Network I/O
This phase covers the creation of the client-server connectivity and implements Network I/O.

## Client.java
The Client class directly communicates with the server through network I/O to receive the data from and send data to the database.

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
The server class directly interacts with the database and handles users that connect to the server with Threads.

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
Defines the methods that the `Server` class implements; 'Server' class has no public non-static methods that are not inherited from other interfaces, so this interface is intentionally left empty.

## Communicator.java
The Communicator interface stores message strings used for communication between Server and GUI.

    Fields
    - String HOST: ip address of the server ("localhost")
    - int PORT = 8888: port number of the server (8888)
    - String ERROR_MESSAGE: A constant `String` representing an error message for communication between the client and server.
    - String SUCCESS_MESSAGE: A constant `String` indicating a successful state.
    - String LOG_IN: A constant `String` for the client to request the login screen.
    - String SIGN_UP: A constant `String` for the client to request the signup screen.
    - String LOGGED_IN: A constant `String` sent by the server to indicate a successful login.
    - String ACCOUNT_CREATED: A constant `String` sent by the server to confirm successful account creation.
    - String ITEM_LISTING: A constant `String` for the client to request a list of items.
    - String ITEM_SEARCH: A constant `String` for the client to initiate a search for items.
    - String MESSAGE_LIST: A constant `String` used as action command for the "View Message" option in the GUI
    - String LOG_OUT: A constant `String` for the client to request to log out.
    - String DELETE_ACCOUNT: A constant `String` for the client to request the deletion of their account.
    - String ACCOUNT_INFO: A constant `String` for the client to request displaying their account information.
    - String MAIN_MENU: A constant 'String' for the client to request displaying the main menu screen.
    - String ADD_ITEM: A constant 'String' for the client to request opening the "Add new item" screen.
    - String ITEM_CREATED: A constant 'String' for the client to confirm item is successfully added to listing.
    - String DELETE_ITEM: A constant 'String' for the client to request item deletion.
    - String CANCEL: A constant 'String' for the client to request cancelling a process.
    - String SEARCH: A constant 'String' for the client to request the search result.
    - String BUY: A constant 'String' for the client to request item purchase.
    - String WRONG_PW: A constant 'String' for the server to notify the client that the password is incorrect.
    - String SEND_MESSAGE: A constant 'String' for the client to request sending message.
    - String MESSAGE_SENT: A constant 'String' for the client to request the list of messages that the client sent.
    - String MESSAGE_RECEIVED: A constant 'String' for the client to request the list of messages that the client received.
    - String END_MESSAGE: A constant 'String' that indicates the end of a message


## Test Case Files
    - TestClient.java: Tests the functionality of the Client class.
    - TestServer.java: Tests the functionality of the Server class.

Phase 3: Building the GUI
Designing and building the graphical user interface for the marketplace.

## GUI.java
The GUI class starts the main program, displaying the GUI to the user and interacts with the server through the Client object.

    Fields
    - ActionListener actionListener: ActionListener for handling general actionevents on the GUI
    - ActionListener itemListActionListener: ActionListener for handling actionevents in item listing option on the GUI
    - ActionListener itemSearchActionListener: ActionListener for handling actionevents in item search option on the GUI
    - ActionListener messageActionListener: ActionListener for handling actionevents in message option on the GUI
    Methods
    - public Client beginConnection(): Creates new Client object that connects to the server.
    - public void initial(): Builds GUI for initial screen (before log in).
    - public void allowLogin(): Builds GUI for Log in screen.
    - public void create(): Builds GUI for Create Account screen.
    - public void mainMenuScreen(): Builds GUI for Main Menu screen.
    - public void itemListSetup(): Builds GUI for Item Listing screen.
    - public void myItemListing(List<ItemInterface> itemList): Adds items from given itemList onto the Item Listing GUI and displays the screen.
    - public void addItem(): Builds GUI for Add Item screen.
    - public void searchItem(): Builds GUI for Item Search screen.
    - public void searchResult(List<ItemInterface> itemList): Adds items found from the search in the given itemList onto the Item Search GUI and displays the screen.
    - public void viewItem(ItemInterface item): Builds GUI for Item Information screen of given item.
    - public void messageListSetup(): Builds GUI for View Message screen.
    - public void messageListing(List<MessageInterface> messageList): Adds messages from the given messageList onto the View Message GUI and displays the screen.
    - public void viewMessage(MessageInterface message): Builds GUI for viewing the given message.
    - public void message(): Builds GUI for Write New Message screen.
    - public void newMesage(String recipientID): Sets recipient of the message and displays the Write New Message screen.
    - public void showInfo(): Builds GUI for Account Info screen.
    - public ImageIcon resizeIcon(ImageIcon icon, int size): Resize the icon image by the given size.
    - public void setGlobalFont(Font font): Sets the global font of the GUI.
    - public void openFileChooser(): Creates and opens a JFileChooser pop-up for selecting item image.
    - public String imageIconToBlobString(ImageIcon icon): Converts image into Blob String representation.
    - public ImageIcon blobStringToImageIcon(String blobString): Converts Blob String to image.
    - private void performSearch(): Sends request to the server to search item according to the input query.
    - public void run(): Initilizes connection and GUI.
    - public static void main(String[] args): Main method to start the program; runs GUI on EDT.

## GUIInterface.java
    - Client beginConnection();
    - void initial();
    - void allowLogin();
    - void create();
    - void mainMenuScreen();
    - void itemListSetup();
    - void myItemListing(List<ItemInterface> itemList);
    - void addItem();
    - void searchItem();
    - void searchResult(List<ItemInterface> itemList);
    - void viewItem(ItemInterface item);
    - void messageListSetup();
    - void messageListing(List<MessageInterface> messageList);
    - void viewMessage(MessageInterface message);
    - void message();
    - void newMesage(String recipientID);
    - void showInfo();
    - ImageIcon resizeIcon(ImageIcon icon, int size);
    - void setGlobalFont(Font font);
    - void openFileChooser();
    - String imageIconToBlobString(ImageIcon icon);
    - ImageIcon blobStringToImageIcon(String blobString);

## Assignment Submission
- Anishka Rao - Submitted Report and Video Presentation on Brightspace
- Zaid Awartani - 
- Caleb Lee - Submitted Vocareum workspace
