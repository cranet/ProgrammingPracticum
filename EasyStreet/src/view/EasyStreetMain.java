/*
 * TCSS 305 - Easy Street
 */

package view;

import java.awt.EventQueue;

/**
 * Runs the Easy Street program.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman
 * @author Alan Fowler (acfowler@u.washington.edu)
 * @version 1.1
 */

public final class EasyStreetMain {
    
    /**
     * Private constructor to prevent construction of instances.
     */
    private EasyStreetMain() {
        // do nothing
    }

    /**
     * Constructs the main GUI window frame.
     * 
     * @param theArgs Command line arguments (ignored).
     */
    public static void main(final String... theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new EasyStreetGUI();     
            }
        });
    }
}
