/**
 * A ItemInterface interface
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Caleb Lee
 * @version April 3, 2025
 */
public interface ItemInterface {
    String getName();
    double getPrice();
    String getDescription();
    String getSellerID();
    void setSellerID(String sellerID);
    void setPrice(int price);
    void setDescription(String description);
    void setName(String itemName);
}
