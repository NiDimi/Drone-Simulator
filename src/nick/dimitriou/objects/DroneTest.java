package nick.dimitriou.objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit tests for the Drone class
 * @author Nick Dimitriou
 */
public class DroneTest {

    private Drone drone;

    /**
     * method to initialize the drone after each method
     */
    @Before
    public void setUp(){
        drone = new Drone(9.8,8.7,10,4,'r') {
            @Override
            public String getType() {// leaving it null it will be tested letter
                return null;
            }
        };
    }

    /**
     * testing the resetDroneCounter method works correctly
     */
    @Test
    public void resetDroneCounter() {
        // because its time we call a method we create a new drone due to the setUp method
        // the drone counter will have increase
        Drone.resetDroneCounter();// so if we reset it
        Drone drone1 = new Drone(10,10,10,4,'r') {// and create a new drone
            @Override
            public String getType() {
                return null;
            }
        };
        assertEquals(0,drone1.getObjectID(),0);// the new drones id should be 0
    }

    /**
     * testing that the hitting method works correctly
     */
    @Test
    public void hitting() {
        //doing multiple test to check the hitting method
        assertTrue(drone.hitting(9.8,8.7,10));
        assertFalse(drone.hitting(300,400,10));
        assertFalse(drone.hitting(9.8,400,10));
        assertFalse(drone.hitting(300,8.7,10));
    }

    /**
     * testing that the getDroneSpeed method returns the correct speed
     */
    @Test
    public void getDroneSpeed() {
        assertEquals(4,drone.getDroneSpeed(),0);
    }
}