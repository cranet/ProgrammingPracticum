/*
 * Todd Crane
 * TCSS 305
 * assignment 6 - tetris
 * 
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import model.TetrisPiece;

/**
 * Class displays the next piece. 
 * 
 * @author Todd Crane
 * @version 6/4/2016
 *
 */
public class PreviewPanel extends JPanel implements Observer {
    
    /** Generated serial ID. */
    private static final long serialVersionUID = 2720809172855151922L;

    /** Original panel size. */
    private static final Dimension ORIG_PANEL_SIZE = new Dimension(5, 5);
    
    /** Original piece size. */
    private static final Dimension ORIG_PIECE_SIZE = new Dimension(1, 1);    
    
    /** Medium scale multiplier. */
    private static final int M = 25;
    
    /** The next tetris piece to display. */
    private TetrisPiece myPiece;
    
    /** The panel size. */
    private final Dimension myPanelSize;
    
    /** The piece size. */
    private final Dimension myPieceSize;
    
    
    /** Constructs a piece preview panel. */
    public PreviewPanel() {
        super();
        myPanelSize = new Dimension(ORIG_PANEL_SIZE.width * M, 
                                    ORIG_PANEL_SIZE.height * M);
        myPieceSize = new Dimension(ORIG_PIECE_SIZE.width * M, 
                                     ORIG_PIECE_SIZE.height * M);
        setupPanel();
    }
    
    /** Sets up the panel. */
    private void setupPanel() {
        setPreferredSize(myPanelSize);
        setBackground(Color.DARK_GRAY);

    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setPaint(Color.CYAN);

        //Iterate through array, get needed parts.
        for (int i = 0; i < myPiece.getPoints().length; i++) {
            final int x = myPiece.getPoints()[i].x();
            final int y = -myPiece.getPoints()[i].y();
            final int width = myPiece.getWidth() * myPieceSize.width;
            final int height = myPiece.getHeight() * myPieceSize.height;
            
            //Square to be drawn.
            final Rectangle rect = new Rectangle();

            //Set up the square to be drawn.
            rect.setSize(myPieceSize.width, myPieceSize.height);
            rect.setLocation(x * myPieceSize.width, y * myPieceSize.height);
            rect.translate(0, myPieceSize.width * 2); //Send to 0,0
            rect.translate((this.getWidth() - width) / 2, 
                           (this.getHeight() - height) / 2);
            //Check for O piece.
            if (myPiece == TetrisPiece.O) {
                rect.translate(-myPieceSize.width, 0);
            }

            g2d.draw(rect);         
        }

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
    public void update(final Observable theObject, final Object theArg) {
        //Check for a tetris piece.
        if (theArg instanceof TetrisPiece) {
            myPiece = (TetrisPiece) theArg;
            repaint();
        }  
    }
    
}
