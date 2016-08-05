/**
 * 
 */
package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

/**
 * Class for setting up the score panel.
 * 
 * @author Todd Crane
 * @version 6/4/2016
 *
 */
public class ScorePanel extends JPanel implements Observer {
    
    /** Generated serial ID. */
    private static final long serialVersionUID = 1397790239198935402L;

    /** Original panel size. */
    private static final Dimension ORIG_PANEL_SIZE = new Dimension(5, 15);
    
    /** Original rectangle size. */
    private static final Dimension ORIG_OUTLINE_SIZE = new Dimension(3, 1);
    
    /** Starting position. */
    private static final Point START_POSITION = new Point(10, 15);
    
    /** Original font size. */
    private static final double FONT_SIZE = .5;
    
    /** Medium scale multiplier. */
    private static final int M = 25;
    
    /** Layout multiplier. */
    private static final int COUNT_M = 5;
    
    /** Score multiplier. */
    private static final int SCORE_M = 100;
    
    /** The panel size. */
    private final Dimension myPanelSize;
    
    /** Box outline. */
    private final Rectangle2D myRect;
    
    /** Labels for the score panel. */
    private final String[] myLabels;
    
    /** The font. */
    private final Font myFont;
    
    /** The current score. */
    private int myScore;
    
    /** The current level. */
    private int myLevel;
    
    /** The next level. */
    private int myNextLevel;
    
    /** The number of lines cleared. */
    private int myLines;
    
    /** Constructs a panel for displaying score information. */
    public ScorePanel() {
        super();
        myPanelSize = new Dimension(ORIG_PANEL_SIZE.width * M, 
                                    ORIG_PANEL_SIZE.height * M);
        myRect = new Rectangle(0, 0, ORIG_OUTLINE_SIZE.width * M, 
                               ORIG_OUTLINE_SIZE.height * M);
        
        myFont = new Font("Font", Font.PLAIN, (int) (FONT_SIZE * M));
        myLabels = new String[]{"SCORE", "LEVEL", "LINES CLEARED", 
                                "NEXT LEVEL"};
        setupPanel();
    }
    
    /** Sets up the panel. */
    private void setupPanel() {
        setPreferredSize(myPanelSize);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.DARK_GRAY);
        setIntialScore();
    }
    
    /** Sets up the initial score values. */
    private void setIntialScore() {
        myScore = 0;
        myLevel = 1;
        myLines = 0;
        myNextLevel = COUNT_M;
    }
    
    @Override
    public void paintComponent(final Graphics theGraphics) {
        super.paintComponent(theGraphics);
        final Graphics2D g2d = (Graphics2D) theGraphics;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                             RenderingHints.VALUE_ANTIALIAS_ON);
        
        g2d.setColor(Color.WHITE);
        g2d.setFont(myFont);
        
        //Convert values to strings.
        final String score = String.valueOf(myScore);
        final String level = String.valueOf(myLevel);
        final String lines = String.valueOf(myLines);
        final String nextLevel = String.valueOf(myNextLevel);
    
        //Put values in array.
        final String[] values = new String[]{score, level, lines, nextLevel};
        
        //Spacers.
        final int rectSpacer = 10;
        final int xValueSpacer = 5;
        final int yValueSpacer = 26;
        int counter = 1;
        
        for (int i = 0; i < myLabels.length; i++) {
            //Label.
            g2d.drawString(myLabels[i], START_POSITION.x, START_POSITION.y * counter);
            //Outline.
            g2d.drawRect(START_POSITION.x, (START_POSITION.y * counter) + rectSpacer, 
                         (int) myRect.getWidth(), (int) myRect.getHeight()); 
            //Value.
            g2d.drawString(values[i], START_POSITION.x + xValueSpacer, 
                           (START_POSITION.y * counter) + yValueSpacer);
            
            //Add "LINES" to next level.
            if ("NEXT LEVEL".equals(myLabels[i])) {
                g2d.drawString("LINES", START_POSITION.x 
                               + xValueSpacer * xValueSpacer, 
                               (START_POSITION.y * counter) + yValueSpacer);
            }
            
            counter += COUNT_M;
        } 
    }
    
    /**
     * Sets the score, level, lines cleared and next level.
     * 
     * @param theArray the number and location of lines cleared.
     */
    private void setScore(final Integer[] theArray) {
        //Add to score.
        int counter = 1;
        for (int i = 0; i < theArray.length; i++) {
            myScore += counter * SCORE_M;
            myLines++;
            counter++;
        }
        
        //Check for level up.
        if (myLines % COUNT_M == 0) {
            myLevel++;
        }
        myNextLevel = COUNT_M - myLines;
        if (myNextLevel <= 0) {
            myNextLevel = COUNT_M;
        }
        
        repaint();
    }
    
    /**
     * Returns the current level.
     *  
     * @return the current level.
     */
    public int getLevel() {
        return myLevel;
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
        if (theArg instanceof Integer[]) {
            final Integer[] intA = (Integer[]) theArg;
            setScore(intA);
        }
    }
}
