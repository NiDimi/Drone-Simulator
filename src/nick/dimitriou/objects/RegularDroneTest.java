package nick.dimitriou.objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit tests for the RegularDrone class
 * @author Nick Dimitriou
 */
public class RegularDroneTest {
    private RegularDrone regularDrone;

    /**
     * initializaing the regularDrone each time before a method
     */
    @Before
    public void setUp(){
        regularDrone = new RegularDrone(10,10,10,5);
    }

    /**
     * testing that the isHitObstacle method returns the correct value
     */
    @Test
    public void isHitObstacle() {
        assertFalse(regularDrone.isHitObstacle());// the variable should be initialized to false
    }

    /**
     * testing that the setHitObstacle method returns the correct value
     */
    @Test
    public void setHitObstacle() {
        regularDrone.setHitObstacle(true);// setting the varibale to true
        assertTrue(regularDrone.isHitObstacle());// testing if the assignment actually worked
        regularDrone.setHitObstacle(false);// setting the varibale to false
        assertFalse(regularDrone.isHitObstacle());// testing if the assignment actually worked
    }

    /**
     * testing that the getType method returns the string Regular Drone
     */
    @Test
    public void getType() {
        assertEquals("Regular Drone", regularDrone.getType());
    }
}