/*
 * TCSS 305 - Assignment 3: SnapShop
 */

package image;

/**
 * Represents a pixel (an RGB value).
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman
 * @author Alan Fowler
 * @version 1.1
 */
public class Pixel {
    // Static Fields

    /**
     * The number of color channels.
     */
    public static final int NUM_CHANNELS = 3;

    /**
     * The minimum value for a color.
     */
    public static final int MIN_COLOR_VALUE = 0;

    /**
     * The maximum value for a color.
     */
    public static final int MAX_COLOR_VALUE = 255;

    // Instance Fields

    /**
     * The red value for this pixel.
     */
    private int myRed;

    /**
     * The green value for this pixel.
     */
    private int myGreen;

    /**
     * The blue value for this pixel.
     */
    private int myBlue;

    // Constructors

    /**
     * Constructs a black pixel (all values at minimum).
     */
    public Pixel() {
        this(MIN_COLOR_VALUE, MIN_COLOR_VALUE, MIN_COLOR_VALUE);
    }

    /**
     * Constructs a pixel with the specified color values.
     * 
     * @param theRed The red value.
     * @param theGreen The green value.
     * @param theBlue The blue value.
     */
    public Pixel(final int theRed, final int theGreen, final int theBlue) {
        myRed = theRed;
        myGreen = theGreen;
        myBlue = theBlue;
    }

    // Instance Methods

    /**
     * Returns the red value for this pixel.
     * 
     * @return the red value for this pixel.
     */
    public int getRed() {
        return myRed;
    }

    /**
     * Returns the green value for this pixel.
     * 
     * @return the green value for this pixel.
     */
    public int getGreen() {
        return myGreen;
    }

    /**
     * Returns the blue value for this pixel.
     * 
     * @return the blue value for this pixel.
     */
    public int getBlue() {
        return myBlue;
    }

    /**
     * Sets the red value for this pixel to the specified value.
     * 
     * @param theRed The new value.
     * @exception IllegalArgumentException if the specified value is less than
     *                MIN_COLOR_VALUE or greater than MAX_COLOR_VALUE.
     */
    public void setRed(final int theRed) throws IllegalArgumentException {
        checkColorValue(theRed);
        myRed = theRed;
    }

    /**
     * Sets the green value for this pixel to the specified value.
     * 
     * @param theGreen The new value.
     * @exception IllegalArgumentException if the specified value is less than
     *                MIN_COLOR_VALUE or greater than MAX_COLOR_VALUE.
     */
    public void setGreen(final int theGreen) throws IllegalArgumentException {
        checkColorValue(theGreen);
        myGreen = theGreen;
    }

    /**
     * Sets the blue value for this pixel to the specified value.
     * 
     * @param theBlue The new value.
     * @exception IllegalArgumentException if the specified value is less than
     *                MIN_COLOR_VALUE or greater than MAX_COLOR_VALUE.
     */
    public void setBlue(final int theBlue) throws IllegalArgumentException {
        checkColorValue(theBlue);
        myBlue = theBlue;
    }

    /**
     * Checks to see if the specified color value is valid.
     * 
     * @param theValue The value.
     * @exception IllegalArgumentException if the specified value is less than
     *                MIN_COLOR_VALUE or greater than MAX_COLOR_VALUE.
     */
    private void checkColorValue(final int theValue) throws IllegalArgumentException {
        if (theValue < MIN_COLOR_VALUE || theValue > MAX_COLOR_VALUE) {
            throw new IllegalArgumentException("Invalid color value: " + theValue);
        }
    }
}
