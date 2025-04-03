import java.io.Serializable;
import java.util.*;

/**
 * An item class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Zaid Al Awartani
 * @version April 01, 2025
 */

public class Item implements Serializable, ItemInterface, Writable<Item> {
    private String name;
    private double price;
    private String description;
    private String sellerID;
    private static ArrayList<Item> item = new ArrayList<>();

    public Item(String name, double price, String description, String sellerID) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.sellerID = sellerID;
        Item.item.add(this);
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

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
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

    public static ArrayList<Item> getList() {
        return new ArrayList<>(item);
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nPrice: %.2f\nDescription: %s\nItem ID: %s", name, price, description, sellerID);
    }

}
