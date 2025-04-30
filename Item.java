/**
 * An Item class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Team 1 Lab 23
 * @version April 01, 2025
 */
public class Item implements ItemInterface {

    private String name;
    private double price;
    private String description;
    private String sellerID;
    private String buyerID = "";
    public static final String FILEPATH = "ItemData.txt";

    public Item(String name, double price, String description, String sellerID) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.sellerID = sellerID;
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

    public String getBuyerID() {
        return buyerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
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

    public void deleteItem() {
        System.out.println("test");
        Database.getItemList().remove(this);
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
