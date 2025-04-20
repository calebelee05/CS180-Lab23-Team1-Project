
import java.io.*;
import java.net.*;
import java.util.List;

/**
 * A Server class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 2
 *
 * @author Team 1 Lab 23
 * @version April 16, 2025
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
                            oos.writeObject(ERROR_MESSAGE);
                        }
                    }
                } else if (signInOption.equals(SIGN_UP)) {
                    while (true) {
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
                // Home screen
                while (true) {
                    String mainMenuOption = reader.readLine();
                    System.out.println(mainMenuOption);
                    if (mainMenuOption.equals(ITEM_LISTING)) {
                        oos.writeObject(Database.getItemList()); // type of List<ItemInterface>
                    } else if (mainMenuOption.equals(ITEM_SEARCH)) {
                        String textQuery = reader.readLine();
                        double lowPriceQuery = Double.parseDouble(reader.readLine()); // GUI already verifies double
                        double highPriceQuery = Double.parseDouble(reader.readLine()); // GUI already verifies double
                        String sellerQuery = reader.readLine();
                        List<ItemInterface> filteredItems = Database.searchItemList(textQuery,
                                lowPriceQuery,
                                highPriceQuery,
                                sellerQuery);
                        oos.writeObject(filteredItems);
                    } else if (mainMenuOption.equals(MESSAGES)) {
                        oos.writeObject(Database.getMessageList());
                    } else if (mainMenuOption.equals(LOG_OUT)) {
                        logout = true;
                        break;
                    } else if (mainMenuOption.equals(DELETE_ACCOUNT)) {
                        DATABASE.deleteAccount(user);
                        logout = true;
                        oos.writeObject(SUCCESS_MESSAGE);
                        break;
                    } else if (mainMenuOption.equals(DISPLAY_BALANCE)) {
                        oos.writeObject(user.getBalance());
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
        DATABASE.read();

        try {
            ServerSocket ss = new ServerSocket(port);
            System.out.println("Server started on port " + port);
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
