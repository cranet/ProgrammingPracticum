/*
 * TCSS 305
 * Spring 2016
 * assignment 3 - easystreet
 */
package model;

import java.util.Map;

/**
 * Class for atv.
 * 
 * @author Todd Crane
 * @version 4/23/2016
 */
public class Atv extends AbstractVehicle {
    
    /** The death time of this vehicle. */
    private static final int DEATH_TIME = 20;

    /**
     * Constructor for the ATV class.
     * 
     * @param theVehicleX The x coordinate of this atv.
     * @param theVehicleY The y coordinate of this atv.
     * @param theDir The direction of this atv.
     */
    public Atv(final int theVehicleX, final int theVehicleY, 
               final Direction theDir) {
        super(theVehicleX, theVehicleY, theDir, DEATH_TIME);
    }

    /**
     * Returns whether or not this object may move onto the given type of
     * terrain, when the street lights are the given color.
     * 
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return Whether or not this object may move onto the given type of
     *         terrain when the street lights are the given color.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean canPass = false;
        //check for valid terrain.
        if (theTerrain != Terrain.WALL) {
            canPass = true;
        }
        return canPass;
    }

    /**
     * Returns the direction this object would like to move, based on the given
     * map of the neighboring terrain.
     * 
     * @param theNeighbors The map of neighboring terrain.
     * @return The direction this object would like to move.
     */

    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        //Direction to be returned, set to random.
        Direction atvDir = Direction.random();
        /*
         * Choose a random direction until a valid option is chosen.
         * Prevents reversing.
         */
        while (theNeighbors.get(atvDir) == Terrain.WALL
                        || atvDir == getDirection().reverse()) {
            atvDir = Direction.random();
        }
        return atvDir;
    }
}
