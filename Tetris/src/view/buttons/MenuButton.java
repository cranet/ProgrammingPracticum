/*
 * Todd Crane
 * TCSS 305
 * assignment 6 - tetris
 * 
 */
package view.buttons;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;

/**
 * Class for creating custom menu buttons. 
 * 
 * @author Todd Crane
 * @version 6/4/2016
 *
 */
public class MenuButton extends JComponent {
    
    /** Generated serial ID. */
    private static final long serialVersionUID = -3673480702427051100L;

    /** Original size. */
    private static final Dimension ORIG_SIZE = new Dimension(2, 1);
    
    /** Start string for firing property events. */
    private static final String START = "START";
    
    /** End string for firing property events. */
    private static final String END = "END";
    
    /** Help string for firing property events. */
    private static final String HELP = "HELP";
    
    /** Grid string for firing property events. */
    private static final String GRID = "GRID";
    
    /** Padding for the button area. */
    private static final int AREA_PADDING = 10;
    
    /** Value used to properly center text. */
    private static final int CENTER = 5;
    
    /** Medium scale multiplier. */
    private static final int M = 25;
    
    /** The active button area size. */
    private final Dimension myButtonAreaSize;
    
    /** The button outline. */
    private final Rectangle2D myRect;
    
    /** The button label. */
    private String myLabel; 
    
    /** Whether or not the mouse has entered the button space. */
    private boolean myEnter;
    
    /**
     * Class for creating custom menu buttons. 
     *  
     * @param theLabel the button label.
     */
    public MenuButton(final String theLabel) {
        super();
        myLabel = theLabel;
        myButtonAreaSize  = new Dimension(ORIG_SIZE.width * M + AREA_PADDING,
                                        ORIG_SIZE.height * M + AREA_PADDING);
        myRect = new Rectangle(0, 0, ORIG_SIZE.width * M, ORIG_SIZE.height * M);
        
        setupButton();
    }
    
    /** Sets up each button. */
    private void setupButton() {
        setPreferredSize(myButtonAreaSize);
        enableInputMethods(true);
        setFocusable(true);
        addMouseListener(new MouseListener()); 
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setPaint(Color.WHITE);
        
        //Mouse over color change
        if (myEnter) {
            g2d.setPaint(Color.LIGHT_GRAY);
        }
        
        //Draw the button outline.
        g2d.draw(myRect);
        
        //Draw the button label.
        //g2d.setFont(myFont);
        g2d.drawString(myLabel, (int) myRect.getCenterX() 
                       - AREA_PADDING - CENTER, 
                       (int) myRect.getCenterY() + CENTER);

    }
    
    /**
     * Sets the label of the button.
     * 
     * @param theLabel the text label to draw.
     */
    public void setLabel(final String theLabel) {
        myLabel = theLabel;
        repaint();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return myButtonAreaSize;
    }
    
    @Override
    public Dimension getMinimumSize() {
        return myButtonAreaSize;
    }
    
    @Override
    public Dimension getMaximumSize() {
        return myButtonAreaSize;
    }
    
    /** Private class for mouse events. */
    private final class MouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(final MouseEvent theEvent) {
            if (START.equals(myLabel)) {
                firePropertyChange(START, null, START);
            } else if (END.equals(myLabel)) {
                firePropertyChange(END, null, END);
            } else if (HELP.equals(myLabel)) {
                firePropertyChange(HELP, null, HELP);
            } else if (GRID.equals(myLabel)) {
                firePropertyChange(GRID, null, GRID);
            }
        }
        
        @Override
        public void mouseEntered(final MouseEvent theEvent) {
            myEnter = true;
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            repaint();
        }
        
        @Override
        public void mouseExited(final MouseEvent theEvent) {
            myEnter = false;
            repaint();
        }
    }

}
