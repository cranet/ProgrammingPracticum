/*
 * Todd Crane
 * TCSS 305
 * assignment 5 - powerpaint
 */
package tools;

import java.awt.Color;
import java.awt.Shape;

/**
 * A shape class that contains all attributes of a drawn shape.
 * 
 * @author Todd Crane
 * @version 5/18/2016
 *
 */
public class PaintShape {
    
    /** The shape to be drawn. */
    private final Shape myShape;
    
    /** The color of the shape. */
    private final Color myColor;
    
    /** The stroke width of the shape. */
    private final int myStroke;
    
    /** Whether or not the shape is filled. */
    private final boolean myFill;
    
    /** The fill color. */
    private final Color myFillColor;
    
    
    /**
     * Creates the shape.
     * 
     * @param theShape the shape type.
     * @param theColor the shape's draw color.
     * @param theStroke the shape's stroke width.
     * @param theFill whether or not the shape is filled.
     * @param theFillColor the shape's fill color.
     */
    public PaintShape(final Shape theShape, final Color theColor,
                      final int theStroke, final boolean theFill,
                      final Color theFillColor) {
        myShape = theShape;
        myColor = theColor;
        myStroke = theStroke;
        myFill = theFill;
        myFillColor = theFillColor;
    }
    
   /**
    * @return the current shape.
    */
    public Shape getShape() {
        return myShape;
    }
    
    /**
     * @return the shape's color.
     */
    public Color getColor() {
        return myColor;
    }
    
    /**
     * @return the shape's stroke width.
     */
    public int getStroke() {
        return myStroke;
    }
    
    /**
     * @return whether or not the shape is filled.
     */
    public boolean getFill() {
        return myFill;
    }
    
    /**
     * @return the shape's fill color.
     */
    public Color getFillColor() {
        return myFillColor;
    }

}
