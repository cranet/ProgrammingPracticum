/*
 * TCSS 305
 * Spring 2016
 * assignment 2 - shoppingcart
 */
package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A shopping cart class that stores information about a customer's
 * overall purchase.
 * 
 * @author Todd Crane
 * @version 4/16/2016
 */
public class ShoppingCart {
    // Fields
    
    /** An array list to store all of the items a customer has ordered.*/
    //private final ArrayList<ItemOrder> myItemOrders;
    private final List<ItemOrder> myItemOrders;
    
    /** Used to check whether or not a customer is a store member.*/
    private boolean myIsMember;
        
    /** Constructs an empty Shopping Cart.*/
    // Constructor that creates an empty shopping cart.
    public ShoppingCart() {
        myItemOrders = new ArrayList<ItemOrder>();
    }

    /*
     * Adds an order to the shopping cart, replacing any previous order
     * for an equivalent item with the new order.
     */
    /**
     * Adds orders to the shopping cart.
     * @param theOrder the ItemOrder to be added to the shopping cart.
     * @throws NullPointerException if the ItemOrder is null.
     */
    public void add(final ItemOrder theOrder) {
        for (int i = 0; i < myItemOrders.size(); i++) {
            if (theOrder.getItem() == myItemOrders.get(i).getItem()) {
                myItemOrders.set(i, theOrder);
                return;
            }
        }
        myItemOrders.add(Objects.requireNonNull(theOrder));
    }

    /**
     * Sets whether or not a customer is a store member.
     * @param theMembership boolean value that determines membership.
     */
    public void setMembership(final boolean theMembership) {
        myIsMember = theMembership;
    }
    
    /*
     * Returns the total cost of this shopping cart as a BigDecimal. 
     * This returned BigDecimal has  scale of 2 and uses the 
     * ROUND_HALF_EVEN rounding rule.
     */
    /**
     * Calculates the total of the shopping cart.
     * @return the current total of the shopping cart.
     */
    public BigDecimal calculateTotal() {
        // BigDecimal to be returned.
        BigDecimal total = BigDecimal.ZERO;
        
        // BigDecimal used for calculations.
        BigDecimal result = BigDecimal.ZERO;

        for (int i = 0; i < myItemOrders.size(); i++) {
            // Getting all needed values as BigDecimals.
            final BigDecimal q = new BigDecimal(myItemOrders.get(i).
                                                getQuantity());
            final BigDecimal bQ = new BigDecimal(myItemOrders.get(i).getItem().
                                           getBulkQuantity());
            final BigDecimal p = myItemOrders.get(i).getItem().getPrice();
            final BigDecimal bP = myItemOrders.get(i).getItem().getBulkPrice();
            
            // Check for membership and if the item has the bulk option.
            if (myIsMember 
                            && (myItemOrders.get(i).getItem().isBulk())) {
                /*
                 * Calculates the price of bulk items by dividing the quantity
                 * by the bulk quantity. Value will always be rounded down to
                 * the nearest integer.
                 * Example: 19/6 = 3 
                 */
                result = q.divideToIntegralValue(bQ); // Divide.
                result = result.multiply(bP); // Multiply result by bulk price.
                
                /*
                 * Calculates the remaining items left over after bulk pricing
                 * has been applied using BigDecimal remainder. Multiplies 
                 * result by regular pricing.
                 * Example: 2 remainder 6 = 2
                 */
                result = result.add((q.remainder(bQ)).multiply(p));
                total = total.add(result);
                
            } else {
                result = p.multiply(q); // Multiply price and quantity.
                total = total.add(result);
            }
        }
        return total.setScale(2, BigDecimal.ROUND_HALF_EVEN);
    }
    
    /**
     * Removes all items from the cart.
     */
    public void clear() {
        myItemOrders.clear();
    }

    /**
     * {@inheritDoc}
     * 
     * The String representation of this Shopping Cart will be as follows:
     * ShoppingCart, Items currently in the shopping cart: (current items)
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(128);
        builder.append(getClass().getSimpleName());
        builder.append(", Items currently in the shopping cart: ");
        builder.append(myItemOrders.toString());
        return builder.toString();
    }
}
