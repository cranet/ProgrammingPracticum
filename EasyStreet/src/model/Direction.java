/*
 * TCSS 305 - Easy Street
 */

package model;

import java.util.Random;

/**
 * An enumeration (and associated functionality) for directions in which a
 * vehicle may travel.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman
 * @author Alan Fowler (acfowler@u.washington.edu)
 * @version 1.1
 */

public enum Direction {

    /**
     * North (which is up on the screen).
     */
    NORTH('N'),

    /**
     * West (which is left on the screen).
     */
    WEST('W'),

    /**
     * South (which is down on the screen).
     */
    SOUTH('S'),

    /**
     * East (which is right on the screen).
     */
    EAST('E');

    /**
     * A Random that we use for generating random directions.
     */
    private static final Random RANDOM = new Random();

    /**
     * The letter corresponding to a particular value of the enumeration.
     */
    private final char myLetter;

    // Constructor

    /**
     * Constructs a new Terrain with the specified letter.
     * 
     * @param theLetter The letter.
     */
    Direction(final char theLetter) {
        myLetter = theLetter;
    }

    // Instance Methods

    /**
     * Returns the Direction represented by the given letter.
     * 
     * @param theLetter The letter.
     * @return the Direction represented by the given letter, or null if no
     *         Direction is represented by the given letter.
     */
    public static Direction valueOf(final char theLetter) {
        Direction result = null;

        for (final Direction direction : Direction.values()) {
            if (direction.letter() == theLetter) {
                result = direction;
                break;
            }
        }

        return result;
    }

    /**
     * Returns the letter corresponding to this direction.
     * 
     * @return the letter corresponding to this direction.
     */
    public char letter() {
        return myLetter;
    }

    /**
     * Returns the direction you get if you rotate this direction
     * counter-clockwise by 90 degrees.
     * 
     * @return the direction you get if you rotate this direction
     *         counter-clockwise by 90 degrees.
     */
    public Direction left() {
        Direction result = null;

        switch (this) {
            case NORTH:
                result = WEST;
                break;

            case WEST:
                result = SOUTH;
                break;

            case SOUTH:
                result = EAST;
                break;

            case EAST:
                result = NORTH;
                break;

            default:
                break;
        }

        return result;
    }

    /**
     * Returns a random Direction.
     * 
     * @return a random Direction.
     */
    public static Direction random() {
        return values()[RANDOM.nextInt(values().length)];
    }

    /**
     * Returns the direction you get if you rotate this direction clockwise by
     * 90 degrees.
     * 
     * @return the direction you get if you rotate this direction clockwise by
     *         90 degrees.
     */
    public Direction right() {
        Direction result = null;

        switch (this) {
            case NORTH:
                result = EAST;
                break;

            case WEST:
                result = NORTH;
                break;

            case SOUTH:
                result = WEST;
                break;

            case EAST:
                result = SOUTH;
                break;

            default:
                break;
        }

        return result;
    }

    /**
     * Returns the direction opposite this one.
     * 
     * @return the direction opposite this one.
     */
    public Direction reverse() {
        return left().left();
    }

    /**
     * Returns the change in x-coordinate by moving one space in this direction
     * (for example, WEST would be -1, and NORTH would be 0).
     * 
     * @return the change in x-coordinate.
     */
    public int dx() {
        int result = 0;

        switch (this) {
            case WEST:
                result = -1;
                break;

            case EAST:
                result = 1;
                break;

            default:
        }

        return result;
    }

    /**
     * Returns the change in y-coordinate by moving one space in this direction
     * (for example, WEST would be 0, and NORTH would be -1).
     * 
     * @return the change in y-coordinate.
     */
    public int dy() {
        int result = 0;

        switch (this) {
            case SOUTH:
                result = 1;
                break;

            case NORTH:
                result = -1;
                break;

            default:
        }

        return result;
    }
}

// end of class Direction
