/* 
 * Todd Crane
 * TCSS 305
 * assignment 5 - powerpaint
 * 
 */
package view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.Icon;

/**
 * Class to draw the draw and fill color icons.
 * 
 * @author Todd Crane
 * @version 5/18/2016
 *
 */
public class ColorIcon implements Icon {
    
    /** The size of the icons. */
    private static final int SIZE = 14;
    
    /** The x coordinate of the icon. */
    private static final int X = 5;
    
    /** The y coordinate of the icon. */
    private static final int Y = 3;
    
    /** The color of the icon. */
    private Color myColor;
    
    /**
     * Constructs the icon. 
     * 
     * @param theColor the color of the icon.
     */
    public ColorIcon(final Color theColor) {
        myColor = theColor;
    }

    @Override
    public void paintIcon(final Component theComponent, 
                          final Graphics theGraphics, final int theX, 
                          final int theY) {
        
        final Graphics2D g2d = (Graphics2D) theGraphics.create();
        g2d.setPaint(myColor);
        g2d.fillRect(X, Y, SIZE, SIZE);
        
        g2d.dispose();
    }
    
    /**
     * Sets the color of the icon.
     * @param theColor the new color of the icon.
     */
    public void setColor(final Color theColor) {
        myColor = theColor;
    }
    
    @Override
    public int getIconHeight() {
        return SIZE;
    }

    @Override
    public int getIconWidth() {
        return SIZE;
    }

}
