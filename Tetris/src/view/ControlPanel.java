/*
 * Todd Crane
 * TCSS 305
 * assignment 6 -tetris
 * 
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BoxLayout;
import javax.swing.InputMap;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

import model.Board;
import view.buttons.ControlButton;

/**
 * Panel that handles the controls.
 * 
 * @author Todd Crane
 * @version 6/4/2016
 *
 */
public class ControlPanel extends JPanel {
    
    /** Generated serial ID. */
    private static final long serialVersionUID = -4189093573889792440L;

    /** Original size. */
    private static final Dimension ORIG_SIZE = new Dimension(5, 15);
    
    /** Medium scale multiplier. */
    private static final int M = 25;
    
    /** The identifier for moving right. */
    private static final String RIGHT = "RIGHT";
    
    /** The identifier for moving down. */
    private static final String DOWN = "DOWN";
   
    /** The identifier for moving left. */
    private static final String LEFT = "LEFT";
    
    /** The identifier for rotating clockwise. */
    private static final String CW = "CW";
    
    /** The identifier for rotating CCW. */
    private static final String CCW = "CCW";
    
    /** The identifier for dropping. */
    private static final String DROP = "DROP";
    
    /** The identifier for pausing. */
    private static final String PAUSE = "PAUSE";
    
    /** The identifier for un-binding keys. */
    private static final String NONE = "NONE";
    
    /** The game board. */
    private Board myBoard;
    
    /** The timer. */
    private final Timer myTimer;
    
    /** The input map. */
    private final InputMap myIM;
    
    /** The action map. */
    private final ActionMap myAM;
    
    /** The panel size. */
    private final Dimension myPanelSize;
    
    /** Flag for pausing. */
    private boolean myFlag;
    
    
    /**
     * Constructs a panel for control buttons.
     * 
     * @param theBoard the game board.
     * @param theTimer the game timer.
     */
    public ControlPanel(final Board theBoard, final Timer theTimer) {
        super();
        myBoard = theBoard;
        myTimer = theTimer;
        myPanelSize = new Dimension(ORIG_SIZE.width * M, ORIG_SIZE.height * M);
        myIM = this.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
        myAM = this.getActionMap();        

        setupPanel();
    }
    
    /** Sets up the panel. */
    private void setupPanel() {
        setPreferredSize(myPanelSize);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.DARK_GRAY);
        
    }
    
    /** Sets up the input map. */
    public void setupInputMap() {
        myIM.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0), RIGHT);
        myIM.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0), DOWN);
        myIM.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), LEFT);
        myIM.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0), CW);
        //myIM.put(KeyStroke.getKeyStroke(KeyEvent.VK_C, 0), CW);
        myIM.put(KeyStroke.getKeyStroke(KeyEvent.VK_V, 0), CCW);
        myIM.put(KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0), DROP);
        myIM.put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), PAUSE);
    }
    
    /** Sets up the action map. */
    public void setupActionMap() {
        myAM.put(RIGHT, new KeyAction(RIGHT));
        myAM.put(DOWN, new KeyAction(DOWN));
        myAM.put(LEFT, new KeyAction(LEFT));
        myAM.put(CW, new KeyAction(CW));
        myAM.put(CCW, new KeyAction(CCW));
        myAM.put(DROP, new KeyAction(DROP));
        myAM.put(PAUSE, new KeyAction(PAUSE));
    }
    
    /** Creates the control buttons. */
    public void createControlButtons() {

        //Get the keys from both maps. 
        final KeyStroke[] im = myIM.allKeys();
        final Object[] am = myAM.allKeys();
        
        for (int i = 0; i < im.length; i++) {

            final String key = (String) am[i];
            final int keyCode = im[i].getKeyCode();
            String keyText = KeyEvent.getKeyText(keyCode);
            
            //Grab arrow symbols
            if ("Up".equals(keyText)) {
                keyText = "\u2191";
            } else if ("Right".equals(keyText)) {
                keyText = "\u2192";
            } else if ("Down".equals(keyText)) {
                keyText = "\u2193";
            } else if ("Left".equals(keyText)) {
                keyText = "\u2190";
            } else if ("Space".equals(keyText)) {
                keyText = "\u25AC";
            }
            
            add(new ControlButton(keyText, key));
        }
    }
    
    /**
     * Methods for re-binding a key (customization of keys).
     * 
     * @param theEvent the key event.
     * @param theOldKey the old key.
     */
    public void rebind(final KeyEvent theEvent, final String theOldKey) {
        myIM.remove(KeyStroke.getKeyStroke(theOldKey));
        myIM.put(KeyStroke.getKeyStrokeForEvent(theEvent), 
                 myIM.get(KeyStroke.getKeyStroke(theOldKey)));
    }
    
    /** Un-binds all keys except for pause. */
    public void unbind() {
        final KeyStroke[] keys = myIM.keys();
        //Ignore the pause key (last key).
        for (int i = 0; i < myIM.size() - 1; i++) {
            myIM.put(keys[i], NONE);
        }
    }
    
    /**
     * Sets the board.
     * @param theBoard the game board.
     */
    public void setBoard(final Board theBoard) {
        myBoard = theBoard;
    }
    
    /**
     * Sets a flag to true if the game is running.
     * 
     * @param theFlag flag for if a game is running.
     */
    public void setFlag(final boolean theFlag) {
        myFlag = theFlag;
    }
    
    @Override
    public Dimension getPreferredSize() {
        return myPanelSize;
    }
    
    @Override
    public Dimension getMinimumSize() {
        return myPanelSize;
    }
    
    @Override
    public Dimension getMaximumSize() {
        return myPanelSize;
    }
    
    /** Private class for key events. */
    private final class KeyAction extends AbstractAction {
        
        /** Generated serial ID. */
        private static final long serialVersionUID = 1217125594407398551L;
        
        /** The string of the the key being pressed. */
        private final String myKey;
        
        /**
         * Sets up the key action.
         * @param theString the string identifier.
         */
        private KeyAction(final String theString) {
            super();
            myKey = theString;
        }

        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            //Only runs actions if timer is running.
            if (myTimer.isRunning()) { 
                if (myKey.equals(RIGHT)) {
                    myBoard.right();
                } else if (myKey.equals(DOWN)) {
                    myBoard.down();
                } else if (myKey.equals(LEFT)) {
                    myBoard.left();
                } else if (myKey.equals(CW)) {
                    myBoard.rotateCW();
                } else if (myKey.equals(CCW)) {
                    myBoard.rotateCCW();
                } else if (myKey.equals(DROP)) {
                    myBoard.drop();
                } 
            }
            //Pause runs regardless.
            if (myKey.equals(PAUSE)) {
                pause();
            } 
        }
        
        /** Checks conditions for pause. */
        private void pause() {
            if (myTimer.isRunning()) {
                myTimer.stop();
                unbind();
            } else if (myFlag) {
                myTimer.start();
                setupInputMap();
            }
        }
        
    }

}
