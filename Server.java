
import java.io.*;
import java.net.*;
import java.util.List;

/**
 * A Server class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 2
 *
 * @author Team 1 Lab 23
 * @version April 29, 2025
 */
public class Server implements Runnable, Communicator, ServerInterface {

    private static final DatabaseInterface DATABASE = new Database(User.FILEPATH, Item.FILEPATH, Message.FILEPATH);
    private Socket socket;
    private UserInterface user;

    public Server(Socket socket) {
        this.socket = socket;
    }

    // Used for testing
    public Socket getSocket() {
        return socket;
    }

    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            oos.flush();
            while (true) {
                // Log in/Sign up
                String signInOption;
                String username;
                String password;
                boolean logout = false;
                while (true) {
                    signInOption = reader.readLine();
                    if (signInOption.equals(LOG_IN)) {
                        username = reader.readLine();
                        password = reader.readLine();
                        try {
                            user = Database.logIn(username, password);
                            oos.writeObject(LOGGED_IN);
                            break;
                        } catch (UserNotFoundException e) {
                            oos.writeObject(ERROR_MESSAGE);
                        }
                    } else if (signInOption.equals(SIGN_UP)) {
                        username = reader.readLine();
                        password = reader.readLine();
                        if (Database.userExists(username)) {
                            oos.writeObject(ERROR_MESSAGE);
                        } else {
                            user = DATABASE.createAccount(username, password);
                            oos.writeObject(LOGGED_IN);
                            break;
                        }
                    }
                }
                // Main menu
                while (true) {
                    String mainMenuOption = reader.readLine();
                    System.out.println(mainMenuOption);
                    if (mainMenuOption.equals(ITEM_LISTING)) {
                        oos.writeObject(Database.getUserItems(user)); // type of List<ItemInterface>
                        while (true) {
                            String itemListingOption = reader.readLine();
                            if (itemListingOption.equals(ITEM_CREATED)) {
                                try {
                                    String itemName = reader.readLine();
                                    double itemPrice = Double.parseDouble(reader.readLine());
                                    String itemDescription = reader.readLine();
                                    DATABASE.addItem(user, itemName, itemPrice, itemDescription);
                                    oos.writeObject(SUCCESS_MESSAGE);
                                } catch (Exception e) {
                                    oos.writeObject(ERROR_MESSAGE);
                                }
                            // read item and delete corresponding item
                            } else if (itemListingOption.equals(DELETE_ITEM)) {
                                boolean success = false;
                                try {
                                    String itemName = reader.readLine();
                                    for (ItemInterface item : Database.getUserItems(user)) {
                                        if (item.getName().equals(itemName)) {
                                            DATABASE.deleteItem(user, item);
                                            success = true;
                                            break;
                                        }
                                    }
                                    if (success) {
                                        oos.writeObject(SUCCESS_MESSAGE);
                                    } else {
                                        oos.writeObject(ERROR_MESSAGE);
                                    }
                                } catch (Exception e) {
                                    oos.writeObject(ERROR_MESSAGE);
                                }
                                
                            } else if (itemListingOption.equals(MAIN_MENU)) {
                                oos.writeObject(SUCCESS_MESSAGE);
                                break;
                            } else if (itemListingOption.equals(ITEM_LISTING)) {
                                oos.writeObject(Database.getUserItems(user));
                            }
                        }
                    } else if (mainMenuOption.equals(ITEM_SEARCH)) {
                        oos.writeObject(SUCCESS_MESSAGE);
                        while (true) {
                            String itemSearchOption = reader.readLine();
                            System.out.println(itemSearchOption);
                            if (itemSearchOption.equals(SEARCH)) {
                                String textQuery = reader.readLine();
                                double lowPriceQuery = Double.parseDouble(reader.readLine()); // GUI already verifies double
                                double highPriceQuery = Double.parseDouble(reader.readLine()); // GUI already verifies double
                                String sellerQuery = reader.readLine();
                                List<ItemInterface> filteredItems = Database.searchItemList(textQuery,
                                        lowPriceQuery,
                                        highPriceQuery,
                                        sellerQuery);
                                oos.writeObject(filteredItems);
                            } else if (itemSearchOption.equals(MAIN_MENU)) {
                                oos.writeObject(SUCCESS_MESSAGE);
                                break;
                            } else if (itemSearchOption.equals(ITEM_SEARCH)) {
                                oos.writeObject(SUCCESS_MESSAGE);
                            } else if (itemSearchOption.equals(BUY)) {
                                String itemName = reader.readLine();
                                String sellerName = reader.readLine();
                                String paymentPW = reader.readLine();
                                if (!paymentPW.equals(user.getPassword())) {
                                    oos.writeObject(WRONG_PW);
                                } else {
                                    try {
                                        ItemInterface item = Database.findItem(itemName, sellerName);
                                        UserInterface seller = Database.findUser(sellerName);
                                        DATABASE.transaction(user, seller, item);
                                        oos.writeObject(SUCCESS_MESSAGE);
                                    } catch (Exception e) {
                                        oos.writeObject(ERROR_MESSAGE);
                                    }
                                }
                            } else if (itemSearchOption.equals(SEND_MESSAGE)) {
                                try {
                                    String recipientID = reader.readLine();
                                    String messageContent = reader.readLine();
                                    String messageInput;
                                    while (true) {
                                        messageInput = reader.readLine();
                                        if (messageInput.equals(END_MESSAGE)) {
                                            break;
                                        }
                                        messageContent = messageContent + "\n" + messageInput;
                                    }
                                    DATABASE.sendMessage(user, Database.findUser(recipientID), messageContent);
                                    oos.writeObject(SUCCESS_MESSAGE);
                                    break;
                                } catch (UserNotFoundException e) {
                                    oos.writeObject(ERROR_MESSAGE);
                                }
                            }
                        }
                    } else if (mainMenuOption.equals(MESSAGE_RECEIVED)) {
                        oos.writeObject(Database.getReceivedMessages(user));
                    } else if (mainMenuOption.equals(MESSAGE_SENT)) {
                        oos.writeObject(Database.getSentMessages(user));
                    } else if (mainMenuOption.equals(SEND_MESSAGE)) {
                        try {
                            String recipientID = reader.readLine();
                            String messageContent = reader.readLine();
                            String messageInput;
                            while (true) {
                                messageInput = reader.readLine();
                                if (messageInput.equals(END_MESSAGE)) {
                                    break;
                                }
                                messageContent = messageContent + "\n" + messageInput;
                            }
                            DATABASE.sendMessage(user, Database.findUser(recipientID), messageContent);
                            oos.writeObject(SUCCESS_MESSAGE);
                        } catch (UserNotFoundException e) {
                            oos.writeObject(ERROR_MESSAGE);
                        }
                    } else if (mainMenuOption.equals(LOG_OUT)) {
                        logout = true;
                        oos.writeObject(SUCCESS_MESSAGE);
                        break;
                    } else if (mainMenuOption.equals(DELETE_ACCOUNT)) {
                        DATABASE.deleteAccount(user);
                        logout = true;
                        oos.writeObject(SUCCESS_MESSAGE);
                        break;
                    } else if (mainMenuOption.equals(ACCOUNT_INFO)) {
                        oos.writeObject(user.toString());
                    } else if (mainMenuOption.equals(MAIN_MENU)) {
                        oos.writeObject(SUCCESS_MESSAGE);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Client Disconnected");
        }
    }

    public static void main(String[] args) {
        DATABASE.read();

        try (ServerSocket ss = new ServerSocket(PORT);) {
            System.out.println("Server started on port " + PORT);
            while (true) {
                Socket s = ss.accept();
                Thread t = new Thread(new Server(s));
                t.start();
                System.out.println("Client connected: " + s.getInetAddress());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
