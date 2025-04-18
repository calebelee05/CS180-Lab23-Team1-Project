import java.io.*;
import java.net.*;

/**
 * A Server class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 2
 *
 * @author Team 1 Lab 23
 * @version April 16, 2025
 */
public class Server implements Runnable, Communicator {
    private static final DatabaseInterface DATABASE = new Database(User.FILEPATH, Item.FILEPATH, Message.FILEPATH);
    private Socket socket;
    private UserInterface user;

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

    public static void main(String[] args) {
        int port = 8888;
        DATABASE.readUser();
        DATABASE.readItem();
        DATABASE.readMessage();

        System.out.println("Server started on port " + port);
        try {
            ServerSocket ss = new ServerSocket(port);
            Socket s = ss.accept();
            Thread t = new Thread(new Server(s));
            t.start();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
