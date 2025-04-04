
/**
 * A ItemInterface interface
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Caleb Lee
 * @version April 3, 2025
 */
public interface ItemInterface {
    // Accessors and Modifiers
    String getName();
    double getPrice();
    String getDescription();
    String getSellerID();
    void setSellerID(String sellerID);
    void setPrice(double price);
    void setDescription(String description);
    void setName(String itemName);
    boolean equals(ItemInterface item);

    // TODO: Need to implement
    void deleteItem(); // Remove item from itemList
}
