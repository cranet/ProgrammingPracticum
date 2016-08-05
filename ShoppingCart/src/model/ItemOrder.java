/*
 * TCSS 305
 * Spring 2016
 * assignment 2 - shoppingcart
 */
package model;

import java.util.Objects;

/**
 * An item order class that stores a reference to the item and a quantity.
 * 
 * @author Todd Crane
 * @version 4/16/2016
 */
public final class ItemOrder {
    // Fields

    /** A reference to the item.*/
    private final Item myItem;
    /** The amount of items ordered.*/
    private final int myQuantity;
     
    //Constructor that creates an ItemOrder.
    /**
     * Constructs an item order for the given quantity of the given item.
     * @param theItem reference to the item.
     * @param theQuantity the amount ordered.
     * @throws NullPointerException if the Item is null.
     * @throws IllegalArgumentException if the quantity is less than zero.
     */
    public ItemOrder(final Item theItem, final int theQuantity) {
        myItem = Objects.requireNonNull(theItem);
        if (theQuantity < 0) {
            throw new IllegalArgumentException();
        }
        myQuantity = theQuantity;
    }

    /**
     * Returns the item in this ItemOrder.
     * @return the reference to the item in this ItemOrder.
     */
    public Item getItem() {
        return myItem;
    }
    
    /**
     * Returns the amount of an item in this ItemOrder.
     * @return the amount of items.
     */
    public int getQuantity() {
        return myQuantity;
    }

    //Returns a String representation of this ItemOrder.
    /**
     * The String representation of this ItemOrder will be formated as follows:
     * ItemOrder, Quantity: (current value).
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(128);
        builder.append(getClass().getSimpleName());
        builder.append(", Quantity: ");
        builder.append(myQuantity);
        return builder.toString();
    }
}
