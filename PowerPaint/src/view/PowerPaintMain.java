/*
 * Todd Crane
 * TCSS 305
 * assignment 5 - powerpaint 
 */
package view;

import java.awt.EventQueue;

/**
 * Main class to run the application.
 * 
 * @author Todd Crane
 * @version 5/18/2016
 *
 */
public final class PowerPaintMain {
    
    /** Private constructor to prevent instantiation. */
    private PowerPaintMain() {
        throw new IllegalStateException();
    }

    /**
     * @param theArgs the passed argument.
     */
    public static void main(final String[] theArgs) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new GUI().start();
            }
        });
    }

}
