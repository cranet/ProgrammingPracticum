/*
 * TCSS 305
 * Spring 2016
 * assignment 3 - easystreet
 */
package model;

import java.util.Map;

/**
 * Class for bicycle.
 * 
 * @author Todd Crane
 * @version 4/23/2016
 */
public class Bicycle extends AbstractVehicle {
    
    /** The death time of this vehicle. */
    private static final int DEATH_TIME = 30;

    /**
     * Constructor for the Bike class.
     * 
     * @param theVehicleX The x coordinate of this bike.
     * @param theVehicleY The y coordinate of this bike.
     * @param theDir The direction of this bike.
     */
    public Bicycle(final int theVehicleX, final int theVehicleY, 
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
        //Check for valid terrain and light combination.
        if (theTerrain == Terrain.TRAIL || theTerrain == Terrain.STREET) {
            canPass = true;
        //Check for valid terrain and light combination.
        } else if ((theTerrain == Terrain.LIGHT && theLight == Light.GREEN)
                        || (theTerrain == Terrain.CROSSWALK
                        && theLight == Light.GREEN)) {
            canPass = true;
        }
        return canPass;
    }

    /**
     * Returns the direction this object would like to move, based on the given
     * map of the neighboring terrain.
     * 
     * @param theNeighbors The map of neighboring terrain.
     * @return The Direction this object would like to move.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        //Direction to be returned
        Direction bikeDir = getDirection();
        /*
         * Array for neighboring terrain.
         * 0 - Straight
         * 1 - Left
         * 2 - Right
         */
        final Terrain[] bikeTerrain = new Terrain[] {
                        theNeighbors.get(getDirection()),
                        theNeighbors.get(bikeDir.left()),
                        theNeighbors.get(bikeDir.right())};
        
        //Check for valid direction and terrain (trail).
        if (isTrail(bikeTerrain)) {
            while (theNeighbors.get(bikeDir) != Terrain.TRAIL 
                            || bikeDir == getDirection().reverse()) {
                bikeDir = Direction.random();
            }
        //Straight is valid.
        } else if (validTerrain(bikeTerrain[0])) {
            bikeDir = getDirection();
        //Left is valid.
        } else if (validTerrain(bikeTerrain[1])) {
            bikeDir = bikeDir.left();
        //Right is valid.
        } else if (validTerrain(bikeTerrain[2])) {
            bikeDir = bikeDir.right();
        //Reverse as last resort.
        } else {
            bikeDir = bikeDir.reverse();
        }
        return bikeDir;
    }
    
    /**
     * Helper method for chooseDirection.
     * Checks for a trail in three directions, straight, left, right.
     * @param theTerrain An array if the neighboring terrain.
     * @return True if a trail is present in any of the three directions,
     * false otherwise.
     */
    private boolean isTrail(final Terrain[] theTerrain) {
        boolean isTrail = false;
        //Check for trail straight, left, right.
        if (theTerrain[0] == Terrain.TRAIL || theTerrain[1] == Terrain.TRAIL
                        || theTerrain[2] == Terrain.TRAIL) {
            isTrail = true;
        }
        return isTrail;
        
    }
    
    /**
     * Helper method for chooseDirection.
     * Checks the passed terrain to see if it is a valid terrain type.
     * @param theTerrain The neighboring terrain.
     * @return True if the terrain is valid, false otherwise.
     */
    private boolean validTerrain(final Terrain theTerrain) {
        boolean validTerrain = false;
        //Check for valid terrain types.
        if (theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT
                        || theTerrain == Terrain.CROSSWALK) {
            validTerrain = true;
        }
        return validTerrain;
    }
}
