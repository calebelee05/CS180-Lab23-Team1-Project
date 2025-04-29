
import java.util.List;

/**
 * A GuiInterface interface
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 2
 *
 * @author Team 1 Lab 23
 * @version March 31, 2025
 */
public interface GuiInterface {

    Client beginConnection();

    void allowLogin();

    void initial();

    void create();

    void mainMenuScreen();

    void itemListSetup();

    void myItemListing(List<ItemInterface> itemList);

    void addItem();

    void searchItem();

    void searchResult(List<ItemInterface> itemList);

    void viewItem(ItemInterface item);

    void pay(ItemInterface item);

    void messageListSetup();

    void messageListing(List<MessageInterface> messageList);

    void viewMessage(MessageInterface message);

    void message();

    void newMessage(String recipientID);

    void showInfo();
}
