/*
 * Todd Crane
 * TCSS 305
 * assignment 5 - powerpaint
 */
package tools;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

/**
 * A tool for drawing rectangles. 
 * @author Todd Crane
 * @version 5/18/2016
 */
public class RectangleTool extends AbstractPaintTool {
    
    /** The shape type. */
    private Rectangle2D myRectangle;
    
    /** The initial starting point for the shape. */
    private Point myStartPoint;
    
    /** The end point for the shape. */
    private Point myEndPoint;
    
    /** Whether or not this shape can fill. */
    private boolean myCanFill;
    
    /**
     * Constructs a rectangle.
     */
    public RectangleTool() {
        super();
        myRectangle = new Rectangle2D.Double();
        myCanFill = true;
    }

    @Override
    public Shape getShape() {
        return myRectangle;
    }
    
    @Override
    public Shape setStartPoint(final Point thePoint) {
        myStartPoint = (Point) thePoint.clone();
        final Rectangle rectangle = new Rectangle(myStartPoint);
        myRectangle = rectangle;
        return myRectangle;
    }
    
    @Override
    public Shape setEndPoint(final Point thePoint) {
        myEndPoint = (Point) thePoint.clone();
        myRectangle.setFrameFromDiagonal(myStartPoint, myEndPoint);
        return myRectangle;
    }
    
    @Override
    public boolean canFill() {
        return myCanFill;
    }

}
