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

import tools.LineTool;
import view.DrawingPanel;

/**
 * The action for selecting the line tool.
 * 
 * @author Todd Crane
 * @version 5/18/2016
 *
 */
public class LineAction extends AbstractAction {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = 729075604262269605L;

    /** Panel to associate with this action. */
    private final DrawingPanel myPanel;
    
    /** The tool to associate with this action. */
    private final LineTool myLineTool;
    
    /**
     * Construct the action. 
     * 
     * @param thePanel panel to associate with this action.
     */
    public LineAction(final DrawingPanel thePanel) {
        super("Line");
        myPanel = thePanel;
        myLineTool = new LineTool();
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_L);
        putValue(Action.SELECTED_KEY, false);
    }
    
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myPanel.setCurrentTool(myLineTool);
    }
}
