/*
 * TCSS 305
 * Spring 2016
 * assignment 1 - testing
 */
package test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import shapes.Circle;

/**
 * Tests the Circle class.
 * 
 * @author Todd Crane
 * @version 4/8/2016
 */
public class CircleTest {
    
    /** A tolerance used when comparing double values for equality. */
    private static final double TOLERANCE = .000001;
    
    /** A Circle to use in the tests. */
    //The test fixture that will be used to run tests.
    private Circle myCircle;
    
    /** A method to initialize the test fixture before each test. */
    //This method runs before each test method.
    @Before
    public void setUp() { //No need to throw any exceptions.
        myCircle = new Circle();        
    }
    
    /**
     * Test method for {@link Circle#Circle()}.
     */
    //Tests the default constructor for each default value.
    @Test
    public void testDefaultConstructor() {
        assertEquals(1.0, myCircle.getRadius(), TOLERANCE);
        assertEquals(new Point(0, 0), myCircle.getCenter());
        assertEquals(Color.BLACK, myCircle.getColor());
    }

    /**
     * Test method for {@link Circle#Circle(double, java.awt.Point, java.awt.Color)}.
     */
    //Tests the overloaded constructor for each passed value.
    @Test
    public void testOverloadedConstructor() {
        //Non default circle with passed values.
        final Circle c1 = new Circle(5.0, new Point(50, 50), Color.CYAN);
        
        assertEquals(5.0, c1.getRadius(), TOLERANCE);
        assertEquals(new Point(50, 50), c1.getCenter());
        assertEquals(Color.CYAN, c1.getColor());
    }
    
    /**
     * Test method for {@link Circle#Circle(double, java.awt.Point, java.awt.Color)}
     * for a negative radius.
     */
    //Tests the overloaded constructor for a negative radius.
    @Test(expected = IllegalArgumentException.class)
    public void testOverloadedConstructorNullNegativeRadius() {
        new Circle(-5.0, new Point(0, 0), Color.RED);
    }
    
    /**
     * Test method for {@link Circle#Circle(double, java.awt.Point, java.awt.Color)}
     * for a zero radius.
     */
    //Tests the overloaded constructor for a zero radius.
    @Test(expected = IllegalArgumentException.class)
    public void testOverloadedConstructorNullZeroRadius() {
        new Circle(0.0, new Point(0, 0), Color.RED);
    }
    
    /**
     * Test method for {@link Circle#Circle(double, java.awt.Point, java.awt.Color)}
     * for a null point.
     */
    //Tests the overloaded constructor for a null point.
    @Test(expected = NullPointerException.class)
    public void testOverloadedConstructorNullPoint() {
        new Circle(1.0, new Point(null), Color.RED);
    }
    
    /**
     * Test method for {@link Circle#Circle(double, java.awt.Point, java.awt.Color)}
     * for a null color.
     */
    //Tests the overloaded constructor for a null color.
    @Test(expected = NullPointerException.class)
    public void testOverloadedConstructorNullColor() {
        new Circle(1.0, new Point(0, 0), null);
    }

    /**
     * Test method for {@link Circle#setRadius(double)}.
     */
    //Tests setting the radius.
    @Test
    public void testSetRadius() {
        myCircle.setRadius(8.0);
        assertEquals("setRadius failed to set the radius value correctly.",
                     8.0, myCircle.getRadius(), TOLERANCE);
    }
    
    /**
     * Test method for {@link Circle#setRadius(double)} for a negative radius.
     */
    //Tests setting the radius to a negative value.
    @Test(expected = IllegalArgumentException.class)
    public void testSetRadiusNull() {
        myCircle.setRadius(-5.0);
    }

    /**
     * Test method for {@link Circle#setCenter(java.awt.Point)}.
     */
    //Tests setting the center.
    @Test
    public void testSetCenter() {
        myCircle.setCenter(new Point(80, 80));
        assertEquals("setCenter failed to set the center correctly.",
                     new Point(80, 80), myCircle.getCenter());
    }
    
    /**
     * Test method for {@link Circle#setCenter(java.awt.Point)}
     * for a null point.
     */
    //Tests setting the center to a null point.
    @Test(expected = NullPointerException.class)
    public void testSetCenterNull() {
        myCircle.setCenter(new Point(null));
    }

    /**
     * Test method for {@link Circle#setColor(java.awt.Color)}.
     */
    //Tests setting the color.
    @Test
    public void testSetColor() {
        myCircle.setColor(Color.RED);
        assertEquals("setColor failed to set the color value correctly.",
                     Color.RED, myCircle.getColor());
    }
    
    /**
     * Test method for {@link Circle#setColor(java.awt.Color)}
     * for a null color.
     */
    //Tests setting the color to a null value.
    @Test(expected = NullPointerException.class)
    public void testSetColorNull() {
        myCircle.setColor(null);
        
    }

    /**
     * Test method for {@link Circle#calculateDiameter()}
     * for a default and non default circle.
     */
    //Tests calculating diameter of both a default and non default circle.
    @Test
    public void testCalculateDiameter() {
        assertEquals("testCalculateDiameter on default failed.", 
                     2 * myCircle.getRadius(), 
                     myCircle.calculateDiameter(), TOLERANCE);
        
        //Non default circle.
        final Circle c1 = new Circle(8.0, new Point(0, 0), Color.RED);
        assertEquals("testCalculateDiameter on new failed.", 
                   2 * c1.getRadius(), c1.calculateDiameter(), TOLERANCE);
    }

    /**
     * Test method for {@link Circle#calculateCircumference()}
     * for a default and non default circle.
     */
    //Tests calculating circumference of both a default and non default circle.
    @Test
    public void testCalculateCircumference() {
        //6.28318531
        assertEquals("testCalculateCircumference on default failed.",
                     2 * myCircle.getRadius() * Math.PI, 
                     myCircle.calculateCircumference(), TOLERANCE);
    
        //Non default circle.
        final Circle c1 = new Circle(8.0, new Point(0, 0), Color.RED);
        assertEquals("testCalculateCircumference on new failed.",
                     2 * c1.getRadius() * Math.PI, 
                     c1.calculateCircumference(), TOLERANCE);
    }

    /**
     * Test method for {@link Circle#calculateArea()}
     * for a default and non default circle.
     */
    //Tests calculating area of both a default and non default circle.
    @Test
    public void testCalculateArea() {
        assertEquals("testCalculateArea on default failed.",
                     Math.pow(myCircle.getRadius(), 2) * Math.PI, 
                     myCircle.calculateArea(), TOLERANCE);
        
        //Non default circle.
        final Circle c1 = new Circle(8.0, new Point(0, 0), Color.RED);
        assertEquals("testCalculateArea on new failed.", 
                     Math.pow(c1.getRadius(), 2) * Math.PI, 
                     c1.calculateArea(), TOLERANCE);
    }

    /**
     * Test method for {@link Circle#toString()}.
     */
    //Tests for the correct output from toString.
    @Test
    public void testToString() {
        assertEquals("toString produced an unexpected result.",
                     "Circle [radius=1.00, center=java.awt.Point[x=0,y=0], "
                     + "color=java.awt.Color[r=0,g=0,b=0]]",
                     myCircle.toString());
    }
}
