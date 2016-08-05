/* 
 * Todd Crane
 * TCSS 305
 * assignment 6 - tetris
 */
package view;

import java.awt.EventQueue;

/**
 * Starts the game.
 * 
 * @author Todd Crane
 * @version 5/28/2016
 *
 */
final class Main {
    
    /** Private constructor to prevent instantiation. */
    private Main() {
        throw new IllegalStateException();
    }

    /**
     * Start point for the application.
     * @param theArgs Command line parameters
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().setup();
            }
        });

    }

}
