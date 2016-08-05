/*
 * Todd Crane
 * TCSS 305
 * assignment 5 - powerpaint
 */
package tools;

import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Line2D;

/**
 * A tool for drawing lines.
 * 
 * @author Todd Crane
 * @version 5/18/2016
 */
public class LineTool extends AbstractPaintTool {
    
    /** The shape type. */
    private Line2D myLine;
    
    /** The initial starting point for the shape. */
    private Point myStartPoint;
    
    /** The end point for the shape. */
    private Point myEndPoint;
    
    /** Whether or not this shape can fill. */
    private boolean myCanFill;
    
    /**
     * Constructs a line.
     */
    public LineTool() {
        super();
        myLine = new Line2D.Double();
        myCanFill = false;
    }

    @Override
    public Shape getShape() {
        return myLine;
    }
    
    @Override
    public Shape setStartPoint(final Point thePoint) {
        myStartPoint = (Point) thePoint.clone();
        myLine = new Line2D.Double(myStartPoint, myStartPoint);
        return myLine;
    }
    
    @Override
    public Shape setEndPoint(final Point thePoint) {
        myEndPoint = (Point) thePoint.clone();
        myLine.setLine(myStartPoint, myEndPoint);
        return myLine;
    }
    
    @Override
    public boolean canFill() {
        return myCanFill;
    }

}
