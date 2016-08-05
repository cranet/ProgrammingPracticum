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
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;



/**
 * Class for creating custom board size buttons.
 * 
 * @author Todd Crane
 * @version 6/4/2016
 *
 */
public class BoardSizeButton extends JComponent {
    
    /** Generated serial ID. */
    private static final long serialVersionUID = 5311481982535136683L;

    /** Original size. */
    private static final Dimension ORIG_SIZE = new Dimension(3, 2);
    
    /** String for property events. */
    private static final String START = "START";
    
    /** Board size option 1. */
    private static final String STRING_1 = "5 x 10";
    
    /** Board size option 2. */
    private static final String STRING_2 = "10 x 20";
    
    /** Board size option 3. */
    private static final String STRING_3 = "25 x 50";
    
    /** Original font size. */
    private static final int FONT_SIZE = 12;
    
    /** Padding for the button area. */
    private static final int AREA_PADDING = 20;
    
    /** Value used to properly center text. */
    private static final int CENTER = 20;
    
    /** Medium scale multiplier. */
    private static final int M = 25;
    
    /** The active button area size. */
    private final Dimension myButtonAreaSize;
    
    /** The button outline. */
    private final Rectangle2D myRect;
    
    /** The button label. */
    private final String myLabel;
    
    /** The font. */
    private final Font myFont;
    
    /** Whether or not the mouse has entered the button space. */
    private boolean myEnter;
    
    /**
     * Constructs a board size button. 
     * 
     * @param theLabel the button label.
     */
    public BoardSizeButton(final String theLabel) {
        super();
        myLabel = theLabel;
        myButtonAreaSize  = new Dimension(ORIG_SIZE.width * M + AREA_PADDING,
                                        ORIG_SIZE.height * M + AREA_PADDING);
        myRect = new Rectangle(0, 0, ORIG_SIZE.width * M, ORIG_SIZE.height * M);
        myFont = new Font("Font", Font.PLAIN, FONT_SIZE);
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
        g2d.setFont(myFont);
        g2d.drawString(myLabel, (int) myRect.getCenterX() - CENTER, 
                       (int) myRect.getCenterY() + 2);

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
            if (STRING_1.equals(myLabel)) {
                firePropertyChange(START, null, STRING_1);
            } else if (STRING_2.equals(myLabel)) {
                firePropertyChange(START, null, STRING_2);
            } else if (STRING_3.equals(myLabel)) {
                firePropertyChange(START, null, STRING_3);
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
