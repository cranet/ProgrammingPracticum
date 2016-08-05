/*
 * Todd Crane
 * TCSS 305
 * assignment 6 - tetris
 * 
 */
package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import model.Board;

/**
 * Creates the GUI. 
 * 
 * @author Todd Crane
 * @version 6/4/2016
 *
 */
public class GUI implements Observer, PropertyChangeListener {
    
    /** The default delay (milliseconds) for the timer. */
    private static final int MOVE_DELAY = 1000;
    
    /** The timer multiplier. */
    private static final double TIMER_M = 0.8;
    
    /** The frame for the game. */
    private final JFrame myFrame;
    
    /** The board for the game. */
    private Board myBoard;
    
    /*** The panel for the board. */
    private final TetrisPanel myTetrisPanel;
    
    /** The panel for previewing a piece. */
    private final PreviewPanel myPreviewPanel;
    
    /** The panel for menu buttons. */
    private final MenuPanel myMenuPanel;
    
    /** The panel for choosing a board size. */
    private final BoardSizePanel myBoardSizePanel;
    
    /** The panel to display the keys. */
    private final ControlPanel myControlPanel;
    
    /** The panel for score information. */
    private final ScorePanel myScorePanel;
    
    /** The timer that controls the rate of the game. */
    private final Timer myTimer;
    
    /** The current level. */
    private int myCurrentLevel;
    
    /** The board size. */
    private Dimension myBoardSize;
    
    /** An image for the window icon. */
    private BufferedImage myImage; 

    /** Initialize the GUI. */
    public GUI() {
        myFrame = new JFrame("Tetris");
        myBoard = new Board();
        myTimer = new Timer(MOVE_DELAY, new MoveListener());
        myTetrisPanel = new TetrisPanel();
        myPreviewPanel = new PreviewPanel();
        myBoardSizePanel = new BoardSizePanel();
        myMenuPanel = new MenuPanel(myBoardSizePanel);
        myControlPanel = new ControlPanel(myBoard, myTimer);
        myScorePanel = new ScorePanel();

    }
    
    /** Sets up elements in the GUI. */
    public void setup() {

        //Add observers and property listeners.
        addObservers();
        myMenuPanel.addPropertyChangeListener(this);
        myBoardSizePanel.addPropertyChangeListener(this);
        myBoardSizePanel.addPropertyChangeListener(myMenuPanel);
        
        //Set up the top box panel.
        final JPanel topBox = new JPanel();
        topBox.setLayout(new BoxLayout(topBox, BoxLayout.X_AXIS));
        topBox.setAlignmentX(Container.LEFT_ALIGNMENT);
        topBox.add(myPreviewPanel);
        topBox.add(myMenuPanel);
        
        //Set up the bottom box panel.
        final JPanel bottomBox = new JPanel();
        bottomBox.setLayout(new BoxLayout(bottomBox, BoxLayout.X_AXIS));
        bottomBox.setAlignmentX(Container.LEFT_ALIGNMENT);
        bottomBox.add(myControlPanel);
        bottomBox.add(myScorePanel);
        
        //Set up the box panel for the top and bottom boxes.
        final JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box, BoxLayout.Y_AXIS));
        box.setAlignmentX(Container.LEFT_ALIGNMENT);
        box.add(topBox);
        box.add(bottomBox);
        
        //Add panels.
        myFrame.add(myTetrisPanel, BorderLayout.CENTER);
        myFrame.add(box, BorderLayout.EAST);
        
        //Set up the control buttons.
        myControlPanel.setupInputMap();
        myControlPanel.setupActionMap();
        myControlPanel.createControlButtons();
        
        //Set up the frame.
        createImage();
        myFrame.setIconImage(myImage);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        myFrame.setResizable(false);
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
        
        myCurrentLevel = myScorePanel.getLevel();
        myTetrisPanel.setGrid(false);
        myBoard.newGame();
    }
    
    /** Starts a new game. */
    private void startGame() {
        //Remove observers, make a new board.
        removeObservers();
        myBoard = new Board(myBoardSize.width, myBoardSize.height);
        //Send new board to panels.
        myTetrisPanel.setBoardSize(myBoardSize);
        myControlPanel.setBoard(myBoard);
        myControlPanel.setupInputMap();
        myControlPanel.setFlag(true);
        //Add observers, start game.
        addObservers();
        myBoard.newGame();
        myTimer.start();
    }
    
    /** Ends the current game. */
    private void endGame() {
        myTimer.stop();
        myControlPanel.unbind(); 
        myControlPanel.setFlag(false);
        myMenuPanel.setLabel("START");
        JOptionPane.showMessageDialog(myFrame, "GameOver");
    }

    /** Adds observers. */
    private void addObservers() {
        myBoard.addObserver(this);
        myBoard.addObserver(myTetrisPanel);
        myBoard.addObserver(myPreviewPanel);
        myBoard.addObserver(myScorePanel);
    }
    
    /** Removes observers. */
    private void removeObservers() {
        myBoard.deleteObservers();
    }
    
    /** Speeds up timer on level up. */
    private void checkLevel() {
        if (myScorePanel.getLevel() > myCurrentLevel) {
            myTimer.setDelay((int) (myTimer.getDelay() * TIMER_M));
            myCurrentLevel++;
        }
    }
    
    /** Sets the image from a file. */
    private void createImage() {
        try {
            myImage = ImageIO.read(new File("images/w_small.png"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    } 

    @Override
    public void update(final Observable theObject, final Object theArg) {
        //Check for lines cleared.
        if (theArg instanceof Integer[]) {
            checkLevel();
        
        //Check for game over boolean.
        } else if (theArg instanceof Boolean) {
            endGame();
        } 
    }
    
    @Override
    public void propertyChange(final PropertyChangeEvent theEvent) {
        //Check for starting the game/choosing board size.
        if ("BOARDSIZE".equals(theEvent.getPropertyName())) {
            myBoardSize = (Dimension) theEvent.getNewValue();
            startGame();
        //Check for game over.
        } else if ("END".equals(theEvent.getPropertyName())) {
            endGame();
        //Check for grid.
        } else if ("GRID".equals(theEvent.getPropertyName())) {
            myTetrisPanel.setGrid(false);
        }
    }
    
    /**
     * A private class that listens for timer events.
     * @author Todd Crane
     * @version 6/4/2016
     *
     */
    private class MoveListener implements ActionListener {
        @Override
        public void actionPerformed(final ActionEvent theEvent) {
            myBoard.step();
        }
    }

}
