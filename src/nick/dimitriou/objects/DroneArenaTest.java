package nick.dimitriou.objects;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Junit tests for the DroneArena class
 * @author Nick Dimitirou
 */
public class DroneArenaTest {
    private static DroneArena droneArena;

    /**
     * method to initialize the arena at the start
     */
    @BeforeClass
    public static void setUp(){
        droneArena = new DroneArena(100,200);
    }

    /**
     * testing that the method to get the X works correctly
     */
    @Test
    public void getX() {
        assertEquals(100,droneArena.getX(),0);
    }

    /**
     * testing that the method to get the Y works correctly
     */
    @Test
    public void getY() {
        assertEquals(200, droneArena.getY(),0);
    }

    /**
     * testing that the method to add a regular drone works correctly
     */
    @Test
    public void addRegular(){
        assertFalse(droneArena.addRegular());
        droneArena.clearArena();
        assertTrue(droneArena.addRegular());
    }

    /**
     * testing that the method to add a stupid drone works correctly
     */
    @Test
    public void addStupid(){
        assertFalse(droneArena.addStupid());
        droneArena.clearArena();
        assertTrue(droneArena.addStupid());
    }

    /**
     * testing that the method to add a shielded drone works correctly
     */
    @Test
    public void addShielded(){
        assertFalse(droneArena.addShielded());
        droneArena.clearArena();
        assertTrue(droneArena.addStupid());
    }

    /**
     * testing that the getNumOfRegularObstacles method returns the correct number of obstacles
     */
    @Test
    public void getNumOfRegularObstacles(){
        assertEquals(0,droneArena.getNumOfRegularObstacles(),0);
    }
}