/*
 * TCSS 305
 * Spring 2016
 * assignment 3 - easystreet
 */
package model;

/**
 * Vehicle parent class.
 * 
 * @author Todd Crane
 * @version 4/23/2016
 */
public abstract class AbstractVehicle implements Vehicle {    
    /** The x coordinate of this vehicle. */
    private int myVehicleX;
    /** The y coordinate of this vehicle. */
    private int myVehicleY;
    /** The direction of this vehicle. */
    private Direction myDirection;
    /** The location of this vehicle image. */
    private final String myImageName;
    /** The starting x coordinate of this vehicle. */
    private final int myStartVehicleX;
    /** The starting y coordinate of this vehicle. */
    private final int myStartVehicleY;
    /** The starting direction of this vehicle. */
    private final Direction myStartDirection;
    /** Number of moves this vehicle stays dead. */
    private final int myDeathTime;
    /** Number of moves this vehicle has been dead. */
    private int myDeathTimer;
    /** String builder for this vehicle. */
    private StringBuilder mySB;
    
    /**
     * Initialize the instance fields.
     * 
     * @param theVehicleX the x coordinate of this vehicle.
     * @param theVehicleY the y coordinate of this vehicle.
     * @param theDirection the direction of this vehicle.
     * @param theDeathTime the number of moves this vehicle stays dead. 
     */
    public AbstractVehicle(final int theVehicleX, final int theVehicleY,
                           final Direction theDirection, final int theDeathTime) {
        
        myVehicleX = theVehicleX;
        myVehicleY = theVehicleY;
        myDirection = theDirection;
        
        myStartVehicleX = theVehicleX;
        myStartVehicleY = theVehicleY;
        myStartDirection = theDirection;
        
        myDeathTime = theDeathTime;
        myImageName = getClass().getSimpleName().toLowerCase();
        myDeathTimer = theDeathTime;
        
    }

    /* (non-Javadoc)
     * @see model.Vehicle#collide(model.Vehicle)
     */
    
    /**
     * Called when this vehicle collides with the specified other vehicle.
     * 
     * @param theOther The other vehicle.
     */
    @Override
    public void collide(final Vehicle theOther) {

        /*
         * Check that both are alive and determine if this vehicle
         * dies from the collision.
         */
        if ((isAlive() && theOther.isAlive()) 
                        && (myDeathTime > theOther.getDeathTime())) {
            myDeathTimer = 0;
        }
    }

    /* (non-Javadoc)
     * @see model.Vehicle#getDeathTime()
     */

    /**
     * Returns the number of moves this vehicle stays dead.
     * 
     * @return The number of moves this vehicle stays dead.
     */
    @Override
    public int getDeathTime() {
        return myDeathTime;
    }

    /* (non-Javadoc)
     * @see model.Vehicle#getImageFileName()
     */
    
    /**
     * Returns the file name of the image for this vehicle, such as
     * "car.gif".
     * Returns a different name for a dead vehicle.
     * 
     * @return The image file name.
     */
    @Override
    public String getImageFileName() {
        mySB = new StringBuilder();
        //Image file name if vehicle is alive.
        if (isAlive()) {
            mySB.append(myImageName);
            mySB.append(".gif");
        //Image file name if vehicle is dead.
        } else {
            mySB.append(myImageName);
            mySB.append("_dead.gif");
        }
        return mySB.toString();
    }

    /* (non-Javadoc)
     * @see model.Vehicle#getDirection()
     */
    
    /**
     * Returns this vehicle's direction.
     * 
     * @return The direction.
     */
    @Override
    public Direction getDirection() {
        return myDirection;
    }

    /* (non-Javadoc)
     * @see model.Vehicle#getX()
     */
    
    /**
     * Returns the x coordinate of this vehicle.
     * 
     * @return The x-coordinate.
     */
    @Override
    public int getX() {
        return myVehicleX;
    }

    /* (non-Javadoc)
     * @see model.Vehicle#getY()
     */
    
    /**
     * Returns the y coordinate of this vehicle.
     * @return The y coordinate.
     */
    @Override
    public int getY() {
        return myVehicleY;
    }

    /* (non-Javadoc)
     * @see model.Vehicle#isAlive()
     */
    
    /**
     * Returns whether this vehicle is alive and should move on the map.
     * 
     * @return True if the vehicle is alive, false otherwise.
     */
    @Override
    public boolean isAlive() {
        boolean isAlive = true;
        //Check vehicle's death time against the timer.
        if (myDeathTime != myDeathTimer) {
            isAlive = false;
        }
        return isAlive;
    }

    /* (non-Javadoc)
     * @see model.Vehicle#poke()
     */
    
    /**
     * Called by the UI to notify a dead vehicle that 1 movement round has
     * passed, so that it will become 1 move closer to revival.
     */
    @Override
    public void poke() {
        //Set random direction when vehicle revives.
        if (isAlive()) {
            myDirection = Direction.random();
        } else {
            myDeathTimer++;
        }
    }

    /* (non-Javadoc)
     * @see model.Vehicle#reset()
     */
    
    /**
     * Moves this vehicle back to its original position.
     */
    @Override
    public void reset() {
        myVehicleX = myStartVehicleX;
        myVehicleY = myStartVehicleY;
        myDirection = myStartDirection;
        myDeathTimer = myDeathTime;
    }

    /* (non-Javadoc)
     * @see model.Vehicle#setDirection(model.Direction)
     */
    
    /**
     * Sets the direction of this vehicle.
     * 
     * @param theDir The new direction.
     */
    @Override
    public void setDirection(final Direction theDir) {
        myDirection = theDir;
    }

    /* (non-Javadoc)
     * @see model.Vehicle#setX(int)
     */
    
    /**
     * Sets the x coordinate of this vehicle.
     * 
     * @param theX The new x coordinate.
     */
    @Override
    public void setX(final int theX) {
        myVehicleX = theX;
    }

    /* (non-Javadoc)
     * @see model.Vehicle#setY(int)
     */
    
    /**
     * Sets the y coordinate of this vehicle.
     * 
     * @param theY The new y coordinate.
     */
    @Override
    public void setY(final int theY) {
        myVehicleY = theY;
    }
    
    /**
     * {@inheritDoc}
     * 
     * The string representation of this vehicle.
     */
    @Override
    public String toString() {
        mySB = new StringBuilder();
        mySB.append(getClass().getSimpleName());
        return mySB.toString();
    }
}
