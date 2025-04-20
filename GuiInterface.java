import java.util.List;

public interface GuiInterface {
    Client beginConnection();
    void initial();
    void allowLogin();
    void create();
    void loggedIn();
    void myItemListing();
    void addItem();
    void searchItem();
    void searchResult(List<ItemInterface> itemList);
    void messageListing(List<MessageInterface> messageList);
    void showBalance();
}
