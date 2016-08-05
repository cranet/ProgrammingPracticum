/*
 * Todd Crane
 * TCSS 305
 * assignment 6 - tetris
 * 
 */
package view.buttons;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;

import javax.swing.JComponent;
import javax.swing.JDialog;

/**
 * Class for creating custom control buttons.
 * 
 * @author Todd Crane
 * @version 6/4/2016
 *
 */
public class ControlButton extends JComponent {
    
    /** Generated serial ID. */
    private static final long serialVersionUID = 2106367835089317093L;

    /** Original button size. */
    private static final Dimension ORIG_BUTTON_SIZE = new Dimension(1, 1);
    
    /** Original area size. */
    private static final Dimension ORIG_AREA_SIZE = new Dimension(5, 2);
    
    /** Value used to properly center text. */
    private static final int CENTER = 5;
    
    /** Medium scale multiplier. */
    private static final int M = 25;
    
    /** Value for centering key icon. */
    private static final int KEY_CENTER = 3;
    
    /** Value for centering key description. */
    private static final int KEY_DESC = 30;
    
    /** The rectangle for the button. */
    private final Rectangle2D myRect;
    
    /** The label of the button. */
    private final String myTitle;
    
    /** The display text of the button. */
    private final String myText;
    
    /** The dialog for re-binding keys. */
    private final JDialog myDialog; 
    
    /** The active button area size. */
    private final Dimension myButtonAreaSize;
    
    /** The button size. */
    private final Dimension myButtonSize;
  
    /** Whether or not the mouse has entered the button space. */
    private boolean myEnter;
    
    /**
     * Constructs a control button.
     * 
     * @param theTitle the title of the button.
     * @param theString 
     */
    public ControlButton(final String theTitle, final String theString) {
        super();
        myTitle = theString;
        myText = theTitle;
        myButtonAreaSize = new Dimension(ORIG_AREA_SIZE.width * M, 
                                         ORIG_AREA_SIZE.height * M);
        myButtonSize = new Dimension(ORIG_BUTTON_SIZE.width * M, 
                                     ORIG_BUTTON_SIZE.height * M);
        
        myRect = new Rectangle(0, 0, myButtonSize.width, myButtonSize.height);
        myDialog = new JDialog();
        
        setupButton();
    }
    
    /** Sets up each button. */
    private void setupButton() {
        
        //Setup button. 
        setPreferredSize(myButtonAreaSize);
        //enableInputMethods(true);
        //setFocusable(true);
        
        //Setup listeners.
        //addMouseListener(new MouseListener());
        
        //Set up the change key dialog.
        changeKeyDialog();
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                             RenderingHints.VALUE_ANTIALIAS_ON);

        //setDisplayText();
        g2d.setPaint(Color.WHITE);
        
        //Mouse over color change.
        if (myEnter) {
            g2d.setPaint(Color.LIGHT_GRAY);
        }
        //Draw the square.
        g2d.draw(myRect);
                
        //Draw the key letter for use as the button label.
        g2d.drawString(myText, (int) myRect.getCenterX() - KEY_CENTER, 
                       (int) myRect.getCenterY() + CENTER);
        
        //Draw the label next to the button (description).
        g2d.drawString(myTitle, (int) myRect.getCenterX() + KEY_DESC, 
                       (int) myRect.getCenterY() + CENTER);
    }
    
    /** Sets up the dialog for changing key bindings. */
    private void changeKeyDialog() {
        myDialog.setTitle("PRESS ANY KEY");
        myDialog.setMinimumSize(myButtonSize);
        myDialog.setResizable(false);
        myDialog.setLocationRelativeTo(this);
        myDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //myDialog.addKeyListener(new KeyListener());
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

}
