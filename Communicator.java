
/**
 * A Communicator interface to store message strings used for communication
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 2
 *
 * @author Team 1 Lab 23
 * @version April 16, 2025
 */
public interface Communicator {
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
    String MESSAGES = "Messages";
    String LOG_OUT = "LogOut";
    String DELETE_ACCOUNT = "DeleteAccount";
    String DISPLAY_BALANCE = "DisplayBalance";

    String MAIN_MENU = "MainMenu";

    // Item listing
    String ADD_ITEM = "AddItem";
    String ADD = "Add";
    String DELETE = "Delete";
    String ITEM_CREATED = "ItemCreated";

    // Item search
    String SEARCH = "Search";

}
