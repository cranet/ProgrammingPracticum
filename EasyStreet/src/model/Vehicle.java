/*
 * TCSS 305 - Easy Street
 */

package model;

import java.util.Map;

/**
 * An interface for objects that can move in four directions.
 * 
 * @author Marty Stepp
 * @author Daniel M. Zimmerman
 * @author Alan Fowler (acfowler@u.washington.edu)
 * @version 1.1
 */
public interface Vehicle {

    /**
     * Returns whether or not this object may move onto the given type of
     * terrain, when the street lights are the given color.
     * 
     * @param theTerrain The terrain.
     * @param theLight The light color.
     * @return whether or not this object may move onto the given type of
     *         terrain when the street lights are the given color.
     */
    boolean canPass(Terrain theTerrain, Light theLight);

    /**
     * Returns the direction this object would like to move, based on the given
     * map of the neighboring terrain.
     * 
     * @param theNeighbors The map of neighboring terrain.
     * @return the direction this object would like to move.
     */
    Direction chooseDirection(Map<Direction, Terrain> theNeighbors);

    /**
     * Called when this Vehicle collides with the specified other Vehicle.
     * 
     * @param theOther The other object.
     */
    void collide(Vehicle theOther);

    /**
     * Returns the number of updates between this vehicle's death and when it
     * should be revived.
     * 
     * @return the number of updates.
     */
    int getDeathTime();

    /**
     * Returns the file name of the image for this Vehicle object, such as
     * "car.gif".
     * 
     * @return the file name.
     */
    String getImageFileName();

    /**
     * Returns this Vehicle object's direction.
     * 
     * @return the direction.
     */
    Direction getDirection();

    /**
     * Returns this Vehicle object's x-coordinate.
     * 
     * @return the x-coordinate.
     */
    int getX();

    /**
     * Returns this Vehicle object's y-coordinate.
     * 
     * @return the y-coordinate.
     */
    int getY();

    /**
     * Returns whether this Vehicle object is alive and should move on the map.
     * 
     * @return true if the object is alive, false otherwise.
     */
    boolean isAlive();

    /**
     * Called by the UI to notify a dead vehicle that 1 movement round has
     * passed, so that it will become 1 move closer to revival.
     */
    void poke();

    /**
     * Moves this vehicle back to its original position.
     */
    void reset();

    /**
     * Sets this object's facing direction to the given value.
     * 
     * @param theDir The new direction.
     */
    void setDirection(Direction theDir);

    /**
     * Sets this object's x-coordinate to the given value.
     * 
     * @param theX The new x-coordinate.
     */
    void setX(int theX);

    /**
     * Sets this object's y-coordinate to the given value.
     * 
     * @param theY The new y-coordinate.
     */
    void setY(int theY);

} // end of interface Vehicle

