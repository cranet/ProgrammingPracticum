/*
 * Todd Crane
 * TCSS 305
 * assignment 5 - powerpaint 
 */
package view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import tools.RectangleTool;
import view.DrawingPanel;

/**
 * The action for selecting the rectangle tool.
 * 
 * @author Todd Crane
 * @version 5/18/2016
 *
 */
public class RectangleAction extends AbstractAction {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = -8281040252784858716L;

    /** Panel to associate with this action. */
    private final DrawingPanel myPanel;
    
    /** The tool to associate with this action. */
    private final RectangleTool myRectangleTool;
    
    /**
     * Construct the action.
     * @param thePanel panel to associate with this action.
     */
    public RectangleAction(final DrawingPanel thePanel) {
        super("Rectangle");
        myPanel = thePanel;
        myRectangleTool = new RectangleTool();
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_R);
        putValue(Action.SELECTED_KEY, false);
    }
    
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myPanel.setCurrentTool(myRectangleTool);
    }
}