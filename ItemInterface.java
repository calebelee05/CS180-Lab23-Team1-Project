
import java.io.Serializable;

/**
 * A ItemInterface interface
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Team 1 Lab 23
 * @version April 3, 2025
 */
public interface ItemInterface extends Serializable {

    // Accessors and Modifiers
    String getName();

    double getPrice();

    String getDescription();

    String getSellerID();

    String getBuyerID();

    void setSellerID(String sellerID);

    void setBuyerID(String buyerID);

    void setPrice(double price);

    void setDescription(String description);

    void setName(String itemName);
}
