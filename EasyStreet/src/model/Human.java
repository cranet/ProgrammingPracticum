/*
 * TCSS 305
 * Spring 2016
 * assignment 3 - easystreet
 */
package model;

import java.util.Map;

/**
 * Class for human.
 * 
 * @author Todd Crane
 * @version 4/23/2016
 *
 */
public class Human extends AbstractVehicle {
    
    /** The death time of this vehicle. */
    private static final int DEATH_TIME = 50;

    /**
     * Constructor for the Human class.
     * 
     * @param theVehicleX The x coordinate of this human.
     * @param theVehicleY The y coordinate of this human.
     * @param theDir The direction of this human.
     */
    public Human(final int theVehicleX, final int theVehicleY, 
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
        //Check for valid terrain.
        if (theTerrain == Terrain.GRASS) {
            canPass = true;
        //Check for valid cross-walk and light combination.
        } else if (theTerrain == Terrain.CROSSWALK && (theLight != Light.GREEN)) {
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
        //Direction to be returned, set to random.
        Direction humanDir = Direction.random();
        //Original direction for reverse and array.
        final Direction humanOrig = getDirection();
        /*
         * Array for neighboring terrain.
         * 0 - Straight
         * 1 - Left
         * 2 - Right
         */
        final Terrain[] humanTerrain = new Terrain[] {
                        theNeighbors.get(getDirection()),
                        theNeighbors.get(humanOrig.left()),
                        theNeighbors.get(humanOrig.right())};
        
        //Check for valid direction and terrain (grass).
        if (isGrass(humanTerrain) && !isCrosswalk(humanTerrain)) {
            /*
             * Choose a random direction until a valid option is chosen.
             * Prevents reverse.
             */
            while (theNeighbors.get(humanDir) != Terrain.GRASS
                            || humanDir == getDirection().reverse()) {
                humanDir = Direction.random();
            }
            
        //Check for valid direction and terrain (cross-walk).
        } else if (isCrosswalk(humanTerrain)) {
            //Ensures will always face cross-walk if present
            while (theNeighbors.get(humanDir) != Terrain.CROSSWALK) {
                humanDir = Direction.random();
            }
        //If no valid direction and terrain is available, reverse.
        } else {
            humanDir = humanOrig.reverse();
        } 
        return humanDir;
    }
    
    /**
     * Helper method for chooseDirection.
     * Checks for grass in three directions, straight, left, right.
     * @param theTerrain The neighboring terrain.
     * @return True if grass is present in any of the three directions,
     * false otherwise.
     */
    private boolean isGrass(final Terrain[] theTerrain) {
        boolean isGrass = false;
        //Check for grass straight, left, right.
        if (theTerrain[0] == Terrain.GRASS || theTerrain[1] == Terrain.GRASS
                        || theTerrain[2] == Terrain.GRASS) {
            isGrass = true;
        }
        return isGrass;
    }
    
    /**
     * Helper method for chooseDirection.
     * Checks for a cross-walk in three directions, straight, left, right.
     * @param theTerrain The neighboring terrain.
     * @return True if a cross-walk is present in any of the three directions,
     * false otherwise.
     */
    private boolean isCrosswalk(final Terrain[] theTerrain) {
        boolean isCrosswalk = false;
        //Check for cross-walk straight, left, right.
        if (theTerrain[0] == Terrain.CROSSWALK 
                        || theTerrain[1] == Terrain.CROSSWALK
                        || theTerrain[2] == Terrain.CROSSWALK) {
            isCrosswalk = true;
            
        }
        return isCrosswalk;
    }
}
