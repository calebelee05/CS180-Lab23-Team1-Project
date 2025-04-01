import java.io.Serializable;

/**
 * An item class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Zaid Al Awartani
 * @version April 01, 2025
 */

public class Item implements Serializable /*, Writable */ {
    private String name;
    private double price;
    private String description;
    private String itemID;

    public Item(String name, double price, String description, String itemID) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String itemName) {
        this.name = itemName;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nPrice: %.2f\nDescription: %s\nItem ID: %s", name, price, description, itemID);
    }

}
