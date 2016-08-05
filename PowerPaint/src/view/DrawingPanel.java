/*
 * Todd Crane
 * TCSS 305
 * assignment 5 - powerpaint
 */
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;

import tools.PaintShape;
import tools.PaintTool;


/**
 * A class that handles drawing shapes on the GUI.
 * 
 * @author Todd Crane
 * @version 5/18/2016
 *
 */
public class DrawingPanel extends JPanel {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = -2923094014046831255L;

    /** The default size of this panel. */
    private static final Dimension DEFAULT_SIZE = new Dimension(600, 300);
    
    /** The default UW Purple drawing color. */
    private static final Color DEFAULT_COLOR = new Color(51, 0, 111);
    
    /** The default UW Gold fill color. */
    private static final Color DEFAULT_FILL_COLOR = new Color(232, 211, 162);
    
    /** A collection of previous shapes. */
    private final List<PaintShape> myPreviousShapes;
    
    /** The paint tool currently in use. */
    private PaintTool myCurrentTool;
    
    /** The current shape. */
    private Shape myCurrentShape;
    
    /** The current color. */
    private Color myColor;
    
    /** The current stroke width. */
    private int myStroke;    
    
    /** The current fill color. */
    private Color myFillColor;
    
    /** The fill state. */
    private boolean myFill;
    
    /** The current shape state. */
    private boolean myClear;
    
    /** Initialize the panel. */
    public DrawingPanel() {
        super();
        myPreviousShapes = new ArrayList<PaintShape>();
        myColor = DEFAULT_COLOR;
        myFillColor = DEFAULT_FILL_COLOR;
        myStroke = 1;
        myClear = false;
        initializePanel();
    }
    
    /** Sets up the panel. */
    private void initializePanel() {
        setPreferredSize(DEFAULT_SIZE);
        setBackground(Color.WHITE);
        
        //Setup the mouse listener.
        final MouseInputAdapter mouseHandler = new MyMouseHandler();
        addMouseListener(mouseHandler);
        addMouseMotionListener(mouseHandler);
    }
    
    /**
     * Sets the current shape.
     * @param theShape the current shape.
     */
    public void setShape(final Shape theShape) {
        myCurrentShape = theShape;
    }
    
    /**
     * Saves a completed shape.
     * @param theShape the shape to be saved.
     */
    public void saveShape(final Shape theShape) {
        //Prevent a true passing for shapes that shouldn't fill.
        if (!myCurrentTool.canFill()) {
            myFill = false;
        }
        myPreviousShapes.add(new PaintShape(theShape, myColor, myStroke,
                                            myFill, myFillColor));
        repaint();
    }
    
    /**
     * Sets the current paint tool.
     * @param theTool the current paint tool.
     */
    public void setCurrentTool(final PaintTool theTool) {
        myCurrentTool = theTool;
    }
    
    /**
     * Sets the current color. 
     * @param theColor the current draw color.
     */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }
    
    /**
     * Sets the stroke width.
     * @param theStroke the stroke width.
     */
    public void setStroke(final int theStroke) {
        myStroke = theStroke;
    }
    
    /**
     * Sets the fill color. 
     * @param theColor the fill color.
     */
    public void setFillColor(final Color theColor) {
        myFillColor = theColor;
    }
    
    /** 
     * Sets if fill is checked or not.
     * @param theBool the fill state.
     */
    public void setFill(final boolean theBool) {
        myFill = theBool; 
    }
    
    /** Clears the drawing panel. */
    public void clear() {
        myPreviousShapes.clear();
        myClear = true;
        repaint();
    }
    
    /**
     * 
     * @return The current draw color.
     */
    public Color getCurrentColor() {
        return myColor;
    }
    
    /**
     * 
     * @return The current fill color.
     */
    public Color getCurrentFillColor() {
        return myFillColor;
    }
  
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        //Only draw if clear has not been pressed.
        if (!myClear) {
            g2d.setStroke(new BasicStroke(myStroke));
            g2d.setPaint(myColor);
            g2d.draw(myCurrentTool.getShape());
            //Check for fill.
            if (myFill && myCurrentTool.canFill()) {
                g2d.setPaint(myFillColor);
                g2d.fill(myCurrentShape);
            }
        }
        
        //Draw previous shapes.
        for (final PaintShape p : myPreviousShapes) {
            g2d.setPaint(p.getColor());
            g2d.setStroke(new BasicStroke(p.getStroke()));
            g2d.draw(p.getShape());
            //Check for fill.
            if (p.getFill()) {
                g2d.setPaint(p.getFillColor());
                g2d.fill(p.getShape());
            }
        }
    }
    
    /** Listens for mouse events. */
    private class MyMouseHandler extends MouseInputAdapter {
        
        @Override
        public void mousePressed(final MouseEvent theEvent) {
            myCurrentShape = myCurrentTool.setStartPoint(theEvent.getPoint());
            myClear = false;
        }
        
        @Override
        public void mouseDragged(final MouseEvent theEvent) {
            //Prevent drawing when stroke is zero.
            if (myStroke > 0) {
                myCurrentShape = myCurrentTool.setEndPoint(theEvent.getPoint());
                repaint();
                //Property change for the clear button.
                firePropertyChange("Draw", false, true);
            }
        }
        
        @Override
        public void mouseReleased(final MouseEvent theEvent) {
            saveShape(myCurrentShape);
        }
        
        @Override
        public void mouseEntered(final MouseEvent theEvent) {
            setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        }
    }
}
