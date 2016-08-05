/*
 * Todd Crane
 * TCSS 305
 * assignment 6 - tetris
 *  
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import view.buttons.BoardSizeButton;

/**
 * Panel for displaying the board size options.
 * 
 * @author Todd Crane
 * @version 6/4/2016
 *
 */
public class BoardSizePanel extends JPanel implements PropertyChangeListener {
    
    /** Generated serial ID. */
    private static final long serialVersionUID = 2360480136694748289L;

    /** Original size. */
    private static final Dimension ORIG_SIZE = new Dimension(5, 3);
    
    /** Size one. */
    private static final Dimension SIZE_1 = new Dimension(5, 10);
    
    /** Size two. */
    private static final Dimension SIZE_2 = new Dimension(10, 20);
    
    /** Size three. */
    private static final Dimension SIZE_3 = new Dimension(25, 50);
    
    /** String for property change. */
    private static final String BOARD_SIZE = "BOARDSIZE";
    
    /** Board size option 1. */
    private static final String STRING_1 = "5 x 10";
    
    /** Board size option 2. */
    private static final String STRING_2 = "10 x 20";
    
    /** Board size option 3. */
    private static final String STRING_3 = "25 x 50";
    
    /** Medium scale multiplier. */
    private static final int M = 25;
    
    /** The panel size. */
    private final Dimension myPanelSize;
    
    /** Constructs the panel. */
    public BoardSizePanel() {
        super();
        myPanelSize = new Dimension(ORIG_SIZE.width * M, ORIG_SIZE.height * M);
        
        setupPanel();
    }
    
    /** Sets up the panel. */
    private void setupPanel() {
        setPreferredSize(myPanelSize);
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(Color.DARK_GRAY);
        
        //Create the buttons.
        createButtons();
    }
    
    /** Creates the buttons. */
    private void createButtons() {
        
        final BoardSizeButton a = new BoardSizeButton(STRING_1);
        final BoardSizeButton b = new BoardSizeButton(STRING_2);
        final BoardSizeButton c = new BoardSizeButton(STRING_3);
        
        //Add the listeners.
        a.addPropertyChangeListener(this);
        b.addPropertyChangeListener(this);
        c.addPropertyChangeListener(this);
        
        //Add to panel.
        add(a);
        add(b);
        add(c);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if ("START".equals(theEvent.getPropertyName())) {
            if (STRING_1.equals(theEvent.getNewValue())) {
                firePropertyChange(BOARD_SIZE, null, SIZE_1); 
            } else if (STRING_2.equals(theEvent.getNewValue())) {
                firePropertyChange(BOARD_SIZE, null, SIZE_2); 
            } else if (STRING_3.equals(theEvent.getNewValue())) {
                firePropertyChange(BOARD_SIZE, null, SIZE_3); 
            } 
        }

        
    }

}
