/*
 * Todd Crane
 * TCSS 305
 * assignment 5 - powerpaint
 */
package tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

/**
 * A tool for drawing an ellipse.
 * 
 * @author Todd Crane
 * @version 5/18/2016
 *
 */
public class EllipseTool extends AbstractPaintTool {
    
    /** The shape type. */
    private Ellipse2D myEllipse;
    
    /** The initial starting point for the shape. */
    private Point myStartPoint;
    
    /** The end point for the shape. */
    private Point myEndPoint;
    
    /** Whether or not this shape can fill. */
    private boolean myCanFill;
    
    /**
     * Constructs an ellipse.
     */
    public EllipseTool() {
        super();
        myEllipse = new Ellipse2D.Double();
        myCanFill = true;
    }

    @Override
    public Shape getShape() {
        return myEllipse;
    }
    
    @Override
    public Shape setStartPoint(final Point thePoint) {
        myStartPoint = (Point) thePoint.clone();
        myEllipse = new Ellipse2D.Double(myStartPoint.getX(), 
                                         myStartPoint.getY(), 0, 0);
        return myEllipse;
    }
    
    @Override
    public Shape setEndPoint(final Point thePoint) {
        myEndPoint = (Point) thePoint.clone();
        myEllipse.setFrameFromDiagonal(myStartPoint, myEndPoint);
        return myEllipse;
    }
    
    @Override
    public boolean canFill() {
        return myCanFill;
    }

}
