/*
 * TCSS 305
 * Spring 2016
 * assignment 3 - easystreet
 */
package model;

import java.util.Map;

/**
 * Class for truck.
 * 
 * @author Todd Crane
 * @version 4/23/2016
 */
public class Truck extends AbstractVehicle {
    
    /** The death time of this vehicle. */
    private static final int DEATH_TIME = 0;

    /**
     * Constructor for the Truck class.
     * 
     * @param theVehicleX The x coordinate of this truck.
     * @param theVehicleY The y coordinate of this truck.
     * @param theDir The direction of this truck.
     */
    public Truck(final int theVehicleX, final int theVehicleY, 
                 final Direction theDir) {
        super(theVehicleX, theVehicleY, theDir, DEATH_TIME);
    }
    
    /* (non-Javadoc)
     * @see model.Vehicle#canPass(model.Terrain, model.Light)
     */
    
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
        //Check valid terrain
        if (theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT) {
            canPass = true;
        //Check for valid cross-walk and light combination.
        } else if (theTerrain == Terrain.CROSSWALK && theLight != Light.RED) {
            canPass = true;  
        }
        return canPass;
    }

    /* (non-Javadoc)
     * @see model.Vehicle#chooseDirection(java.util.Map)
     */
    
    /**
     * Returns the direction this object would like to move, based on the given
     * map of the neighboring terrain.
     * 
     * @param theNeighbors The map of neighboring terrain.
     * @return The direction this object would like to move.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> 
        theNeighbors) {
        //Direction to be returned, set to random.
        Direction truckDir = Direction.random();
        //Original direction for reverse.
        final Direction truckOrig = getDirection();
        
        //Check for valid direction and terrain.
        if (dirCheck(theNeighbors)) {
            /*
             * Choose a random direction until a valid option is chosen.
             * Prevents reversing.
             */
            while (theNeighbors.get(truckDir) != Terrain.STREET 
                            && theNeighbors.get(truckDir) != Terrain.LIGHT
                            && theNeighbors.get(truckDir) != Terrain.CROSSWALK
                            || truckDir == getDirection().reverse()) {
                
                truckDir = Direction.random();
            }
        //If no valid direction and terrain is available, reverse.
        } else {
            truckDir = truckOrig.reverse();
        }
        return truckDir;
    }
    
    /**
     * Helper method for chooseDirection.
     * Checks the terrain next to the vehicle in three directions:
     * left, straight, right.
     * @param theNeighbors The map of the neighboring terrain.
     * @return True if the vehicle can move in any of the three
     * directions, false otherwise.
     */
    private boolean dirCheck(final Map<Direction, Terrain> theNeighbors) {
        boolean dirCheck = false;
        //Check left, straight, right.
        if (validTerrain(theNeighbors.get(getDirection().left()))
                        || validTerrain(theNeighbors.get(getDirection()))
                        || validTerrain(theNeighbors.get
                                        (getDirection().right()))) {
            dirCheck = true;
        }
        return dirCheck;
    }
    
    /**
     * Helper method for dirCheck.
     * Checks the passed Terrain to see if it is a valid terrain type.
     * @param theTerrain The terrain.
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
