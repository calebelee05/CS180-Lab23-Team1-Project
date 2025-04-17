import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * This is the GUI class. CS180 Team Phase.
 *
 * @author Anishka Rao, lab section 23
 * 
 * @version April, 2025
 */
public class GUI extends JComponent implements Runnable
{
    Database database;

    JFrame frame;
    Container content;
    JPanel loginPanel;
    JButton login;
    JButton createAccount;
    JButton loginToAccount;
    
    JTextField username;
    JTextField password;
    
    JTextField createUser;
    JTextField createPass;
    JButton createAcc;

    ActionListener actionListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == login) {
                    allowLogin();
                }
                if (e.getSource() == createAccount) {
                    create();
                }
                if (e.getSource() == loginToAccount) {
                    try {
                        UserInterface user = database.logIn(username.getText(), password.getText());
                    } catch (UserNotFoundException unfe) {
                        JOptionPane.showMessageDialog(null, unfe.getMessage(),
                                        "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                if (e.getSource() == createAcc) {
                    User newUser = new User(createUser.getText(), createPass.getText(), 0);
                }
            }
        };

    public void allowLogin() {
        username = new JTextField(50);
        JLabel usernameLabel = new JLabel("Enter Username:");
        password = new JTextField(50);
        JLabel passwordLabel = new JLabel("Enter Password:");
        JPanel userAndPass = new JPanel();
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
        JPanel userAndPass = new JPanel();
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

    public void run() {
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
