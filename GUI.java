
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;

/**
 * This is the GUI class.
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 3
 *
 * @author Team 1 Lab 23
 * @version April 29, 2025
 */
public class GUI extends JComponent implements Runnable, Communicator, GuiInterface {

    private Client client;

    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;

    // Initial
    private JPanel initialPanel;
    private JButton login;
    private JButton createAccount;

    // Log in
    private JPanel loginPanel;
    private JTextField username;
    private JTextField password;
    private JButton loginToAccount;

    // Sign up
    private JPanel signupPanel;
    private JTextField createUser;
    private JTextField createPass;
    private JButton createAcc;

    private JButton cancelSignIn; // Return to initial screen

    // Main Menu
    private JPanel menuPanel;
    private JButton itemSearch;
    private JButton messages;
    private JButton deleteAccount;
    private JButton logout;
    private JButton displayInfo;

    private JButton mainMenu; // Return to main menu

    // Item Listing
    private JPanel itemListPanel;
    private JPanel itemListSubPanel;
    private JPanel myItemInfoPanel;
    private JPanel itemAddPanel;
    private JButton itemListing;
    private JButton addItem;
    private JButton backToItems;
    private JButton deleteItem;
    private JButton createItem;
    private JButton cancelCreate;
    private JTextField itemName;
    private JTextField itemPrice;
    private JTextField itemDescription;
    private JTextArea myItemInfo;

    // Item Searching
    private JPanel itemSearchPanel;
    private JPanel searchResultSubpanel;
    private JPanel itemInfoPanel;
    private JPanel buyItemPanel;
    private JTextField itemNameQuery;
    private JTextField itemPriceLow;
    private JTextField itemPriceHigh;
    private JTextField itemSellerquery;
    private JTextField paymentPW;
    private JTextArea confirmItemInfo;
    private JTextArea itemInfo;
    private JButton search;
    private JButton buyItem;
    private JButton purchase;
    private JButton cancelPurchase;
    private JButton backToSearch;

    // Messaging
    private JPanel messagePanel;
    private JPanel messageListOptionPanel;
    private JPanel messageListPanel;
    private JPanel messageListSubPanel;
    private JPanel viewMessagePanel;
    private JTextField recipientName;
    private JTextArea messageContent;
    private JButton viewMessageSent;
    private JButton viewMessageReceived;
    private JButton backToMessageOption;
    private JButton backToMessageList;
    private JButton writeMessage;
    private JButton sendMessage;
    private JButton cancelMessage;
    private String messageType;

    // Account information
    private JPanel accountInfoPanel;
    private JTextArea accountInfoText;

    ActionListener actionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            // Log in option
            if (e.getActionCommand().equals(LOG_IN)) {
                username.setText("");
                password.setText("");
                cardLayout.show(cardPanel, "Login");
            }
            
            // Sign up option
            if (e.getActionCommand().equals(SIGN_UP)) {
                createUser.setText("");
                createPass.setText("");
                cardLayout.show(cardPanel, "Signup");
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
                            cardLayout.show(cardPanel, "MainMenu");
                            JOptionPane.showMessageDialog(null, "Logged in successfully!",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        throw new IOException();
                    }
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null,
                            "Error sending request to server.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            
            // Create account button
            if (e.getActionCommand().equals(ACCOUNT_CREATED)) {
                // Get inputs
                String usernameInput = createUser.getText();
                String passwordInput = createPass.getText();

                // Validate inputs
                if (usernameInput.isBlank() || passwordInput.isBlank() ||
                usernameInput.contains(" ") || passwordInput.contains(" ")) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid username and password.",
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
                            cardLayout.show(cardPanel, "MainMenu");
                            JOptionPane.showMessageDialog(null, "Account created successfully!",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        throw new IOException();
                    }
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null,
                            "Error sending request to server.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            
            if (e.getActionCommand().equals(CANCEL)) {
                cardLayout.show(cardPanel, "Initial");
            }
            // Back to menu
            if (e.getActionCommand().equals(MAIN_MENU)) {
                try {
                    client.sendRequest(MAIN_MENU);
                    cardLayout.show(cardPanel, "MainMenu");
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null,
                        "Failed to send request to the server.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                }
                
            }
            
            // Item Listing
            if (e.getActionCommand().equals(ITEM_LISTING)) {
                try {
                    Object response = client.sendRequest(ITEM_LISTING);
                    if (response instanceof List) {
                        List<ItemInterface> itemList = (List<ItemInterface>) response;
                        // use itemList
                        myItemListing(itemList);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Failed to retrieve item list.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null,
                            "Error sending request to server.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            // Item Search
            if (e.getActionCommand().equals(ITEM_SEARCH)) {
                try {
                    Object response = client.sendRequest(ITEM_SEARCH);
                    if (response instanceof String) {
                        String message = (String) response;
                        if (message.equals(ERROR_MESSAGE)) {
                            JOptionPane.showMessageDialog(null, "Error occurred.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (message.equals(SUCCESS_MESSAGE)) {
                            searchResultSubpanel.removeAll();
                            itemNameQuery.setText("");
                            itemSellerquery.setText("");
                            itemPriceLow.setText("0");
                            itemPriceHigh.setText("99999");
                            cardLayout.show(cardPanel, "ItemSearch");
                        }
                    } else {
                        throw new IOException();
                    }
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null,
                            "Error sending request to server.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                 // search by name/description, price range, and seller username
            }
            // Search button
            if (e.getActionCommand().equals(SEARCH)) {
                // Get inputs
                String textQuery = itemNameQuery.getText(); // Searches in Item.name and Item.description
                String lowPriceQuery = itemPriceLow.getText(); // Low-bound for price range
                String highPriceQuery = itemPriceHigh.getText(); // High-bound for price range
                String sellerQuery = itemSellerquery.getText(); // Searches in Item.sellerID

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
                    Object response = client.sendRequest(SEARCH,
                            textQuery,
                            lowPriceQuery,
                            highPriceQuery,
                            sellerQuery);
                    if (response instanceof List) {
                        List<ItemInterface> itemList = (List<ItemInterface>) response;
                        // use filtered itemList (display search result)
                        searchResult(itemList);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Failed to retrieve item list.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null,
                            "Error sending request to server.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            
            // Message Listing
            if (e.getActionCommand().equals(MESSAGE_LIST)) {
                cardLayout.show(cardPanel, "MessageOption");
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
                                cardLayout.show(cardPanel,"Initial");
                            } else {
                                JOptionPane.showMessageDialog(null,
                                        "Failed to delete account.",
                                        "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            throw new IOException();
                        }
                    } catch (IOException ioe) {
                        JOptionPane.showMessageDialog(null,
                                "Error sending request to server.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
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
                            cardLayout.show(cardPanel,"Initial");
                        } else {
                            JOptionPane.showMessageDialog(null,
                                    "Failed to log-out.",
                                    "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        throw new IOException();
                    }
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null,
                            "Error sending request to server.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }

            // Display Balance
            if (e.getActionCommand().equals(ACCOUNT_INFO)) {
                try {
                    Object response = client.sendRequest(ACCOUNT_INFO);
                    if (response instanceof String) {
                        String accountInfo = (String) response;
                        // use balance
                        accountInfoText.setText(accountInfo);
                        cardLayout.show(cardPanel, "AccountInfo");
                    } else {
                        throw new IOException();
                    }
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null,
                            "Error sending request to server.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    };

    ActionListener itemListActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals(ADD_ITEM)) {
                itemName.setText("");
                itemPrice.setText("");
                itemDescription.setText("");
                cardLayout.show(cardPanel, "AddItem");
            }
            if (e.getActionCommand().equals(ITEM_CREATED)) {
                // Get inputs
                String itemNameInput = itemName.getText();
                String itemPriceInput = itemPrice.getText();
                String itemDescrptionInput = itemDescription.getText();

                // Validate inputs
                if (itemNameInput.isEmpty() || itemPriceInput.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter both an item name and price.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    double price = Double.parseDouble(itemPriceInput);
                    if (price < 0) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException nfe) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid number for the price.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Send request and process response
                try {
                    Object response = client.sendRequest(ITEM_CREATED, itemNameInput, itemPriceInput, itemDescrptionInput);
                    if (response instanceof String) {
                        String message = (String) response;
                        if (message.equals(ERROR_MESSAGE)) {
                            JOptionPane.showMessageDialog(null, "Failed to create item.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (message.equals(SUCCESS_MESSAGE)) {
                            cardLayout.show(cardPanel, "ItemList");
                            JOptionPane.showMessageDialog(null, "Item created successfully!",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        throw new IOException();
                    }
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null,
                            "Error sending request to server.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                try {
                    Object response = client.sendRequest(ITEM_LISTING);
                    if (response instanceof List) {
                        List<ItemInterface> itemList = (List<ItemInterface>) response;
                        // use itemList
                        myItemListing(itemList);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Failed to retrieve item list.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    } 
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null,
                            "Error sending request to server.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getSource() == deleteItem) {
                try {
                    Object response = client.sendRequest(DELETE_ITEM, e.getActionCommand());
                    if (response instanceof String) {
                        String message = (String) response;
                        if (message.equals(ERROR_MESSAGE)) {
                            JOptionPane.showMessageDialog(null, "Failed to delete item.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (message.equals(SUCCESS_MESSAGE)) {
                            cardLayout.show(cardPanel, "ItemList");
                            JOptionPane.showMessageDialog(null, "Item deleted successfully!",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        throw new IOException();
                    }
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null,
                            "Error sending request to server.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
                try {
                    Object response = client.sendRequest(ITEM_LISTING);
                    if (response instanceof List) {
                        List<ItemInterface> itemList = (List<ItemInterface>) response;
                        // use itemList
                        myItemListing(itemList);
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Failed to retrieve item list.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null,
                            "Error sending request to server.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            if (e.getActionCommand().equals(CANCEL)) {
                cardLayout.show(cardPanel, "ItemList");
            }
        }
    };

    ActionListener itemSearchActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == purchase) {
                try {
                    Object response = client.sendRequest(BUY, e.getActionCommand().split(":")[0],
                            e.getActionCommand().split(":")[1], paymentPW.getText());
                    if (response instanceof String) {
                        String message = (String) response;
                        if (message.equals(WRONG_PW)) {
                            JOptionPane.showMessageDialog(null, "Incorrect Password",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (message.equals(ERROR_MESSAGE)) {
                            JOptionPane.showMessageDialog(null, "Failed to process transaction",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        } else if (message.equals(SUCCESS_MESSAGE)) {
                            cardLayout.show(cardPanel, "ItemInfo");
                            JOptionPane.showMessageDialog(null, "Item purchased successfully!",
                                    "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    } else {
                        throw new IOException();
                    }
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null,
                            "Error sending request to server.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getActionCommand().equals(ITEM_SEARCH)) {
                searchResultSubpanel.removeAll();
                itemNameQuery.setText("");
                itemSellerquery.setText("");
                itemPriceLow.setText("0");
                itemPriceHigh.setText("99999");
                cardLayout.show(cardPanel, "ItemSearch");
            } else if (e.getActionCommand().equals(CANCEL)) {
                cardLayout.show(cardPanel, "ItemInfo");
            }
        }
    };

    ActionListener messageActionListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == sendMessage) {
                if (!messageContent.getText().isBlank()) {
                    try {
                        Object response = client.sendRequest(SEND_MESSAGE, e.getActionCommand(),
                                messageContent.getText(), END_MESSAGE);
                        if (response instanceof String) {
                            String message = (String) response;
                            if (message.equals(ERROR_MESSAGE)) {
                                JOptionPane.showMessageDialog(null, "Failed to send message.",
                                        "Error", JOptionPane.ERROR_MESSAGE);
                            } else if (message.equals(SUCCESS_MESSAGE)) {
                                cardLayout.show(cardPanel, "MessageOption");
                                JOptionPane.showMessageDialog(null, "Message sent",
                                        "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                        } else {
                            throw new IOException();
                        }
                    } catch (IOException ioe) {
                        JOptionPane.showMessageDialog(null,
                                "Error sending request to server.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else if (e.getActionCommand().equals(MESSAGE_SENT)) {
                try {
                    Object response = client.sendRequest(MESSAGE_SENT);
                    if (response instanceof List) {
                        List<MessageInterface> messageList = (List<MessageInterface>) response;
                        // use messageList
                        messageListing(messageList);
                        messageType = "Sent";
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Failed to retrieve message list.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null,
                            "Error sending request to server.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getActionCommand().equals(MESSAGE_RECEIVED)) {
                try {
                    Object response = client.sendRequest(MESSAGE_RECEIVED);
                    if (response instanceof List) {
                        List<MessageInterface> messageList = (List<MessageInterface>) response;
                        // use messageList
                        messageListing(messageList);
                        messageType = "Received";
                    } else {
                        JOptionPane.showMessageDialog(null,
                                "Failed to retrieve message list.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (IOException ioe) {
                    JOptionPane.showMessageDialog(null,
                            "Error sending request to server.",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else if (e.getSource() == writeMessage) {
                String recipient = null;
                if (messageType.equals("Seller")) {
                    recipient = e.getActionCommand();
                } else if (messageType.equals("Sent")) {
                    recipient = e.getActionCommand().split(":")[1];
                } else if (messageType.equals("Received")) {
                    recipient = e.getActionCommand().split(":")[0];
                }
                newMessage(recipient);
            } else if (e.getSource() == cancelMessage) {
                if (messageType.equals("Seller")) {
                    cardLayout.show(cardPanel, "ItemInfo");
                } else {
                    cardLayout.show(cardPanel, "MessageList");
                }
            }
        }
    };

    public Client beginConnection() {
        try {
            System.out.println("Connected to server!");
            return (new Client(HOST, PORT));
        } catch (IOException ioe) {
            System.out.println("Connection failed!");
            return null;
        }
    }

    // Initial sign in screen
    public void initial() {
        login = new JButton("Login");
        login.addActionListener(actionListener);
        login.setActionCommand(LOG_IN);
        createAccount = new JButton("Create Account");
        createAccount.addActionListener(actionListener);
        createAccount.setActionCommand(SIGN_UP);

        initialPanel = new JPanel();
        initialPanel.setLayout(new BoxLayout(initialPanel, BoxLayout.Y_AXIS));
        login.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAccount.setAlignmentX(Component.CENTER_ALIGNMENT);
        initialPanel.add(login);
        initialPanel.add(createAccount);
        cardPanel.add(initialPanel, "Initial");
    }

    // Log in screen
    public void allowLogin() {
        username = new JTextField(50);
        JLabel usernameLabel = new JLabel("Enter Username:");
        password = new JTextField(50);
        JLabel passwordLabel = new JLabel("Enter Password:");
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        password.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginToAccount = new JButton("Login");
        loginToAccount.addActionListener(actionListener);
        loginToAccount.setActionCommand(LOGGED_IN);
        loginToAccount.setAlignmentX(Component.CENTER_ALIGNMENT);

        cancelSignIn = new JButton("Cancel");
        cancelSignIn.addActionListener(actionListener);
        cancelSignIn.setActionCommand(CANCEL);
        cancelSignIn.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginPanel.add(usernameLabel);
        loginPanel.add(username);
        loginPanel.add(passwordLabel);
        loginPanel.add(password);
        loginPanel.add(loginToAccount);
        loginPanel.add(cancelSignIn);
        cardPanel.add(loginPanel, "Login");
    }

    // Create account screen
    public void create() {
        createUser = new JTextField(50);
        JLabel usernameLabel = new JLabel("Enter Username:");
        createPass = new JTextField(50);
        JLabel passwordLabel = new JLabel("Enter Password:");
        signupPanel = new JPanel();
        signupPanel.setLayout(new BoxLayout(signupPanel, BoxLayout.Y_AXIS));
        createUser.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        createPass.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        createAcc = new JButton("Create Account");
        createAcc.addActionListener(actionListener);
        createAcc.setActionCommand(ACCOUNT_CREATED);
        createAcc.setAlignmentX(Component.CENTER_ALIGNMENT);
        cancelSignIn = new JButton("Cancel");
        cancelSignIn.addActionListener(actionListener);
        cancelSignIn.setActionCommand(CANCEL);
        cancelSignIn.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupPanel.add(usernameLabel);
        signupPanel.add(createUser);
        signupPanel.add(passwordLabel);
        signupPanel.add(createPass);
        signupPanel.add(createAcc);
        signupPanel.add(cancelSignIn);
        cardPanel.add(signupPanel, "Signup");
    }

    // Main menu screen
    public void mainMenuScreen() {
        itemListing = new JButton("View Item List");
        itemListing.addActionListener(actionListener);
        itemListing.setActionCommand(ITEM_LISTING);

        itemSearch = new JButton("Search Item");
        itemSearch.addActionListener(actionListener);
        itemSearch.setActionCommand(ITEM_SEARCH);

        messages = new JButton("View Messages");
        messages.addActionListener(actionListener);
        messages.setActionCommand(MESSAGE_LIST);

        deleteAccount = new JButton("Delete Account");
        deleteAccount.addActionListener(actionListener);
        deleteAccount.setActionCommand(DELETE_ACCOUNT);

        logout = new JButton("Logout");
        logout.addActionListener(actionListener);
        logout.setActionCommand(LOG_OUT);

        displayInfo = new JButton("Account Info");
        displayInfo.addActionListener(actionListener);
        displayInfo.setActionCommand(ACCOUNT_INFO);

        menuPanel = new JPanel();
        menuPanel.setLayout(new FlowLayout());
        menuPanel.add(itemListing);
        menuPanel.add(itemSearch);
        menuPanel.add(messages);
        menuPanel.add(deleteAccount);
        menuPanel.add(logout);
        menuPanel.add(displayInfo);
        cardPanel.add(menuPanel, "MainMenu");
    }

    /* Item List */
    // Item listing screen
    public void itemListSetup() {
        itemListPanel = new JPanel();
        JPanel addAndMenu = new JPanel(new GridLayout(1, 2));
        mainMenu = new JButton("Back to Menu");
        mainMenu.addActionListener(actionListener);
        mainMenu.setActionCommand(MAIN_MENU);
        addItem = new JButton("Add new item");
        addItem.setActionCommand(ADD_ITEM);
        addItem.addActionListener(itemListActionListener);
        addAndMenu.add(addItem);
        addAndMenu.add(mainMenu);
        itemListSubPanel = new JPanel(new GridLayout(0, 1));

        myItemInfoPanel = new JPanel();
        myItemInfo = new JTextArea(2, 0);
        myItemInfo.setEditable(false);
        backToItems = new JButton("Back");
        backToItems.addActionListener(actionListener);
        backToItems.setActionCommand(ITEM_LISTING);
        deleteItem = new JButton("Delete");
        deleteItem.addActionListener(itemListActionListener);

        JPanel backAndDelete = new JPanel(new GridLayout(1, 2));
        backAndDelete.add(backToItems);
        backAndDelete.add(deleteItem);
        myItemInfoPanel.add(myItemInfo, BorderLayout.CENTER);
        myItemInfoPanel.add(backAndDelete, BorderLayout.SOUTH);

        itemListPanel.add(itemListSubPanel);
        itemListPanel.add(addAndMenu, BorderLayout.SOUTH);
        cardPanel.add(itemListPanel, "ItemList");
        cardPanel.add(myItemInfoPanel, "MyItemInfo");
    }
    public void myItemListing(List<ItemInterface> itemList) {
        itemListSubPanel.removeAll();
        if (itemList.isEmpty()) {
            JLabel noItem = new JLabel("No Items");
            noItem.setPreferredSize(new Dimension(500, 40));
            itemListSubPanel.add(noItem);
        }
        for (ItemInterface item : itemList) {
            JButton itemButton = new JButton(item.getName());
            itemButton.setPreferredSize(new Dimension(500, 40));
            itemButton.addActionListener(e -> {
                if (item.getBuyerID().isEmpty()) {
                    myItemInfo.setText("Item name: " + item.getName() + 
                                        "\nPrice: $" + item.getPrice() +
                                        "\nDescription: " + item.getDescription());
                } else {
                    myItemInfo.setText("Item name: " + item.getName() + 
                                        "\nPrice: $" + item.getPrice() +
                                        "\nDescription: " + item.getDescription() +
                                        "\nBought by: " + item.getBuyerID());
                }
                
                deleteItem.setActionCommand(item.getName());
                cardLayout.show(cardPanel, "MyItemInfo");
            });
            itemListSubPanel.add(itemButton);
        }
        cardPanel.revalidate();
        cardLayout.show(cardPanel, "ItemList");
    }

    // Add item screen
    public void addItem() {
        itemName = new JTextField(50);
        JLabel itemNameLabel = new JLabel("Enter Item Name:");
        itemPrice = new JTextField(50);
        JLabel itemPriceLabel = new JLabel("Enter Item Price:");
        itemDescription = new JTextField(100);
        JLabel itemDescriptionLabel = new JLabel("Enter Item Description:");
        itemAddPanel = new JPanel();
        itemAddPanel.setLayout(new BoxLayout(itemAddPanel, BoxLayout.Y_AXIS));
        itemName.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemPrice.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemDescription.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemDescriptionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel addAndCancel = new JPanel(new GridLayout(1, 2));
        createItem = new JButton("Add");
        createItem.addActionListener(itemListActionListener);
        createItem.setActionCommand(ITEM_CREATED);
        cancelCreate = new JButton("Cancel");
        cancelCreate.addActionListener(itemListActionListener);
        cancelCreate.setActionCommand(CANCEL);
        addAndCancel.add(createItem);
        addAndCancel.add(cancelCreate);

        addAndCancel.setAlignmentX(Component.CENTER_ALIGNMENT);
        itemAddPanel.add(itemNameLabel);
        itemAddPanel.add(itemName);
        itemAddPanel.add(itemPriceLabel);
        itemAddPanel.add(itemPrice);
        itemAddPanel.add(itemDescriptionLabel);
        itemAddPanel.add(itemDescription);
        itemAddPanel.add(addAndCancel);
        cardPanel.add(itemAddPanel, "AddItem");
    }

    /* Item searching screen */
    public void searchItem() {
        itemSearchPanel = new JPanel();
        JPanel searchBoxes = new JPanel(new GridLayout(2, 4));
        JPanel searchAndEnter = new JPanel();
        itemNameQuery = new JTextField(2);
        itemPriceLow = new JTextField("0", 2);
        itemPriceHigh = new JTextField("99999", 2);
        itemSellerquery = new JTextField(2);
        JLabel itemNameSearchLabel = new JLabel("Name/Description");
        JLabel minPriceLabel = new JLabel("Minimum Price");
        JLabel maxPriceLabel = new JLabel("Maximum Price");
        JLabel sellerSearchLabel = new JLabel("Seller Name");

        searchBoxes.add(itemNameSearchLabel);
        searchBoxes.add(itemNameQuery);
        searchBoxes.add(sellerSearchLabel);
        searchBoxes.add(itemSellerquery);
        searchBoxes.add(minPriceLabel);
        searchBoxes.add(itemPriceLow);
        searchBoxes.add(maxPriceLabel);
        searchBoxes.add(itemPriceHigh);

        search = new JButton("Search");
        search.addActionListener(actionListener);
        search.setActionCommand(SEARCH);

        searchAndEnter.add(searchBoxes, BorderLayout.CENTER);
        searchAndEnter.add(search, BorderLayout.EAST);

        mainMenu = new JButton("Back to Menu");
        mainMenu.addActionListener(actionListener);
        mainMenu.setActionCommand(MAIN_MENU);

        searchResultSubpanel = new JPanel(new GridLayout(0, 1));

        itemSearchPanel.add(searchAndEnter, BorderLayout.NORTH);
        itemSearchPanel.add(searchResultSubpanel, BorderLayout.EAST);
        itemSearchPanel.add(mainMenu, BorderLayout.SOUTH);

        cardPanel.add(itemSearchPanel, "ItemSearch");
    }

    // Item search result
    public void searchResult(List<ItemInterface> itemList) {
        searchResultSubpanel.removeAll();
        if (itemList.isEmpty()) {
            JLabel noItem = new JLabel("No results found");
            noItem.setPreferredSize(new Dimension(350, 200));
            searchResultSubpanel.add(noItem);
        }
        for (ItemInterface item : itemList) {
            JButton itemButton = new JButton(item.getName());
            itemButton.setPreferredSize(new Dimension(500, 40));
            itemButton.addActionListener(e -> {
                viewItem(item);
                cardLayout.show(cardPanel, "ItemInfo");
            });
            searchResultSubpanel.add(itemButton, BorderLayout.CENTER);
        }
        cardPanel.revalidate();
        cardLayout.show(cardPanel, "ItemSearch");
    }
    public void viewItem(ItemInterface item) {

        itemInfoPanel = new JPanel();
        itemInfo = new JTextArea(2, 0);
        itemInfo.setText("Item name: " + item.getName() + 
                        "\nPrice: $" + item.getPrice() +
                        "\nDescription: " + item.getDescription() +
                        "\nSeller: " + item.getSellerID());
        itemInfo.setEditable(false);

        buyItem = new JButton("Buy");
        buyItem.addActionListener(itemSearchActionListener);
        buyItem.addActionListener(e -> {
            pay(item);
        });

        writeMessage = new JButton("Message seller");
        writeMessage.addActionListener(messageActionListener);
        writeMessage.setActionCommand(item.getSellerID());
        messageType = "Seller";

        backToSearch = new JButton("Back");
        backToSearch.addActionListener(actionListener);
        backToSearch.setActionCommand(ITEM_SEARCH);

        JPanel buyAndMessage = new JPanel(new GridLayout(1, 3));
        buyAndMessage.add(buyItem);
        buyAndMessage.add(writeMessage);
        buyAndMessage.add(backToSearch);
        itemInfoPanel.add(itemInfo, BorderLayout.NORTH);
        itemInfoPanel.add(buyAndMessage, BorderLayout.SOUTH);

        buyItemPanel = new JPanel();
        JLabel confirmPayment = new JLabel("Confirm payment");
        confirmPayment.setPreferredSize(new Dimension(500, 20));
        confirmItemInfo = new JTextArea();
        confirmItemInfo.setPreferredSize(new Dimension(500, 60));
        JPanel confirmPay = new JPanel(new GridLayout(2, 1));

        purchase = new JButton("Pay");
        purchase.addActionListener(itemSearchActionListener);
        cancelPurchase = new JButton("Cancel");
        cancelPurchase.addActionListener(itemSearchActionListener);
        cancelPurchase.setActionCommand(CANCEL);
        JPanel payAndCancel = new JPanel(new GridLayout(1,2));
        payAndCancel.add(purchase);
        payAndCancel.add(cancelPurchase);
        paymentPW = new JTextField();
        JLabel enterPW = new JLabel("Enter password:");
        JPanel passwordInput = new JPanel(new GridLayout(2, 1));
        passwordInput.add(enterPW);
        passwordInput.add(paymentPW);
        confirmPay.add(confirmItemInfo);
        confirmPay.add(passwordInput);
        buyItemPanel.add(confirmPayment, BorderLayout.NORTH);
        buyItemPanel.add(confirmPay, BorderLayout.CENTER);
        buyItemPanel.add(payAndCancel, BorderLayout.SOUTH);

        cardPanel.add(itemInfoPanel, "ItemInfo");
        cardPanel.add(buyItemPanel, "ConfirmPurchase");
    }

    // Buy Item
    public void pay(ItemInterface item) {
        confirmItemInfo.setText("Name: " + item.getName() +
                                "\nPrice: $" + item.getPrice() +
                                "\nSeller: " + item.getSellerID());
        paymentPW.setText("");
        purchase.setActionCommand(item.getName() + ":" + item.getSellerID());
        cardPanel.revalidate();
        cardLayout.show(cardPanel, "ConfirmPurchase");
    }

    /* Message lists */
    public void messageListSetup() {
        messageListOptionPanel = new JPanel(new FlowLayout());

        viewMessageSent = new JButton("Sent");
        viewMessageSent.addActionListener(messageActionListener);
        viewMessageSent.setActionCommand(MESSAGE_SENT);

        viewMessageReceived = new JButton("Received");
        viewMessageReceived.addActionListener(messageActionListener);
        viewMessageReceived.setActionCommand(MESSAGE_RECEIVED);

        mainMenu = new JButton("Back to Menu");
        mainMenu.addActionListener(actionListener);
        mainMenu.setActionCommand(MAIN_MENU);

        messageListOptionPanel.add(viewMessageSent);
        messageListOptionPanel.add(viewMessageReceived);
        messageListOptionPanel.add(mainMenu);
        
        messageListPanel = new JPanel();
        messageListSubPanel = new JPanel(new GridLayout(0, 1));
        backToMessageOption = new JButton("Back");
        backToMessageOption.addActionListener(actionListener);
        backToMessageOption.setActionCommand(MESSAGE_LIST);
        messageListPanel.add(backToMessageOption, BorderLayout.NORTH);
        messageListPanel.add(messageListSubPanel, BorderLayout.CENTER);

        cardPanel.add(messageListOptionPanel, "MessageOption");
        cardPanel.add(messageListPanel, "MessageList");
    }
    public void messageListing(List<MessageInterface> messageList) {
        messageListSubPanel.removeAll();
        if (messageList.isEmpty()) {
            JLabel noMessage = new JLabel("No Messages");
            noMessage.setPreferredSize(new Dimension(500, 40));
            messageListSubPanel.add(noMessage);
        }
        for (MessageInterface message : messageList) {
            JButton messageButton = new JButton("From: " + message.getSenderID() + 
                                                " To: " + message.getRecipientID() +
                                                " " + message.getTimestamp());
            messageButton.setPreferredSize(new Dimension(250, 60));
            messageButton.addActionListener(e -> {
                viewMessage(message);
                cardLayout.show(cardPanel, "ViewMessage");
            });
            messageListSubPanel.add(messageButton);
        }
        cardPanel.revalidate();
        cardLayout.show(cardPanel, "MessageList");
    }
    public void viewMessage(MessageInterface message) {
        viewMessagePanel = new JPanel();
        JPanel replyAndBack = new JPanel(new GridLayout(1, 2));
        JTextArea viewMessageContent = new JTextArea(message.getContents());
        viewMessageContent.setPreferredSize(new Dimension(500, 200));
        viewMessageContent.setEditable(false);
        JTextArea messageInfo = new JTextArea("From: " + message.getSenderID() + 
                                              "\nTo: " + message.getRecipientID() +
                                              "\nTime: " + message.getTimestamp());
        messageInfo.setPreferredSize(new Dimension(500, 60));
        backToMessageList = new JButton("Back");
        backToMessageList.addActionListener(actionListener);
        backToMessageList.setActionCommand(MESSAGE_LIST);
        writeMessage = new JButton("Reply");
        writeMessage.addActionListener(messageActionListener);
        writeMessage.setActionCommand(message.getSenderID() + ":" + message.getRecipientID());

        replyAndBack.add(writeMessage);
        replyAndBack.add(backToMessageList);
        viewMessagePanel.add(messageInfo, BorderLayout.NORTH);
        viewMessagePanel.add(viewMessageContent, BorderLayout.CENTER);
        viewMessagePanel.add(replyAndBack, BorderLayout.SOUTH);
        cardPanel.add(viewMessagePanel, "ViewMessage");
    }

    // Message UI
    public void message() {
        messagePanel = new JPanel();
        JPanel enterRecipient = new JPanel(new GridLayout(1, 2));
        JLabel recipientLabel = new JLabel("Recipient:");
        recipientName = new JTextField();
        recipientName.setPreferredSize(new Dimension(200, 20));
        enterRecipient.add(recipientLabel);
        enterRecipient.add(recipientName);
        messageContent = new JTextArea();
        messageContent.setPreferredSize(new Dimension(500, 200));
        sendMessage = new JButton("Send");
        sendMessage.setPreferredSize(new Dimension(100, 30));
        cancelMessage = new JButton("Cancel");
        JPanel sendAndCancel = new JPanel(new GridLayout(1, 2));
        cancelMessage.addActionListener(messageActionListener);
        sendAndCancel.add(sendMessage);
        sendAndCancel.add(cancelMessage);
        sendMessage.addActionListener(messageActionListener);
        messagePanel.add(enterRecipient, BorderLayout.NORTH);
        messagePanel.add(messageContent, BorderLayout.CENTER);
        messagePanel.add(sendAndCancel, BorderLayout.SOUTH);
        cardPanel.add(messagePanel, "WriteMessage");
    }

    // Write new message
    public void newMessage(String recipientID) {
        recipientName.setText(recipientID);
        sendMessage.setActionCommand(recipientID);
        messageContent.setText("");
        cardPanel.revalidate();
        cardLayout.show(cardPanel, "WriteMessage");
    }

    // Show Account Information
    public void showInfo() {
        accountInfoPanel = new JPanel();
        accountInfoText = new JTextArea();
        accountInfoText.setPreferredSize(new Dimension(500, 200));

        mainMenu = new JButton("Back to Menu");
        mainMenu.addActionListener(actionListener);
        mainMenu.setActionCommand(MAIN_MENU);
        accountInfoPanel.add(accountInfoText, BorderLayout.CENTER);
        accountInfoPanel.add(mainMenu, BorderLayout.SOUTH);

        cardPanel.add(accountInfoPanel, "AccountInfo");
    }

    public void run() {
        client = beginConnection();

        frame = new JFrame("GUI");
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);

        initial();
        allowLogin();
        create();
        mainMenuScreen();
        itemListSetup();
        addItem();
        searchItem();
        message();
        messageListSetup();
        showInfo();

        frame.add(cardPanel, BorderLayout.CENTER);
        cardLayout.show(cardPanel, "Initial");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new GUI());
    }
}
