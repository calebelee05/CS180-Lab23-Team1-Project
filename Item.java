
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

/**
 * An Item class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Zaid Al Awartani
 * @version April 01, 2025
 */
public class Item implements ItemInterface {

    private String name;
    private double price;
    private String description;
    private String sellerID;
    private static List<ItemInterface> itemList = Collections.synchronizedList(new ArrayList<>());
    private static final String FILEPATH = "ItemData.txt";

    public Item(String name, double price, String description, String sellerID) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.sellerID = sellerID;
        File saveFile = new File(FILEPATH);
        try {
            if (!saveFile.exists()) {
                saveFile.createNewFile();
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        itemList.add(this);
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

    /* TODO (maybe later): return items corresponding to search keyword
     *  public static ArrayList<Item> searchItems(String searchFor)
     */
    public void deleteItem() {
        itemList.remove(this);
        writeObject();
    }

    public static List<ItemInterface> getList() {
        return itemList;
    }

    // Implement File I/O methods
    public static synchronized void writeObject() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILEPATH))) {
            oos.writeObject(itemList);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    public static synchronized List<ItemInterface> readObject() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILEPATH))) {
            List<ItemInterface> objects = (List<ItemInterface>) ois.readObject();
            itemList = Collections.synchronizedList(objects);
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return itemList;
    }

    @Override
    public boolean equals(Object object) {
        try {
            ItemInterface item = (Item) object;
            return (sellerID.equals(item.getSellerID()) && name.equals(item.getName()));
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nPrice: %.2f\nDescription: %s\nSeller: %s",
                name,
                price,
                description,
                sellerID);
    }

}
