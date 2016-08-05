/*
 * Todd Crane
 * TCSS 305
 * assignment 5 - powerpaint
 */

package tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Path2D;

/**
 * A tool for drawing paths.
 * 
 * @author Todd Crane
 * @version 5/18/2016
 */
public class PencilTool extends AbstractPaintTool {
    
    /** The shape type. */
    private Path2D myPencil;
    
    /** The initial starting point for the shape. */
    private Point myStartPoint;
    
    /** The end point for the shape. */
    private Point myEndPoint;
    
    /** Whether or not this shape can fill. */
    private boolean myCanFill;
    
    /**
     * Constructs a pencil.
     */
    public PencilTool() {
        super();
        myPencil = new Path2D.Double();
        myCanFill = false;
    }

    @Override
    public Shape getShape() {
        return myPencil;
    }
    
    @Override
    public Shape setStartPoint(final Point thePoint) {
        myStartPoint = (Point) thePoint.clone();
        myPencil = new Path2D.Double();
        myPencil.moveTo(myStartPoint.getX(), myStartPoint.getY());
        return myPencil;
    }
    
    @Override
    public Shape setEndPoint(final Point thePoint) {
        myEndPoint = (Point) thePoint.clone();
        myPencil.lineTo(myEndPoint.getX(), myEndPoint.getY());
        return myPencil;
    }
    
    @Override
    public boolean canFill() {
        return myCanFill;
    }

}
