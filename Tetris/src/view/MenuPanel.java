/*
 * Todd Crane
 * TCSS 305
 * assignment 6 - tetris
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import view.buttons.MenuButton;

/**
 * Panel to set up the menu.
 * 
 * @author Todd Crane
 * @version 6/4/2016
 *
 */
public class MenuPanel extends JPanel implements PropertyChangeListener {
    
    /** Generated serial ID. */
    private static final long serialVersionUID = 9082764696770921834L;

    /** Original size. */
    private static final Dimension ORIG_SIZE = new Dimension(5, 5);
    
    /** Start string for firing property events. */
    private static final String START = "START";
    
    /** End string for firing property events. */
    private static final String END = "END";
    
    /** Help string for firing property events. */
    private static final String HELP = "HELP";
    
    /** Grid string for firing property events. */
    private static final String GRID = "GRID";
    
    /** Medium scale multiplier. */
    private static final int M = 25;
    
    /** The panel size. */
    private final Dimension myPanelSize;
    
    /** Choose board size dialog. */
    private final JDialog mySizeDialog;
    
    /** The board size panel. */
    private final BoardSizePanel myBoardSizePanel;
    
    /** The start and end button. */
    private MenuButton myStart;
    
    /**
     * Constructs a panel for menu buttons.
     * 
     * @param theBoardSizePanel the panel for choosing a board size.
     */
    public MenuPanel(final BoardSizePanel theBoardSizePanel) {
        super();
        myBoardSizePanel = theBoardSizePanel;
        myPanelSize = new Dimension(ORIG_SIZE.width * M, ORIG_SIZE.height * M);
        mySizeDialog = new JDialog();
        
        setupPanel();
    }
    
    /** Sets up the panel. */
    private void setupPanel() {
        setPreferredSize(myPanelSize);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.DARK_GRAY);
        
        //Set up the change key dialog.
        setupDialog();
        
        //Create the menu buttons. 
        createMenuButtons();
    }
    
    /** Sets up the choose size dialog. */
    private void setupDialog() {
        //Set up size dialog
        mySizeDialog.setTitle("CHOOSE A BOARD SIZE");
        mySizeDialog.setResizable(false);
        mySizeDialog.setLayout(new BorderLayout());
        mySizeDialog.setPreferredSize(new Dimension(myPanelSize.width, 
                                                myPanelSize.height));
        mySizeDialog.setLocationRelativeTo(this);
        mySizeDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        mySizeDialog.add(myBoardSizePanel, BorderLayout.CENTER);
        mySizeDialog.pack();
    }
        
    /** Creates the menu buttons. */
    private void createMenuButtons() {
        myStart = new MenuButton(START);
        myStart.addPropertyChangeListener(this);       
        add(myStart);
        
        final MenuButton help = new MenuButton(HELP);
        help.addPropertyChangeListener(this);
        add(help);
        
        final MenuButton grid = new MenuButton(GRID);
        grid.addPropertyChangeListener(this);
        add(grid);
    }
    
    /**
     * Sets the button label.
     * 
     * @param theLabel the new label.
     */
    public void setLabel(final String theLabel) {
        myStart.setLabel(theLabel);
    }
    
    /**
     * Builds the help message displaying the scoring algorithm.
     * 
     * @return the help message.
     */
    private String helpMessage() {
        final StringBuilder sb = new StringBuilder(200);
        sb.append("COMPLETE A LINE TO GAIN 100 POINTS");
        sb.append('\n');
        sb.append("LEVEL AND DIFFICULTY INCREASE EVERY 5 LINES CLEARED");
        return sb.toString();
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

    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        if (START.equals(theEvent.getPropertyName())) {
            mySizeDialog.setVisible(true);
            myStart.setLabel(END);
        } else if ("BOARDSIZE".equals(theEvent.getPropertyName())) {
            mySizeDialog.dispose();
        } else if (END.equals(theEvent.getPropertyName())) {
            firePropertyChange(END, null, END);
            myStart.setLabel(START);
        } else if (HELP.equals(theEvent.getPropertyName())) {
            JOptionPane.showMessageDialog(this, helpMessage(), "HOW TO PLAY", 1);
        } else if (GRID.equals(theEvent.getPropertyName())) {
            firePropertyChange(GRID, null, GRID);
        }
    }
}
