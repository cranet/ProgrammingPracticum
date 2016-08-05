/*
 * TCSS 305
 * Spring 2016
 * assignment 3 - easystreet
 */
package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * Unit tests for class Truck.
 * 
 * @author Todd Crane
 * @version 4/23/2016
 *
 */
public class TruckTest {
    
    /**
     * The number of times to repeat at test to have a high probability
     * that all random possibilities have been explored.
     */
    private static final int TRIES_FOR_RANDOM = 50;

    /** Test method for Truck constructor. */
    @Test
    public void testTruckConstructor() {
        final Truck t = new Truck(10, 11, Direction.NORTH);
        
        assertEquals("Truck x coordinate not initialized correctly!", 10, 
                     t.getX());
        assertEquals("Truck y coordinate not initialized correctly!", 11, 
                     t.getY());
        assertEquals("Truck direction not initialized correctly!",
                     Direction.NORTH, t.getDirection());
        assertEquals("Truck death time not initialized correctly!", 0, 
                     t.getDeathTime());
        assertTrue("Truck isAlive() fails initially!", t.isAlive());
    }

    /** Test method for Truck setters. */
    @Test
    public void testTruckSetters() {
        final Truck t = new Truck(10, 11, Direction.NORTH);
        
        t.setX(12);
        assertEquals("Truck setX failed!", 12, t.getX());
        t.setY(13);
        assertEquals("Truck setY failed!", 13, t.getY());
        t.setDirection(Direction.SOUTH);
        assertEquals("Truck setDirection failed!", Direction.SOUTH, 
                     t.getDirection());
    }
    
    /**
     * Test method for {@link Truck#canPass(Terrain, Light)}.
     */
    @Test
    public void testCanPass() {
        /*
         * Trucks can move on streets, lights, and cross-walks.
         * 
         * Trucks should not move to other terrain types.
         * 
         * Trucks should only reverse as a last resort.
         */
        
        final List<Terrain> validTerrain = new ArrayList<>();
        validTerrain.add(Terrain.STREET);
        validTerrain.add(Terrain.CROSSWALK);
        
        final Truck t = new Truck(0, 0, Direction.NORTH);
        //Test each terrain type as a destination.
        for (final Terrain destinationTerrain : Terrain.values()) {
            //Try the test under each light condition.
            for (final Light currentLightCondition : Light.values()) {
                if (destinationTerrain == Terrain.STREET) {
                    
                    //Trucks can drive on street under any condition.
                    assertTrue("Truck should be able to pass STREET"
                               + ", with light " + currentLightCondition,
                               t.canPass(destinationTerrain, 
                                         currentLightCondition));
                } else if (destinationTerrain == Terrain.CROSSWALK) {
                    //Trucks can pass cross-walk on yellow or green
                    
                    if (currentLightCondition == Light.RED) {
                        assertFalse("Truck should NOT be able to pass " 
                                    + destinationTerrain + ", with light "
                                    + currentLightCondition,
                                    t.canPass(destinationTerrain,
                                              currentLightCondition));
                    } else { //Light is yellow or green.
                        assertTrue("Truck should be able to pass " 
                                   + destinationTerrain + ", with light "
                                   + currentLightCondition,
                                   t.canPass(destinationTerrain, 
                                             currentLightCondition));
                    }
                } 
            }
        }
    }

    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionSurroundedByStreet() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.STREET);
        neighbors.put(Direction.NORTH, Terrain.STREET);
        neighbors.put(Direction.EAST, Terrain.STREET);
        neighbors.put(Direction.SOUTH, Terrain.STREET);
        
        boolean seenWest = false;
        boolean seenNorth = false;
        boolean seenEast = false;
        boolean seenSouth = false;
        
        final Truck t = new Truck(0, 0, Direction.NORTH);
        
        for (int count = 0; count < TRIES_FOR_RANDOM; count++) {
            final Direction d = t.chooseDirection(neighbors);
            
            if (d == Direction.WEST) {
                seenWest = true;
            } else if (d == Direction.NORTH) {
                seenNorth = true;
            } else if (d == Direction.EAST) {
                seenEast = true;
            } else if (d == Direction.SOUTH) { // this should NOT be chosen
                seenSouth = true;
            }
        }
 
        assertTrue("Truck chooseDirection() fails to select randomly "
                   + "among all possible valid choices!",
                   seenWest && seenNorth && seenEast);
            
        assertFalse("True chooseDirection() reversed direction "
                        + "when not necessary!", seenSouth);
    }
    
    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirectionMustReverse() {
        
        for (final Terrain terrain : Terrain.values()) {
            if (terrain == Terrain.GRASS) {
                
                final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
                neighbors.put(Direction.WEST, Terrain.GRASS);
                neighbors.put(Direction.NORTH, Terrain.GRASS);
                neighbors.put(Direction.EAST, Terrain.GRASS);
                neighbors.put(Direction.SOUTH, Terrain.STREET);
                
                final Truck t = new Truck(0, 0, Direction.NORTH);
                
                // the Truck must reverse and go SOUTH
                assertEquals("Truck chooseDirection() failed "
                                + "when reverse was the only valid choice!",
                             Direction.SOUTH, t.chooseDirection(neighbors));
            } 
        }
    }
}
