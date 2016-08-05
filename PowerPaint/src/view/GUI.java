/*
 * Todd Crane
 * TCSS 305
 * assignment 5 - powerpaint 
 */
package view;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.JFrame;

import tools.PencilTool;
import view.actions.EllipseAction;
import view.actions.LineAction;
import view.actions.PencilAction;
import view.actions.RectangleAction;

/**
 * GUI class to create a GUI.
 * 
 * @author Todd Crane
 * @version 5/18/2016
 *
 */
public class GUI {
    
    /** The frame for this application. */
    private final JFrame myFrame;
    
    /** The menu bar for this application. */
    private final MenuBar myMenuBar;
    
    /** The tool bar for this application. */
    private final ToolBar myToolBar;
    
    /** A panel for drawing shapes. */
    private final DrawingPanel myDrawingPanel;
    
    /** A tool for drawing paths. */
    private final PencilTool myPencilTool;
    
    /** An image for the window icon and about icon. */
    private BufferedImage myImage;    

    /**
     * Initialize the GUI.
     */
    public GUI() {
        myFrame = new JFrame("PowerPaint");
        myToolBar = new ToolBar();
        createImage();
        myDrawingPanel = new DrawingPanel();
        myMenuBar = new MenuBar(myFrame, myDrawingPanel, myImage);
        myPencilTool = new PencilTool();
    }
    
    /** Sets up elements in the GUI. */
    public void start() {
        //Action array for connecting menu-bar and tool-bar buttons.
        final Action[] actions = {new PencilAction(myDrawingPanel),
                                  new LineAction(myDrawingPanel),
                                  new RectangleAction(myDrawingPanel),
                                  new EllipseAction(myDrawingPanel)};
        //Connect buttons.
        for (final Action a : actions) {
            myMenuBar.createToolButton(a);
            myToolBar.createToolBarButton(a);
        }
        
        //Finish setting up the GUI.
        myDrawingPanel.addPropertyChangeListener(myMenuBar);
        myDrawingPanel.setCurrentTool(myPencilTool);
        myFrame.setIconImage(myImage);
        myFrame.setJMenuBar(myMenuBar);
        myFrame.add(myToolBar, BorderLayout.SOUTH);
        myFrame.add(myDrawingPanel, BorderLayout.CENTER);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.pack();
        myFrame.setLocationRelativeTo(null);
        myFrame.setVisible(true);
    }
    
    /** Sets the image from a file. */
    private void createImage() {
        try {
            myImage = ImageIO.read(new File("images/w_small.png"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }    

}
