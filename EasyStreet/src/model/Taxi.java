/*
 * TCSS 305
 * Spring 2016
 * assignment 3 - easystreet
 */
package model;

import java.util.Map;

/**
 * Class for taxi.
 * 
 * @author Todd Crane
 * @version 4/23/2016
 */
public class Taxi extends AbstractVehicle {
    
    /** The death time of this vehicle. */
    private static final int DEATH_TIME = 10;

    /**
     * Constructor for the Taxi class.
     * 
     * @param theVehicleX The x coordinate of this taxi.
     * @param theVehicleY The y coordinate of this taxi.
     * @param theDir The direction of this taxi.
     */
    public Taxi(final int theVehicleX, final int theVehicleY, 
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
        if (theTerrain == Terrain.STREET || theTerrain == Terrain.CROSSWALK) {
            canPass = true;
        //Check for valid terrain and light combination.
        } else if (theTerrain == Terrain.LIGHT && theLight != Light.RED) {
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
        //Direction to be returned, set to straight.
        Direction taxiDir = getDirection();
        /*
         * Array for neighboring terrain.
         * 0 - Straight
         * 1 - Left
         * 2 - Right
         */
        final Terrain[] taxiTerrain = new Terrain[] {
                        theNeighbors.get(getDirection()),
                        theNeighbors.get(taxiDir.left()),
                        theNeighbors.get(taxiDir.right())};

        //Straight is valid.
        if (validTerrain(taxiTerrain[0])) {
            taxiDir = getDirection();
        //Left is valid.
        } else if (validTerrain(taxiTerrain[1])) {
            taxiDir = taxiDir.left(); 
        //Right is valid.
        } else if (validTerrain(taxiTerrain[2])) {
            taxiDir = taxiDir.right();
        //Reverse as last resort.
        } else {
            taxiDir = taxiDir.reverse();
        }
        return taxiDir;
    }
       
    /**
     * Helper method for chooseDirection.
     * Checks the passed terrain to see if it is a valid terrain type.
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
