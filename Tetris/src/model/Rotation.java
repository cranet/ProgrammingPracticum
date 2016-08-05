/*
 * TCSS 305
 * 
 * An implementation of the classic game "Tetris".
 */

package model;

import java.util.Random;

/**
 * Enumeration of Rotation types.
 * 
 * @author TCSS 305 Instructors
 * @version Spring 2016
 */
public enum Rotation {

    /**
     * No rotation.
     */
    NONE,

    /**
     * Quarter rotation or 90 degrees.
     */
    QUARTER,

    /**
     * Half rotation or 180 degrees.
     */
    HALF,

    /**
     * Three quarters rotation or 270 degrees.
     */
    THREEQUARTER;

    /**
     * A Random object used for generating random rotations.
     */
    private static final Random RANDOM = new Random();

    /**
     * Create a new Rotation from this one rotated clockwise.
     * 
     * @return new Rotation object that is rotated 90 degrees clockwise.
     */
    public Rotation clockwise() {
        return values()[(this.ordinal() + 1) % values().length];
    }

    /**
     * Create a new Rotation from this one rotated counterclockwise.
     * 
     * @return new Rotation object that is rotated 90 degrees clockwise.
     */
    public Rotation counterClockwise() {
        return values()[(this.ordinal() - 1 + values().length) % values().length];
    }

    /**
     * Creates a new Rotation with a random angle.
     * 
     * @return new random Rotation.
     */
    public static Rotation random() {
        return values()[RANDOM.nextInt(values().length)];
    }

}
