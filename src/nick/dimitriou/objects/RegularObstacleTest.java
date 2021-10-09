package nick.dimitriou.objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit tests for the Regular Obstacle class
 * @author Nick Dimitriou
 */
public class RegularObstacleTest {
    private RegularObstacle regularObstacle;

    /**
     * method to initialize the regularObstacle before each method
     */
    @Before
    public void setUp(){
        regularObstacle = new RegularObstacle(10,10,10);
    }

    /**
     * checking that the ID assignment is correct
     */
    @Test
    public void IDAssignment(){
        assertEquals(1,regularObstacle.getObjectID(),0);
    }

    /**
     * testing that the getType method returns the Regular Obstacle
     */
    @Test
    public void getType() {
        assertEquals("Regular Obstacle", regularObstacle.getType());
    }

    /**
     * testing that the resetObstacleCounter resets correctly the counter
     */
    @Test
    public void resetObstacleCounter() {
        RegularObstacle.resetObstacleCounter();// reseting teh counter
        regularObstacle = new RegularObstacle(10,10,10);// adding one new obstacle
        assertEquals(0,regularObstacle.getObjectID(),0);// checking taht the obstacvle got the a zero ID
    }
}