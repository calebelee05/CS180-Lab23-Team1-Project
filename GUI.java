
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.util.*;

/**
 * This is the GUI class.
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 2
 *
 * @author Team 1 Lab 23
 * @version April 17, 2025
 */
public class GUI extends JComponent implements Runnable, Communicator, GuiInterface {

    private Client client;
    private final String host = "localhost";
    private final int port = 8888;

    private JFrame frame;
    private Container content;
    private JPanel loginPanel;
    private JButton login;
    private JButton createAccount;
    private JButton loginToAccount;

    JTextField username;
    JTextField password;

    JTextField createUser;
    JTextField createPass;
    JButton createAcc;
    JPanel userAndPass;

    JButton itemListing;
    JButton itemSearch;
    JButton messages;
    JButton deleteAccount;
    JButton logout;
    JButton displayBalance;
    JButton mainMenu;
    JPanel userAccount;

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // Log in option
            if (e.getActionCommand().equals(LOG_IN)) {
                allowLogin();
            }
            // Sign up option
            if (e.getActionCommand().equals(SIGN_UP)) {
                create();
            }
            // Log in button
            if (e.getActionCommand().equals(LOGGED_IN)) {
                // Get inputs
                String usernameInput = username.getText();
                String passwordInput = password.getText();

                // Validate inputs
                if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both a username and password.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Send request and process response
                try {
                    Object response = client.sendRequest(LOG_IN, usernameInput, passwordInput);
                    if (response instanceof String) {
                        String message = (String) response;
                        if (message.equals(ERROR_MESSAGE)) {
                            JOptionPane.showMessageDialog(null, "Username or password is incorrect.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (message.equals(LOGGED_IN)) {
                            loggedIn();
                            JOptionPane.showMessageDialog(null, "Logged in successfully!",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (IOException ioe) {
                    System.out.println("Error sending request to server.");
                }
            }
            // Create account button
            if (e.getActionCommand().equals(ACCOUNT_CREATED)) {
                // Get inputs
                String usernameInput = createUser.getText();
                String passwordInput = createPass.getText();

                // Validate inputs
                if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both a username and password.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Send request and process response
                try {
                    Object response = client.sendRequest(SIGN_UP, usernameInput, passwordInput);
                    if (response instanceof String) {
                        String message = (String) response;
                        if (message.equals(ERROR_MESSAGE)) {
                            JOptionPane.showMessageDialog(null, "Username already exists.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (message.equals(LOGGED_IN)) {
                            loggedIn();
                            JOptionPane.showMessageDialog(null, "Account created successfully!",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (IOException ioe) {
                    System.out.println("Error sending request to server.");
                }
            }
            // Back to menu
            if (e.getSource() == mainMenu) {
                mainMenuScreen();
            }

            // Item Listing
            if (e.getActionCommand().equals(ITEM_LISTING)) {
                try {
                    Object response = client.sendRequest(ITEM_LISTING);
                    if (response instanceof List) {
                        List<ItemInterface> itemList = (ArrayList<ItemInterface>) response;
                        // use itemList
                        myItemListing(itemList);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Failed to retrieve item list.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ioe) {
                    System.out.println("Error sending request to server.");
                }
            }
            // Add Item
            if (e.getActionCommand().equals(ADD_ITEM)) {
                addItem();
            }

            // Item Search
            if (e.getActionCommand().equals(ITEM_SEARCH)) {
                searchItem(); // search by name/description, price range, and seller username
            }
            // Search button
            if (e.getActionCommand().equals(SEARCH)) {
                // Get inputs
                String textQuery = "textQuery"; // Searches in Item.name and Item.description
                String lowPriceQuery = "0.0"; // Low-bound for price range
                String highPriceQuery = "10.0"; // High-bound for price range
                String sellerQuery = "sellerQuery"; // Searches in Item.sellerID

                // Validate inputs
                try {
                    double lowBound = Double.parseDouble(lowPriceQuery);
                    double highBound = Double.parseDouble(highPriceQuery);
                    if (lowBound < 0 || highBound < 0) {
                        JOptionPane.showMessageDialog(null,
                                "Price range must be non-negative.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    } else if (lowBound > highBound) {
                        JOptionPane.showMessageDialog(null,
                                "Price range is not valid.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null,
                            "Please enter valid numbers for price range.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Send request and process response
                try {
                    Object response = client.sendRequest(ITEM_SEARCH,
                            textQuery,
                            lowPriceQuery,
                            highPriceQuery,
                            sellerQuery);
                    if (response instanceof List) {
                        List<ItemInterface> itemList = (ArrayList<ItemInterface>) response;
                        // use filtered itemList (display search result)
                        searchResult(itemList);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Failed to retrieve item list.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ioe) {
                    System.out.println("Error sending request to server.");
                }
            }
            
            // Message Listing
            if (e.getActionCommand().equals(MESSAGES)) {
                try {
                    Object response = client.sendRequest(MESSAGES);
                    if (response instanceof List) {
                        List<MessageInterface> messageList = (ArrayList<MessageInterface>) response;
                        // use messageList
                        messageListing(messageList);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Failed to retrieve message list.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ioe) {
                    System.out.println("Error sending request to server.");
                }
            }

            // Delete account
            if (e.getActionCommand().equals(DELETE_ACCOUNT)) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete your account?",
                        "Delete account",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == 0) {
                    try {
                        Object response = client.sendRequest(DELETE_ACCOUNT);
                        if (response instanceof String) {
                            String message = (String) response;
                            if (message.equals(SUCCESS_MESSAGE)) {
                                JOptionPane.showMessageDialog(null,
                                        "Account deleted successfully.",
                                        "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                                initial();
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Failed to delete account.",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } catch (IOException ioe) {
                        System.out.println("Error sending request to server.");
                    }
                }
            }

            // Logout
            if (e.getActionCommand().equals(LOG_OUT)) {
                try {
                    Object response = client.sendRequest(LOG_OUT);
                    if (response instanceof String) {
                        String message = (String) response;
                        if (message.equals(SUCCESS_MESSAGE)) {
                            JOptionPane.showMessageDialog(null,
                                    "Log-out successful.",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            initial();
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Failed to log-out.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                } catch (IOException ioe) {
                    System.out.println("Error sending request to server.");
                }
            }

            // Display Balance
            if (e.getActionCommand().equals(DISPLAY_BALANCE)) {
                try {
                    Object response = client.sendRequest(DELETE_ACCOUNT);
                    if (response instanceof String) {
                        String balanceString = (String) response;
                        double balance = Double.parseDouble(balanceString);
                        // use balance
                        showBalance();
                    }
                } catch (IOException ioe) {
                    System.out.println("Error sending request to server.");
                }
            }
        }
    };

    public Client beginConnection() {
        try {
            System.out.println("Connected to server!");
            return (new Client(host, port));
        } catch (IOException ioe) {
            System.out.println("Connection failed!");
            return null;
        }
    }

    public void allowLogin() {
        username = new JTextField(50);
        JLabel usernameLabel = new JLabel("Enter Username:");
        password = new JTextField(50);
        JLabel passwordLabel = new JLabel("Enter Password:");
        userAndPass = new JPanel();
        userAndPass.setLayout(new BoxLayout(userAndPass, BoxLayout.Y_AXIS));
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginToAccount = new JButton("Login");
        loginToAccount.addActionListener(actionListener);
        loginToAccount.setActionCommand(LOGGED_IN);
        loginToAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
        userAndPass.add(usernameLabel);
        userAndPass.add(username);
        userAndPass.add(passwordLabel);
        userAndPass.add(password);
        userAndPass.add(loginToAccount);
        content.remove(loginPanel);
        content.add(userAndPass, BorderLayout.CENTER);
        content.revalidate();
    }

    public void create() {
        createUser = new JTextField(50);
        JLabel usernameLabel = new JLabel("Enter Username:");
        createPass = new JTextField(50);
        JLabel passwordLabel = new JLabel("Enter Password:");
        userAndPass = new JPanel();
        userAndPass.setLayout(new BoxLayout(userAndPass, BoxLayout.Y_AXIS));
        createUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        createPass.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAcc = new JButton("Create Account");
        createAcc.addActionListener(actionListener);
        createAcc.setActionCommand(ACCOUNT_CREATED);
        createAcc.setAlignmentX(Component.CENTER_ALIGNMENT);
        userAndPass.add(usernameLabel);
        userAndPass.add(createUser);
        userAndPass.add(passwordLabel);
        userAndPass.add(createPass);
        userAndPass.add(createAcc);
        content.remove(loginPanel);
        content.add(userAndPass, BorderLayout.CENTER);
        content.revalidate();
    }

    public void loggedIn() {
        itemListing = new JButton("View Item List");
        itemListing.addActionListener(actionListener);
        itemListing.setActionCommand(ITEM_LISTING);

        itemSearch = new JButton("Search Item");
        itemSearch.addActionListener(actionListener);
        itemSearch.setActionCommand(ITEM_SEARCH);

        messages = new JButton("View Messages");
        messages.addActionListener(actionListener);
        messages.setActionCommand(MESSAGES);

        deleteAccount = new JButton("Delete Account");
        deleteAccount.addActionListener(actionListener);
        deleteAccount.setActionCommand(DELETE_ACCOUNT);

        logout = new JButton("Logout");
        logout.addActionListener(actionListener);
        logout.setActionCommand(LOG_OUT);

        displayBalance = new JButton("View Balance");
        displayBalance.addActionListener(actionListener);
        displayBalance.setActionCommand(DISPLAY_BALANCE);

        userAccount = new JPanel();
        userAccount.setLayout(new FlowLayout());
        userAccount.add(itemListing);
        userAccount.add(itemSearch);
        userAccount.add(messages);
        userAccount.add(deleteAccount);
        userAccount.add(logout);
        userAccount.add(displayBalance);
        content.remove(userAndPass);
        content.add(userAccount, BorderLayout.CENTER);
        content.revalidate();
    }

    // Initial sign in screen
    public void initial() {

    }

    // Main menu
    public void mainMenuScreen() {

    }

    // Item listing screen
    public void myItemListing(List<ItemInterface> itemList) {

    }

    // Add item screen
    public void addItem() {

    }

    // View My Item
    public void viewMyItem(ItemInterface item) {

    }

    // Item searching screen
    public void searchItem() {

    }

    // Item search result
    public void searchResult(List<ItemInterface> itemList) {

    }

    // View Item
    public void viewItem(ItemInterface item) {

    }

    // Message lists
    public void messageListing(List<MessageInterface> messageList) {

    }

    // Write new message
    public void newMessage(UserInterface recipient) {

    }

    // Show balance
    public void showBalance() {

    }

    public void run() {
        client = beginConnection();

        frame = new JFrame("GUI");
        content = frame.getContentPane();
        content.setLayout(new BorderLayout());
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        login = new JButton("Login");
        login.addActionListener(actionListener);
        login.setActionCommand(LOG_IN);
        createAccount = new JButton("Create Account");
        createAccount.addActionListener(actionListener);
        createAccount.setActionCommand(SIGN_UP);

        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginPanel.add(login);
        loginPanel.add(createAccount);
        content.add(loginPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GUI());
    }
}
