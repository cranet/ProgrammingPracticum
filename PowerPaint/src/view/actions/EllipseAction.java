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

import tools.EllipseTool;
import view.DrawingPanel;

/**
 * The action for selecting the ellipse tool.
 * 
 * @author Todd Crane
 * @version 5/18/2016
 *
 */
public class EllipseAction extends AbstractAction {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = -355611344100476721L;

    /** Panel to associate with this action. */
    private final DrawingPanel myPanel;
    
    /** The tool to associate with this action. */
    private final EllipseTool myEllipseTool;
    
    /**
     * Construct the action.
     * 
     * @param thePanel panel to associate with this action.
     */
    public EllipseAction(final DrawingPanel thePanel) {
        super("Ellipse");
        myPanel = thePanel;
        myEllipseTool = new EllipseTool();
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_E);
        putValue(Action.SELECTED_KEY, false);
    }
    
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myPanel.setCurrentTool(myEllipseTool);
    }
}