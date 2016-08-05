/*
 * Todd Crane
 * TCSS 305
 * assignment 5 - powerpaint
 */
package tools;

import java.awt.Point;

/**
 * An abstract class for drawing tools.
 * 
 * @author Todd Crane
 * @version 5/18/2016
 *
 */
public abstract class AbstractPaintTool implements PaintTool {

    /** A point outside the drawing area. */
    public static final Point NO_POINT = new Point(-50, -50);
    
    /** The initial starting point for the shape. */
    private final Point myStartPoint;
    
    /** The end point for the shape. */
    private final Point myEndPoint;

    /** Constructs a paint tool. */
    public AbstractPaintTool() {
        myStartPoint = NO_POINT;
        myEndPoint = NO_POINT;
    }
    
    /**
     * @return the start point for this tool.
     */
    protected Point getStartPoint() {
        return myStartPoint;
    }
    
    /**
     * @return the end point for this tool.
     */
    protected Point getEndPoint() {
        return myEndPoint;
    }

}
