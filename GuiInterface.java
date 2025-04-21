
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

    void initial();

    void allowLogin();

    void create();

    void loggedIn();

    void myItemListing(List<ItemInterface> itemList);

    void addItem();

    void searchItem();

    void searchResult(List<ItemInterface> itemList);

    void messageListing(List<MessageInterface> messageList);

    void showBalance();
}
