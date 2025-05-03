
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.List;
import org.junit.Before;

/**
 * A JUnit test class for the Item Class
 *
 * Purdue University -- CS18000 -- Spring 2025 -- Team Project -- Phase 1
 *
 * @author Team 1 Lab 23
 * @version April 06, 2025
 */
public class TestItem {

    // fields used for testing
    private ItemInterface item1;
    private ItemInterface item2;

    @Before
    public void setUp() {
        Database.getItemList().clear();
        item1 = new Item("itemA", 10.0, "firstItem", "SellerA", "noImage.png");
        item2 = new Item("itemB", 20.0, "secondItem", "SellerB", "noImage.png");
        Database.getItemList().add(item1);
        Database.getItemList().add(item2);
    }

    // Testing Mutators and Accessors
    @Test
    public void testGettersSetters() {
        assertEquals("itemA", item1.getName());
        assertEquals(10.0, item1.getPrice(), 0.001);
        assertEquals("firstItem", item1.getDescription());
        assertEquals("SellerA", item1.getSellerID());
        item1.setName("itemG");
        item1.setPrice(15.0);
        item1.setDescription("updated");
        item1.setSellerID("SellerG");
        assertEquals("itemG", item1.getName());
        assertEquals(15.0, item1.getPrice(), 0.001);
        assertEquals("updated", item1.getDescription());
        assertEquals("SellerG", item1.getSellerID());
    }

    // Testing equals and toString methods
    @Test
    public void testEqualsAndToString() {
        ItemInterface copyItem = new Item("itemB", 0.0, "description", "SellerB", "noImage.png");
        assertTrue(item2.equals(copyItem));
        assertFalse(item1.equals(item2));
        String expectedToString = String.format("Name: %s\nPrice: %.2f\nDescription: %s\nSeller: %s",
                "itemB",
                20.00,
                "secondItem",
                "SellerB");
        assertEquals(expectedToString, item2.toString());
    }
}
