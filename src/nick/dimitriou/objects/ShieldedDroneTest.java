package nick.dimitriou.objects;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * JUnit test for the ShieldDrone class
 * @author Nick Dimitriou
 */
public class ShieldedDroneTest {
    /**
     * testing that the getType method returns Shielded Drone
     */
    @Test
    public void getType() {
        ShieldedDrone shieldedDrone = new ShieldedDrone(10,10,10,5);
        assertEquals("Shielded Drone", shieldedDrone.getType());
    }
}