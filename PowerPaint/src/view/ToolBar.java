/**
 * Todd Crane
 * TCSS 305
 * assignment 5 - powerpaint
 */
package view;

import javax.swing.Action;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

/**
 * Creates the tool bar.
 * 
 * @author Todd Crane
 * @version 5/18/2016
 *
 */
public class ToolBar extends JToolBar {
    
    /** A generated serialization ID. */
    private static final long serialVersionUID = 7280568427508955229L;
    /** Button group for the tool bar buttons. */
    private final ButtonGroup myGroup;
    
    /** Constructs the tool bar. */
    public ToolBar() {
        super();
        myGroup = new ButtonGroup();
    }
    
    /**
     * Create a toggle button for the tool bar.
     * @param theAction to associate with the button.
     */
    public void createToolBarButton(final Action theAction) {
        final JToggleButton toggleButton = new JToggleButton(theAction);
        myGroup.add(toggleButton);
        add(toggleButton);
    }

}
