/*
 * TCSS 305
 * Spring 2016
 * assignment 2 - shoppingcart
 */
package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;

import model.Item;

import org.junit.Before;
import org.junit.Test;



/**
 * Tests the Item class.
 * 
 * @author Todd Crane
 * @version 4/8/2016
 */
public class ItemTest {

    // The test fixtures that will be used to run tests.
    /** An Item without bulk option to use in the tests. */
    private Item myItemNonBulk;
    /** An Item with bulk option to use in the tests. */
    private Item myItemBulk;
    
    // Setup will run before each test.
    /**
     * A method to initialize the test fixtures before each test.
     */
    @Before
    public void setUp() { // No need to throw any exceptions.
        myItemNonBulk = new Item("testNonBulk", BigDecimal.ZERO);
        myItemBulk = new Item("testBulk", BigDecimal.TEN, 4, 
                              new BigDecimal("30"));
    }

    // Tests the constructor of an Item with name and price.
    /**
     * Test method for {@link model.Item#Item(java.lang.String, 
     * java.math.BigDecimal)}.
     */
    @Test
    public void testConstructorNonBulk() {
        assertEquals(BigDecimal.ZERO, myItemNonBulk.getPrice());
        assertFalse(myItemNonBulk.isBulk());
    }
    
    // Tests a non bulk Item for a null String.
    /**
     * Test method for {@link model.Item#Item(java.lang.String, 
     * java.math.BigDecimal)} for a null String.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorNonBulkNullName() {
        new Item(null, BigDecimal.ZERO);
    }
    
    // Tests a non bulk Item for a null BigDecimal price.
    /**
     * Test method for {@link model.Item#Item(java.lang.String, 
     * java.math.BigDecimal)} for a null BigDecimal price.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorNonBulkNullPrice() {
        new Item("C", null);
    }

    // Tests a non bulk Item for a negative price.
    /**
     * Test method for {@link model.Item#Item(java.lang.String, 
     * java.math.BigDecimal)} for a negative price.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNonBulkLessZeroPrice() {
        new Item("C", new BigDecimal("-1"));
    }
    
    /*
     * Tests the constructor of an Item with a name, price,
     * bulk quantity and bulk price.
     */
    /**
     * Test method for {@link model.Item#Item(java.lang.String, 
     * java.math.BigDecimal, int, java.math.BigDecimal)}.
     */
    @Test
    public void testConstructorBulk() {
        assertEquals(BigDecimal.TEN, myItemBulk.getPrice());
        assertEquals(4, myItemBulk.getBulkQuantity());
        assertEquals(new BigDecimal("30"), myItemBulk.getBulkPrice());
        assertTrue(myItemBulk.isBulk());
    }
    
    // Tests a bulk Item for a null String.
    /**
     * Test method for {@link model.Item#Item(java.lang.String, 
     * java.math.BigDecimal, int, java.math.BigDecimal)} for a null String.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorBulkNullName() {
        new Item(null, BigDecimal.ZERO, 4, new BigDecimal("30"));
    }
    
    // Tests a bulk Item for a null BigDecimal price.
    /**
     * Test method for {@link model.Item#Item(java.lang.String, 
     * java.math.BigDecimal, int, java.math.BigDecimal)} for a 
     * null BigDecimal price.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorBulkNullPrice() {
        new Item("C", null, 4, new BigDecimal("30"));
    }
    
    // Tests a bulk Item for a negative price.
    /**
     * Test method for {@link model.Item#Item(java.lang.String, 
     * java.math.BigDecimal, int, java.math.BigDecimal)} for a negative price.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorBulkLessZeroPrice() {
        new Item("C", new BigDecimal("-1"), 4, new BigDecimal("30"));
    }
    
    // Tests a bulk Item for a negative bulk quantity.
    /**
     * Test method for {@link model.Item#Item(java.lang.String, 
     * java.math.BigDecimal, int, java.math.BigDecimal)} for a negative
     * bulk quantity.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorBulkLessZeroBulkQuantity() {
        new Item("C", BigDecimal.TEN, -1, new BigDecimal("30"));
    }

    // Tests a bulk Item for a null BigDecimal bulk quantity price.
    /**
     * Test method for {@link model.Item#Item(java.lang.String, 
     * java.math.BigDecimal, int, java.math.BigDecimal)} for a 
     * null BigDecimal bulk quantity price.
     */
    @Test(expected = NullPointerException.class)
    public void testConstructorBulkNullBulkPrice() {
        new Item("C", BigDecimal.TEN, 4, null);
    }
    
    // Tests a bulk Item for a negative bulk price.
    /**
     * Test method for {@link model.Item#Item(java.lang.String, 
     * java.math.BigDecimal, int, java.math.BigDecimal)} for a negative
     * bulk price.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testConstructorBulkLessZeroBulkPrice() {
        new Item("C", BigDecimal.TEN, 4, new BigDecimal("-1"));
    }
    
    // Tests for the correct output from toString for a non bulk Item.
    /**
     * Test method for {@link model.Item#toString()}.
     */
    @Test
    public void testToStringNonBulk() {
        assertEquals("testNonBulk, $0.00", myItemNonBulk.toString());
    }
    
    // Tests for the correct output from toString for a bulk Item.
    /**
     * Test method for {@link model.Item#toString()}.
     */
    @Test
    public void testToStringBulk() {
        assertEquals("testBulk, $10.00 (4 for $30.00)", myItemBulk.toString());
    }

    // Tests for the equality of two non bulk Items.
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsNonBulkObject() {
        final Item i1 = new Item("testNonBulk", BigDecimal.ZERO);
        assertEquals(myItemNonBulk, i1);
    }
    
    // Tests for the equality of name of two non bulk Items.
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsNonBulkName() {
        final Item i1 = new Item("C", BigDecimal.ZERO);
        assertFalse(myItemNonBulk.equals(i1));
    }
    
    // Tests for the equality of price of two non bulk Items.
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsNonBulkPrice() {
        final Item i1 = new Item("testNonBulk", BigDecimal.TEN);
        assertFalse(myItemNonBulk.equals(i1));
    }
    
    // Tests for the equality of two bulk Items.
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsBulkObject() {
        final Item i1 = new Item("testBulk", BigDecimal.TEN, 4,
                                 new BigDecimal("30"));
        assertEquals(myItemBulk, i1);
    }
    
    // Tests for the equality of name of two bulk Items.
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsBulkName() {
        final Item i1 = new Item("C", BigDecimal.TEN, 4,
                                 new BigDecimal("30"));
        assertFalse(myItemBulk.equals(i1));
    }
    
    // Tests for the equality of price of two bulk Items.
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsBulkPrice() {
        final Item i1 = new Item("testBulk", BigDecimal.ONE, 4,
                                 new BigDecimal("30"));
        assertFalse(myItemBulk.equals(i1));
    }
    
    // Tests for the equality of bulk quantity of two bulk Items.
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsBulkBQuantity() {
        final Item i1 = new Item("testBulk", BigDecimal.TEN, 1, 
                                 new BigDecimal("30"));
        assertFalse(myItemBulk.equals(i1));
    }
    
    // Tests for the equality of bulk price of two bulk Items.
    /**
     * Test method for {@link model.Item#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsBulkBPrice() {
        final Item i1 = new Item("testBulk", BigDecimal.TEN, 4,
                                 new BigDecimal("15"));
        assertFalse(myItemBulk.equals(i1));
    }
    
    // Test for correct hash code of two equal non bulk Items.
    /**
     * Test method for {@link model.Item#hashCode()}.
     */
    @Test
    public void testHashCodeNonBulk() {
        final Item i1 = new Item("testNonBulk", BigDecimal.ZERO);
        assertEquals(myItemNonBulk.hashCode(), i1.hashCode());
    }
    
    // Test for correct hash code of two equal bulk Items.
    /**
     * Test method for {@link model.Item#hashCode()}.
     */
    @Test
    public void testHashCodeBulk() {
        final Item i1 = new Item("testBulk", BigDecimal.TEN, 4, 
                                 new BigDecimal("30"));
        assertEquals(myItemBulk.hashCode(), i1.hashCode());
    }
}
