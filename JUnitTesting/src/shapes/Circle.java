package shapes;
/*
 * TCSS 305
 * Assignment 1 - Testing
 */

import java.awt.Color;
import java.awt.Point;
import java.util.Objects;

/**
 * Represents a circle.
 * 
 * <p>Invariant:
 * <br>myRadius must be greater than zero
 * <br>AND myCenter must not be null
 * <br>AND myColor must not be null</p>
 * 
 * @author Alan Fowler acfowler@uw.edu
 * @version 2.0
 * 
 * Error was found in the calculateArea method
 * and corrected on 4/7/2016 by Todd Crane.
 * 
 * Previous calculation: 2 * myRadius * Math.pi
 * Fixed calculation: Math.pow(myRadius, 2) * Math.pi
 * 
 */
public class Circle {

    // class constants

    /**
     * A default radius to use when no other value is specified - (1.0 - a unit circle).
     */
    private static final double DEFAULT_RADIUS = 1.0; // unit circle

    /**
     * A default Point for the center of a Circle - (0, 0) the coordinate system origin.
     */
    private static final Point DEFAULT_CENTER = new Point(0, 0); // origin

    /**
     * A default Color to use when no other Color is specified - Color.BLACK.
     */
    private static final Color DEFAULT_COLOR = Color.BLACK;

    // instance fields

    /**
     * The radius of this Circle.
     */
    private double myRadius;

    /**
     * The center of this Circle.
     */
    private Point myCenter; // Note that java.awt.Point is mutable

    /**
     * The color of this Circle.
     */
    private Color myColor;

    // constructors

    /**
     * Constructs a Circle with the specified radius, location, and color.
     * 
     * <p>Precondition: The parameters must not violate the class invariant.
     * That is, theRadius must be greater than zero
     *      AND theCenter must not be null
     *      AND theColor must not be null</p>
     * 
     * @param theRadius the radius to assign to this Circle
     * @param theCenter the center point to assign to this Circle
     * @param theColor the color to assign to this Circle
     * @throws IllegalArgumentException if theRadius is less than or equal to zero
     * @throws NullPointerException if theCenter is null OR theColor is null
     */
    public Circle(final double theRadius,
                  final Point theCenter,
                  final Color theColor) {

        if (theRadius <= 0) {
            throw new IllegalArgumentException("The radius must be a positive value!");
        }

        myRadius = theRadius;
        
        // make a defensive copy of the mutable Point parameter
        // to avoid an encapsulation violation
        myCenter = (Point) Objects.requireNonNull(theCenter).clone();
        
        /* 
         * Note that the Objects class and requireNonNull() were added to Java in version 7.
         * The method will throw an exception if the parameter passed to the method is null.
         * This simplifies the code by eliminating the need for an if / else test
         * and an explicit throws statement.
         */
        myColor = Objects.requireNonNull(theColor);
    }

    /**
     * Constructs a black unit circle centered at the origin.
     */
    public Circle() {
        this(DEFAULT_RADIUS, DEFAULT_CENTER, DEFAULT_COLOR);
    }

    // instance methods (commands)

    /**
     * Sets the radius of this Circle to the specified value.
     * 
     * <p>Precondition: theRadius must be greater than zero</p>
     * <p>Postcondition: this Circle will be assigned the specified radius</p>
     * 
     * @param theRadius the radius value to assign to this Circle
     * @throws IllegalArgumentException if theRadius is less than or equal to zero
     */
    public final void setRadius(final double theRadius) {

        if (theRadius <= 0) {
            throw new IllegalArgumentException();
        }
        myRadius = theRadius;
    }

    /**
     * Sets the location of the center of this Circle to the specified point.
     * 
     * <p>Precondition: thePoint must not be null</p>
     * <p>Postcondition: this Circle will be assigned the specified center Point</p>
     * 
     * @param thePoint the center value to assign to this Circle
     * @throws NullPointerException if thePoint is null
     */
    public final void setCenter(final Point thePoint) {
        
        // make a defensive copy of the mutable Point parameter
        // to avoid an encapsulation violation
        myCenter = (Point) Objects.requireNonNull(thePoint).clone();
    }

    /**
     * Sets the Color of this Circle to the specified value.
     * 
     * <p>Precondition: theColor must not be null</p>
     * <p>Postcondition: this Circle will be assigned the specified Color</p>
     * 
     * @param theColor the Color value to assign to this Circle
     * @throws NullPointerException if theColor is null
     */
    public final void setColor(final Color theColor) {

        myColor = Objects.requireNonNull(theColor);
    }

    // instance methods (queries)

    /**
     * Returns the radius of this Circle.
     * 
     * @return the radius of this Circle
     */
    public final double getRadius() {
        return myRadius;
    }

    /**
     * Returns the center Point of this Circle.
     * 
     * @return the center Point of this Circle
     */
    public final Point getCenter() {

        // return a defensive copy of the mutable Point field
        // to avoid an encapsulation violation
        return (Point) myCenter.clone();
    }

    /**
     * Returns the Color of this Circle.
     * 
     * @return the Color of this Circle
     */
    public final Color getColor() {
        return myColor;
    }

    /**
     * Calculates and returns the diameter of this Circle.
     * 
     * @return The diameter of this Circle
     */
    public double calculateDiameter() {
        return 2 * myRadius;
    }

    /**
     * Calculates and returns the circumference of this Circle.
     * 
     * @return the circumference of this Circle
     */
    public double calculateCircumference() {
        return Math.PI * calculateDiameter();
    }

    /**
     * Calculates and returns the area of this Circle.
     * 
     * @return the area of this Circle
     */
    
    public double calculateArea() {
        return Math.pow(myRadius, 2) * Math.PI;
    }
    
    // overridden methods from class Object

    /**
     * {@inheritDoc}
     * 
     * The String representation of this Circle will be formatted as follows:
     * <br>Circle [radius=(current value), center=(current value), color=(current value)].
     */
    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder(128); // default initial size = 16
        builder.append(getClass().getSimpleName()); // the class name without the package name
        builder.append(" [radius=");
        builder.append(String.format("%.2f", myRadius));
        builder.append(", center=");
        builder.append(myCenter);
        builder.append(", color=");
        builder.append(myColor);
        builder.append(']');
        return builder.toString();
    }

}
