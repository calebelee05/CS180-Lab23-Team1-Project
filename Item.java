import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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

public class Item implements Serializable, ItemInterface {
    private String name;
    private double price;
    private String description;
    private String sellerID;
    private static ArrayList<Item> ItemList = new ArrayList<>();
    private static File saveFile = null;

    public Item(String name, double price, String description, String sellerID, String fileName) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.sellerID = sellerID;
        if (saveFile == null) {
            try {
                saveFile = new File(fileName);
                if (!saveFile.exists()) {
                    saveFile.createNewFile();
                }
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        }
        ItemList.add(this);
    }

    // Implement Item interface
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

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setName(String itemName) {
        this.name = itemName;
    }

    public boolean equals(Item item) {
        return (sellerID.equals(item.getSellerID()) && name.equals(item.getName()));
    }

    public static ArrayList<Item> getList() {
        return ItemList;
    }

    // Implement File I/O methods
    public static synchronized void writeObject() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(saveFile))) {
                oos.writeObject(ItemList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static synchronized ArrayList<Item> readObject() {
        ArrayList<Item> objects = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(saveFile))) {
            ItemList = (ArrayList<Item>) ois.readObject();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        
        return ItemList;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nPrice: %.2f\nDescription: %s\nSeller: %s", name, price, description, sellerID);
    }

}
