/*
 * Todd Crane
 * TCSS 305
 * assignment 6 - tetris
 * 
 */
package test;

import java.util.Observable;
import java.util.Observer;

import model.Board;

/**
 * First class for testing, prints the board.
 * 
 * @author Todd Crane
 * @version 6/4/2016
 *
 */
public class BoardTest implements Observer {

    /**
     * Prints the board.
     * @param theArg the argument.
     */
    public static void main(final String[] theArg) {
        
        final Board a = new Board(10, 20);
        a.newGame();
        System.out.println(a.toString());
        
        for (int i = 0; i < 21; i++) {
            a.step();
            System.out.println(a.toString());
        }
    }

    @Override
    public void update(final Observable arg0, final Object arg1) {
        System.out.println("observed");
        System.out.println(arg1);
        if (arg1 instanceof String) {
            System.out.println("yes, string");
        }
        
    }

}
