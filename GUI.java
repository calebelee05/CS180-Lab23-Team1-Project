
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
    JPanel userAccount;

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == login) {
                allowLogin();
            }
            if (e.getSource() == createAccount) {
                create();
            }
            if (e.getSource() == loginToAccount) {
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
                    if (response instanceof String message) {
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
            if (e.getSource() == createAcc) {
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
                    if (response instanceof String message) {
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
            if (e.getSource() == itemListing) {
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
            if (e.getSource() == itemSearch) { // search by item name/description, price range, and seller username
                searchItem();
                // Get inputs
                String textQuery = "textQuery"; // Searches in Item.name and Item.description // Replace with actual input
                String lowPriceQuery = "0.0"; // Low-bound for price range // Replace with input
                String highPriceQuery = "10.0"; // High-bound for price range // Replace with input
                String sellerQuery = "sellerQuery"; // Searches in Item.sellerID // Replace with actual input

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
                        // use filtered itemList
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
            if (e.getSource() == messages) {
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
            if (e.getSource() == deleteAccount) {
                int confirm = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to delete your account?",
                        "Delete account",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == 0) {
                    try {
                        Object response = client.sendRequest(DELETE_ACCOUNT);
                        if (response instanceof String message) {
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
            if (e.getSource() == logout) {
                try {
                    Object response = client.sendRequest(LOG_OUT);
                    if (response instanceof String message) {
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
            if (e.getSource() == displayBalance) {
                try {
                    Object response = client.sendRequest(DELETE_ACCOUNT);
                    if (response instanceof String balanceString) {
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

    // Phase 3 TODO: implement GUI layout
    public void initial() {

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
        itemSearch = new JButton("Search Item");
        itemSearch.addActionListener(actionListener);
        messages = new JButton("View Messages");
        messages.addActionListener(actionListener);
        deleteAccount = new JButton("Delete Account");
        deleteAccount.addActionListener(actionListener);
        logout = new JButton("Logout");
        logout.addActionListener(actionListener);
        displayBalance = new JButton("View Balance");
        displayBalance.addActionListener(actionListener);

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

    public void myItemListing(List<ItemInterface> itemList) {

    }

    public void addItem() {

    }

    public void searchItem() {

    }

    public void searchResult(List<ItemInterface> itemList) {

    }

    public void messageListing(List<MessageInterface> messageList) {

    }

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
        createAccount = new JButton("Create Account");
        createAccount.addActionListener(actionListener);

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
