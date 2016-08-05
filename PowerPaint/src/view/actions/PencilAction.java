/*
 *Todd Crane
 *TCSS 305
 *assignment 5 - powerpaint 
 */
package view.actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;

import tools.PencilTool;
import view.DrawingPanel;

/**
 * The action for selecting the pencil tool.
 * 
 * @author Todd Crane
 * @version 5/18/2016
 *
 */
public class PencilAction extends AbstractAction {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = 2631634476367441191L;

    /** Panel to associate with this action. */
    private final DrawingPanel myPanel;
    
    /** The tool to associate with this action. */
    private final PencilTool myPencilTool;
    
    /**
     * Construct the action.
     * 
     * @param thePanel panel to associate with this action.
     */
    public PencilAction(final DrawingPanel thePanel) {
        super("Pencil");
        myPanel = thePanel;
        myPencilTool = new PencilTool();
        putValue(Action.MNEMONIC_KEY, KeyEvent.VK_P);
        putValue(Action.SELECTED_KEY, true);
    }
    
    @Override
    public void actionPerformed(final ActionEvent theEvent) {
        myPanel.setCurrentTool(myPencilTool);
    }
}