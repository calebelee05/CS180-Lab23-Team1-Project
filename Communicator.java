
/**
 * A Communicator interface to store message strings used for communication
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 2
 *
 * @author Team 1 Lab 23
 * @version April 16, 2025
 */
public interface Communicator {
    // ip address and port
    String HOST = "localhost";
    int PORT = 8888; 

    // Status
    String ERROR_MESSAGE = "Error";
    String SUCCESS_MESSAGE = "Success";
    
    // Login screen
    String LOG_IN = "LogIn";
    String SIGN_UP = "SignUp";
    String LOGGED_IN = "LoggedIn";
    String ACCOUNT_CREATED = "AccountCreated";

    // Main menu
    String ITEM_LISTING = "ItemListing";
    String ITEM_SEARCH = "ItemSearch";
    String MESSAGE_LIST = "MessageList";
    String LOG_OUT = "LogOut";
    String DELETE_ACCOUNT = "DeleteAccount";
    String ACCOUNT_INFO = "AccountInfo";

    String MAIN_MENU = "MainMenu";

    // Item listing
    String ADD_ITEM = "AddItem";
    String ITEM_CREATED = "ItemCreated";
    String DELETE_ITEM = "DeleteItem";

    String CANCEL = "Cancel";

    // Item search
    String SEARCH = "Search";
    String BUY = "Buy";
    String WRONG_PW = "IncorrectPassword";

    // Message
    String MESSAGE = "Message";
    String SEND_MESSAGE = "SendMessage";
    String MESSAGE_SENT = "MessageSent";
    String MESSAGE_RECEIVED = "MessageReceived";
    String END_MESSAGE = "/EndOfMessage/";

}
