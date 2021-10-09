package nick.dimitriou.GUI;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Junit test for the statusTimer class
 * @author Nick Dimitriou
 */
public class StatusTimerTest {
    private StatusTimer statusTimer;

    /**
     * method to call each time before the other methods to set up the timer
     */
    @Before
    public void setUp(){
        statusTimer = new StatusTimer() {// creating a new status timer before each class is run
            @Override
            public void handle(long l) {// leaving it empty no reason to have context

            }
        };
    }

    /**
     * checking the start method works correctly
     */
    @Test
    public void start() {
        statusTimer.start();// starting the timer
        assertTrue(statusTimer.isRunning());// the state of teh running boolean should be true
    }

    /**
     * checking the stop methods work correctly
     */
    @Test
    public void stop() {
        statusTimer.stop();// stoping the timer
        assertFalse(statusTimer.isRunning());// the state of the running boolean should be false
    }

}