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

public class Item implements Serializable, ItemInterface, Writable<Item> {
    private String name;
    private double price;
    private String description;
    private String sellerID;
    private static ArrayList<Item> item = new ArrayList<>();
    private static File itemData;

    public Item(String name, double price, String description, String sellerID) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.sellerID = sellerID;
        item.add(this);
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

    // Implement Writable interface
    public synchronized void writeObject(String fileName, ArrayList<Item> list) {
        try {
            File f = new File(fileName);
            if (!f.exists()) {
                f.createNewFile();
            }
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
                oos.writeObject(list);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public synchronized ArrayList<Item> readObject(String fileName) {
        ArrayList<Item> objects = new ArrayList<>();
        try {
            File f = new File(fileName);
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
                objects = (ArrayList<Item>) ois.readObject();
                return objects;
            } catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return objects;
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nPrice: %.2f\nDescription: %s\nItem ID: %s", name, price, description, sellerID);
    }

}
