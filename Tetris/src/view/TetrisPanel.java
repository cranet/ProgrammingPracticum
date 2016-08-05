/*
 * Todd Crane
 * TCSS 305
 * assignment 6 - tetris
 * 
 */
package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

/**
 * Class extends panel.
 * Draws the grid and pieces.
 * 
 * @author Todd Crane
 * @version 6/4/2016
 *
 */
public class TetrisPanel extends JPanel implements Observer {
    
    /** Generated serial ID. */
    private static final long serialVersionUID = 8868437628885941022L;

    /** Original size. */
    private static final Dimension ORIG_PANEL_SIZE = new Dimension(10, 20);
    
    /** Default board size. */
    private static final Dimension DEFAULT_BOARD = new Dimension(10, 20);
    
    /** Small scale multiplier. */
    private static final int S = 10;
    
    /** Medium scale multiplier. */
    private static final int M = 25;
    
    /** Large scale multiplier. */
    private static final int L = 50;
    
    /** The stroke for the blocks. */
    private static final int STROKE = 4;
    
    /** The number for ignoring the first lines of the board. */
    private static final int IGNORE = 5;
    
    /** Shape to be drawn. */
    private Shape myShape;
    
    /** The array for strings sent from the board. */
    private String[] myArray;
    
    /** The panel size. */
    private Dimension myPanelSize;
    
    /** The board size .*/
    private Dimension myBoardSize;
    
    /** Checks for drawing a grid. */
    private boolean myGrid;
    
    /**
     * Constructs a new TetrisPanel.
     */
    public TetrisPanel() {
        super();
        myPanelSize = new Dimension(ORIG_PANEL_SIZE.width * M, 
                                    ORIG_PANEL_SIZE.height * M);
        myBoardSize = new Dimension(DEFAULT_BOARD.width, DEFAULT_BOARD.height);
        setupPanel();
    }
    
    /** Sets up the defaults for the panel. */
    private void setupPanel() {
        //Setup the panel. 
        setPreferredSize(myPanelSize);
        setBackground(Color.WHITE);
        
        //Add listener for resizing.
        //addComponentListener(new ResizeListener());
        
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                            RenderingHints.VALUE_ANTIALIAS_ON);
        
        if (myGrid) {
            drawGrid(g2d);
        }
        g2d.setPaint(Color.CYAN);        
        drawRectangle(g2d);
        
    }
    
    /**
     * Draws rectangles that make up pieces.
     * 
     * @param theGraphics The Graphics2D object.
     */
    private void drawRectangle(final Graphics2D theGraphics) {

        //Iterate through the array.
        for (int i = IGNORE; i < myArray.length; i++) {
            //Set up fields.
            int x = 0;
            final int y = (i - IGNORE) * (getWidth() / myBoardSize.width);
            final String row = myArray[i];
            int spaceCount = -1; //Keep track of empty space.

            //Iterate through each row of text.
            for (int j = 0; j < row.length(); j++) {
                //Record spaces for coordinates.
                if (row.charAt(j) == ' ') {
                    spaceCount++;
                //Draw the rectangles.
                } else if (row.charAt(j) != '|' && row.charAt(j) != ','
                                 && row.charAt(j) != '-') {
                    spaceCount++;
                    x = spaceCount * (getWidth() / myBoardSize.width);
                    myShape = new Rectangle(x, y, 
                                            getWidth() / myBoardSize.width, 
                                            getHeight() / myBoardSize.height);
                    theGraphics.setStroke(new BasicStroke(STROKE));
                    theGraphics.setPaint(Color.WHITE);
                    theGraphics.draw(myShape);
                    theGraphics.setPaint(Color.CYAN);
                    theGraphics.fill(myShape);

                }
            }
        }
    }
    
    /**
     * Draws a 10 x 20 grid on the panel.
     * 
     * @param theGraphics The Graphics2D object.
     */
    private void drawGrid(final Graphics2D theGraphics) {
        theGraphics.setPaint(Color.LIGHT_GRAY);
        //x lines (horizontal)
        for (int i = 0; i < myBoardSize.height; i++) {
            theGraphics.drawLine(0, i * (getHeight() / myBoardSize.height),
                                 getWidth(), 
                                 i * (getHeight() / myBoardSize.height));
        }
        //y lines (vertical)
        for (int i = 0; i < myBoardSize.width; i++) {
            theGraphics.drawLine(i * (getWidth() / myBoardSize.width), 0, 
                                 i * (getWidth() / myBoardSize.width), 
                                 getHeight());
        }
        
    }
    
    /**
     * The size of the board.
     * @param theDimension the new board size.
     */
    public void setBoardSize(final Dimension theDimension) {
        myBoardSize = theDimension;
    }
    
    /**
     * Sets whether or not to draw the grid.
     * @param theBool whether or not to draw the grid.
     */
    public void setGrid(final boolean theBool) {
        if (myGrid) {
            myGrid = theBool;
        } else {
            myGrid = true;
        }
    }

    @Override
    public void update(final Observable theObject, final Object theArg) {
        if (theArg instanceof String) {
            //Capture and split string.
            final String boardString = (String) theArg;
            myArray = boardString.split("\n");
            repaint();
        }

    }
    
    /**
     * Sets the size of the panel.
     * Used to resize the game.
     * 
     * @param theString the size.
     */
    public void setSize(final String theString) {
        if ("S".equals(theString)) {
            myPanelSize = new Dimension(ORIG_PANEL_SIZE.width * S, 
                                        ORIG_PANEL_SIZE.height * S);
        } else if ("M".equals(theString)) {
            myPanelSize = new Dimension(ORIG_PANEL_SIZE.width * M, 
                                        ORIG_PANEL_SIZE.height * M);
        } else if ("L".equals(theString)) {
            myPanelSize = new Dimension(ORIG_PANEL_SIZE.width * L, 
                                        ORIG_PANEL_SIZE.height * L);
        }
        repaint();
    }

}
