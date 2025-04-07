
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;

/**
 * A JUnit test class for the Item Class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Team 1 Lab 23
 * @version April 06, 2025
 */
public class TestItem {

    // Instance variables used for testing with generic items
    String itemName = "Item 1";
    String itemDescription = "Test Item 1";
    double itemPrice = 15.0;
    String sellerID = "Seller1";
    ItemInterface item = new Item(itemName, itemPrice, itemDescription, sellerID);
    String newItemName = "Modified Item 1";
    String newItemDescription = "Modified Description 1";
    double newItemPrice = 20.0;
    String newSellerID = "SellerModified";
    String anotherItemName = "Item 2";
    String anotherItemDescription = "Test Item 2";
    double anotherItemPrice = 12.0;
    String anotherSellerID = "Seller2";
    ItemInterface item2 = new Item(anotherItemName, anotherItemPrice, anotherItemDescription, anotherSellerID);

    // Testing Mutators and Accessors
    @Test
    public void TestGettersSetters() {
        item.setName(newItemName);
        item.setDescription(newItemDescription);
        item.setPrice(newItemPrice);
        item.setSellerID(newSellerID);
        assertEquals(newItemName, item.getName());
        assertEquals(newItemDescription, item.getDescription());
        assertEquals(newItemPrice, item.getPrice(), 0.001);
        assertEquals(newSellerID, item.getSellerID());
    }

    // Testing equals and toString methods
    @Test
    public void testEqualsAndToString() {
        ItemInterface copyItem2 = new Item(anotherItemName,
                anotherItemPrice + 5,
                "Different description",
                anotherSellerID);
        assertTrue(item2.equals(copyItem2));
        assertFalse(item.equals(item2));
        String expectedToString = String.format("Name: %s\nPrice: %.2f\nDescription: %s\nSeller: %s",
                anotherItemName,
                anotherItemPrice,
                anotherItemDescription,
                anotherSellerID);
        assertEquals(expectedToString, item2.toString());
    }

    // Testing the method of deleting an item
    @Test
    public void testDeleteItem() {
        List<ItemInterface> list = Item.getList();
        list.clear();
        ItemInterface testItem1 = new Item("DeleteTest1",
                40.0,
                "To delete",
                "SellerDelete");
        ItemInterface testItem2 = new Item("KeepTest",
                80.0,
                "To keep",
                "SellerKeep");

        assertTrue(list.contains(testItem1));
        assertTrue(list.contains(testItem2));
        testItem1.deleteItem();
        assertFalse(list.contains(testItem1));
        assertTrue(list.contains(testItem2));
    }

    // Testing File IO Operations
    @Test
    public void testObjectReadAndWrite() {
        List<ItemInterface> list = Item.getList();
        list.clear();
        ItemInterface itemFile1 = new Item("ItemFile1",
                150.0,
                "Continued item 1",
                "SellerFile1");
        ItemInterface itemFile2 = new Item("ItemFile2",
                250.0,
                "Continued item 2",
                "SellerFile2");
        Item.writeObject();
        list.clear();
        assertEquals(0, list.size());

        List<ItemInterface> readItems = Item.readObject();
        assertEquals(2, readItems.size());
        boolean itemFound1 = false;
        boolean itemFound2 = false;
        for (ItemInterface item : readItems) {
            if (item.getSellerID().equals("SellerFile1")
                    && item.getName().equals("ItemFile1")) {
                itemFound1 = true;
            }
            if (item.getSellerID().equals("SellerFile2")
                    && item.getName().equals("ItemFile2")) {
                itemFound2 = true;
            }
        }
        assertTrue(itemFound1 && itemFound2);
    }

}
