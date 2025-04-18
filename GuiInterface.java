public interface GuiInterface {
    Client beginConnection();
    void initial();
    void allowLogin();
    void create();
    void loggedIn();
    void myItemListing();
    void addItem();
    void searchItem();
    void messageListing();
}
