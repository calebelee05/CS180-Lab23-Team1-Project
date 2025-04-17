import java.net.*;
import java.io.*;

public class Server implements Runnable {
    private static final Database DATABASE = new Database(User.FILEPATH, Item.FILEPATH, Message.FILEPATH);
    private Socket socket;
    private UserInterface user;

    // Message strings to communicate with client
    public static final String LOG_IN = "LogIn";
    public static final String SIGN_UP = "SignUp";
    public static final String LOGGED_IN = "LoggedIn";
    public static final String ERROR = "Error";
    public static final String ACCOUNT_CREATED = "AccountCreated";


    public Server(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            PrintWriter pw = new PrintWriter(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                // Login screen
                String signInOption = reader.readLine();
                String username;
                String password;
                if (signInOption.equals(LOG_IN)) {
                    while (true) {
                        username = reader.readLine();
                        password = reader.readLine();
                        try {
                            user = Database.logIn(username, password);
                            pw.write(LOGGED_IN);
                            break;
                        } catch (UserNotFoundException e) {
                            pw.write(ERROR);
                        }
                    }
                } else if (signInOption.equals(SIGN_UP)) {
                    while (true) { 
                        username = reader.readLine();
                        password = reader.readLine();
                        if (Database.userExists(username)) {
                            pw.write(ERROR);
                        } else {
                            user = DATABASE.createAccount(username, password);
                            pw.write(ACCOUNT_CREATED);
                            break;
                        }
                    }
                }
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
