/*
 * TCSS 305
 * 
 * An implementation of the classic game "Tetris".
 */

package model;

import java.util.Objects;

/**
 * Represents a 2D Point with x and y coordinates.
 * Point objects are immutable.
 * (Compare to java.awt.Point which are mutable)
 * 
 * @author TCSS 305 Instructors
 * @version Spring 2016
 */
public final class Point {

    /** The X coordinate. */
    private final int myX;

    /** The Y coordinate. */
    private final int myY;

    /**
     * Constructs a Point using the provided coordinates.
     * 
     * @param theX the X coordinate.
     * @param theY the Y coordinate.
     */
    public Point(final int theX, final int theY) {
        myX = theX;
        myY = theY;
    }

    // Queries
    /**
     * Returns the X coordinate.
     * 
     * @return the X coordinate of the Point.
     */
    public int x() {
        return myX;
    }

    /**
     * Returns the Y coordinate.
     * 
     * @return the Y coordinate of the Point.
     */
    public int y() {
        return myY;
    }

    /**
     * Creates a new point transformed by x and y.
     * 
     * @param theX the X factor to transform by.
     * @param theY the Y factor to transform by.
     * @return the new transformed Point.
     */
    public Point transform(final int theX, final int theY) {
        return new Point(myX + theX, myY + theY);
    }
    
    /**
     * Creates a new point transformed by another Point.
     * 
     * @param thePoint the Point to transform with.
     * @return the new transformed Point.
     */
    public Point transform(final Point thePoint) {
        return transform(thePoint.x(), thePoint.y());
    }

    // overridden methods of class Object

    @Override
    public boolean equals(final Object theOther) {
        boolean result = false;
        if (theOther == this) {
            result = true;
        } else if (theOther != null && theOther.getClass() == getClass()) {
            final Point p = (Point) theOther;
            result = myX == p.myX && myY == p.myY;
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(myX, myY);
    }

    @Override
    public String toString() {
        return String.format("(%d, %d)", myX, myY);
    }
}
