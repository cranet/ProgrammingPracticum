/*
 * Todd Crane
 * TCSS 305
 * assignment 5 - powerpaint
 */
package tools;

import java.awt.Point;
import java.awt.Shape;

/**
 * An interface for paint tools. 
 * 
 * @author Todd Crane
 * @version 5/18/2016
 *
 */
public interface PaintTool {
    
    /**
     * Returns the Shape type that this tool draws.
     * 
     * @return the shape.
     */
    Shape getShape();
    

    /**
     * Sets the starting point for the shape.
     * 
     * @param thePoint new start point for the shape.
     * @return the shape with new points.
     */
    Shape setStartPoint(Point thePoint);
    
    /**
     * Sets the ending point for the shape.
     * 
     * @param thePoint new end point for the shape.
     * @return the shape.
     */
    Shape setEndPoint(Point thePoint);
    
    /**
     * Sets whether or not this shape can fill.
     * @return if the shape can fill.
     */
    boolean canFill();
    
}
