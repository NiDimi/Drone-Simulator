package nick.dimitriou.objects;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit test for teh stupid drone class
 * @author Nick Dimitriou
 */
public class StupidDroneTest {
    /**
     * testing that the getType returns stupid Drone
     */
    @Test
    public void getType() {
        StupidDrone stupidDrone = new StupidDrone(10,10,10,5);
        assertEquals("Stupid Drone", stupidDrone.getType());
    }
}