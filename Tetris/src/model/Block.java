/*
 * TCSS 305 - Project Tetris
 */

package model;

/**
 * The different types of blocks that can be stored in a Board's grid.
 * 
 * @author TCSS 305 Instructors
 * @version Spring 2016
 */
public enum Block {
    
    /** AN empty space in the grid. */
    EMPTY('*'),
    /** A Block from an IPiece. */
    I('I'),
    /** A Block from a JPiece. */
    J('J'),
    /** A Block from an LPiece. */
    L('L'),
    /** A Block from an OPiece. */
    O('0'),
    /** A Block from an SPiece. */
    S('S'),
    /** A Block from a TPiece. */
    T('T'),
    /** A Block from a ZPiece. */
    Z('Z');
    
    /** The char associated with each Tetris Block constant. */
    private char myChar;
    
    /**
     * Initializes the enum.
     * 
     * @param theChar The char to associate with this Tetris Block constant
     */
    Block(final char theChar) {
        myChar = theChar;
    }
    
    /**
     * @return the char associated with this Tetris Block.
     */
    public char getChar() {
        return myChar;
    }
    

}
