/*
 * TCSS 305
 * Spring 2016
 * assignment 2 - shoppingcart
 */
package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Objects;

/**
 * An Item class that stores information about an individual item.
 * 
 * @author Todd Crane
 * @version 4/16/2016
 *
 */
public final class Item {
    // Fields

    /**
     * The name of this item.
     */
    private final String myName;
    
    /**
     * The price of this item.
     */
    private final BigDecimal myPrice;
    
    /**
     * The bulk quantity for this item.
     */
    private int myBulkQuantity;
    
    /**
     * The bulk price for this item.
     */
    private BigDecimal myBulkPrice;

    /**
     * Whether or not the item has the bulk option.
     */
    private final boolean myBulkOption;
 
    //Constructor that takes a name and a price as arguments.
    /**
     * Constructs an item that has a name and price.
     * Does not have a bulk option.
     * Allows for free item implementation.
     * 
     * @param theName the name to assign to this item.
     * @param thePrice the price to assign to this item.
     * @throws NullPointerException if the name or price is null.
     * @throws IllegalArgumentException if the price is less than zero.
     */
    public Item(final String theName, final BigDecimal thePrice) {
        myName = Objects.requireNonNull(theName);
        //Check for a price less than zero. Allows for free items (BOGO).
        if (thePrice.compareTo(BigDecimal.ZERO) == -1) {
            throw new IllegalArgumentException();
        }
        myPrice = Objects.requireNonNull(thePrice);
        myBulkOption = false; // No bulk option available.
    }

    /*
     * Constructor that takes a name, price, bulk quantity and bulk price
     * as arguments.
     */
    /**
     * Constructs an item that has a name, price, bulk quantity and price.
     * Allows for free item implementation.
     * 
     * @param theName the name to assign to this item.
     * @param thePrice the price to assign to this item.
     * @param theBulkQuantity the amount required for the bulk option.
     * @param theBulkPrice the bulk item price
     * @throws NullPointerException if the name, price, or bulk price is null.
     * @throws IllegalArgumentException if either price, bulk quantity,
     * or bulk price are less than zero.
     */
    public Item(final String theName, final BigDecimal thePrice, final int theBulkQuantity,
                final BigDecimal theBulkPrice) {
        myName = Objects.requireNonNull(theName);
        /*
         * Check for price less than zero, bulk quantity less than zero, and
         * bulk price less than zero. Allows for free pricing options.
         */
        if ((thePrice.compareTo(BigDecimal.ZERO) == -1) 
                        || (theBulkQuantity < 0) 
                        || (theBulkPrice.compareTo(BigDecimal.ZERO) == -1)) {
            throw new IllegalArgumentException();
        }
        myPrice = Objects.requireNonNull(thePrice);
        myBulkQuantity = theBulkQuantity;
        myBulkPrice = Objects.requireNonNull(theBulkPrice);
        myBulkOption = true; // Bulk option available. 
    }

    /**
     * Returns the price of this item.
     * @return the price of this item.
     */
    public BigDecimal getPrice() {
        return myPrice;
    }
    
    /**
     * Returns the bulk quantity for this item.
     * @return the bulk quantity for this item.
     */
    public int getBulkQuantity() {
        return myBulkQuantity;
    }
    
    /**
     * Returns the bulk pricing for this item.
     * @return the bulk pricing for this item.
     */
    public BigDecimal getBulkPrice() {
        return myBulkPrice;
    }

    /**
     * Returns whether or not the item has the bulk option.
     * @return the bulk option.
     */
    // Returns true if the item does have bulk option, false otherwise.
    public boolean isBulk() {
        return myBulkOption;
    }

    /**
     * {@inheritDoc}
     * 
     * The String representation of this Item will be as follows:
     * (item name), $(price, US currency format)
     * 
     * If the bulk option is present, the output will be as follows:
     * (item name), $(price, US currency format) ((bulk quantity) for 
     * $(bulk price, US currency format))
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(128);
        final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        
        builder.append(myName);
        builder.append(", ");
        builder.append(nf.format(myPrice));
        // Bulk option.
        if (isBulk()) {
            builder.append(" (");
            builder.append(myBulkQuantity);
            builder.append(" for ");
            builder.append(nf.format(myBulkPrice));
            builder.append(')');
        }
        return builder.toString();
    }

    /**
     * {@inheritDoc}
     * 
     * This method compares the name and price to determine the equality of
     * Item objects. If bulk pricing is an option, bulk price and quantity
     * are also compared. All fields must be equal for two Item objects
     * to be considered equal.
     */
    
    /*
     * (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     * 
     * Returns true if the specified Item object is equivalent to this Item
     * and false otherwise.
     * 
     * Overrides from class Object.
     */
    @Override
    public boolean equals(final Object theOther) {
        boolean returnValue = false;

        if (theOther != null && this.getClass() == theOther.getClass()) {
            final Item otherItem = (Item) theOther;
            
            returnValue = (Objects.equals(myName, otherItem.myName))
                            && (myPrice.compareTo(otherItem.myPrice) == 0);
            
            if (isBulk()) {
                returnValue = (Objects.equals(myName, otherItem.myName))
                                && (myPrice.compareTo(otherItem.myPrice) == 0)
                                && (Integer.compare(myBulkQuantity, 
                                               otherItem.myBulkQuantity) == 0)
                                && (myBulkPrice.compareTo
                                                (otherItem.myBulkPrice) == 0);
            }
        }
        return returnValue;
    }

    /**
     * {@inheritDoc}
     * 
     * Returns an integer hash code for this item.
     */
    @Override
    public int hashCode() {
        final int hash;
        if (isBulk()) {
            hash = Objects.hash(myName, myPrice);
        } else {
            hash = Objects.hash(myName, myPrice, myBulkQuantity, myBulkPrice);
        }
        return hash;
    }
}
