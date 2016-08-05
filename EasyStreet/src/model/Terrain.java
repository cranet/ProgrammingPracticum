/*
 * TCSS 305 - Easy Street
 */

package model;

/**
 * An enumeration (and associated functionality) for types of terrain that may
 * appear on the map.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman
 * @author Alan Fowler (acfowler@u.washington.edu)
 * @version 1.2
 */

public enum Terrain {
    /**
     * Grass.
     */
    GRASS('-'),

    /**
     * Street.
     */
    STREET('|'),

    /**
     * Light.
     */
    LIGHT('+'),

    /**
     * Wall.
     */
    WALL('X'),

    /**
     * Trail.
     */
    TRAIL('.'),
    
    /**
     * Trail.
     */
    CROSSWALK('#');

    /**
     * The character corresponding to a particular value of the enumeration.
     */
    private char myLetter;

    // Constructor

    /**
     * Constructs a new Terrain with the specified letter.
     * 
     * @param theLetter The letter.
     */
    Terrain(final char theLetter) {
        myLetter = theLetter;
    }

    // Instance Methods

    /**
     * Returns the Terrain represented by the given letter.
     * 
     * @param theLetter The letter.
     * @return the Terrain represented by the given letter, or GRASS if no
     *         Terrain is represented by the given letter.
     */
    public static Terrain valueOf(final char theLetter) {
        Terrain result = GRASS;

        for (final Terrain terrain : Terrain.values()) {
            if (terrain.myLetter == theLetter) {
                result = terrain;
                break;
            }
        }

        return result;
    }

    /**
     * Returns a String representation of this Terrain, such as "WALL (X)".
     * 
     * @return a String representation of this Terrain.
     */
    @Override
    public String toString() {
        return super.toString() + " (" + myLetter + ")";
    }
}

// end of class Terrain
