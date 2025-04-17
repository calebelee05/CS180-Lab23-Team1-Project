import java.net.*;
import java.io.*;

/**
 * A Server class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Team 1 Lab 23
 * @version April 16, 2025
 */
public class Server implements Runnable {
    private static final DatabaseInterface DATABASE = new Database(User.FILEPATH, Item.FILEPATH, Message.FILEPATH);
    private Socket socket;
    private UserInterface user;

    // Message strings to communicate with client
    // Login screen
    public static final String LOG_IN = "LogIn";
    public static final String SIGN_UP = "SignUp";
    public static final String LOGGED_IN = "LoggedIn";
    public static final String ERROR = "Error";
    public static final String ACCOUNT_CREATED = "AccountCreated";

    // main menu
    public static final String ITEM_LISTING = "ItemListing";
    public static final String ITEM_SEARCH = "ItemSearch";
    public static final String MESSAGES = "Messages";
    public static final String LOG_OUT = "LogOut";
    public static final String DELETE_ACCOUNT = "DeleteAccount";


    public Server(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            oos.flush();
            while (true) {
                // Login screen
                String signInOption = reader.readLine();
                String username;
                String password;
                boolean logout = false;
                if (signInOption.equals(LOG_IN)) {
                    while (true) {
                        username = reader.readLine();
                        password = reader.readLine();
                        try {
                            user = Database.logIn(username, password);
                            oos.writeObject(LOGGED_IN);
                            break;
                        } catch (UserNotFoundException e) {
                            oos.writeObject(ERROR);
                        }
                    }
                } else if (signInOption.equals(SIGN_UP)) {
                    while (true) { 
                        username = reader.readLine();
                        password = reader.readLine();
                        if (Database.userExists(username)) {
                            oos.writeObject(ERROR);
                        } else {
                            user = DATABASE.createAccount(username, password);
                            oos.writeObject(ACCOUNT_CREATED);
                            break;
                        }
                    }
                }
                // Home screen
                while (true) { 
                    String mainMenuOption = reader.readLine();
                    if (mainMenuOption.equals(ITEM_LISTING)) {

                    } else if (mainMenuOption.equals(ITEM_SEARCH)) {

                    } else if (mainMenuOption.equals(MESSAGES)) {

                    } else if (mainMenuOption.equals(LOG_OUT)) {
                        logout = true;
                        break;
                    } else if (mainMenuOption.equals(DELETE_ACCOUNT)) {
                        DATABASE.deleteAccount(user);
                        logout = true;
                        break;
                    }
                }
                if (logout) {
                    continue;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
