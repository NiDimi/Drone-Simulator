package nick.dimitriou.objects;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Junit test for the bird class
 * @author Nick Dimitriou
 */
public class BirdTest {
    /**
     * testing that the getType method works correctly
     */
    @Test
    public void getType() {
        Bird bird = new Bird(10,10,5);// creating a bird at radnomg possition
        assertEquals("Bird", bird.getType());// checking the return is the same
    }
}