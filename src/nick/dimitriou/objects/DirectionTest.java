package nick.dimitriou.objects;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

/**
 * JUnit tests for direction enumerator
 * @author Nick Dimitriou
 */
@RunWith(Parameterized.class)
public class DirectionTest {
    private Direction direction;
    private Direction oppositeDirection;

    /**
     * constructor for the parameterized tests
     * @param direction the direction in use
     * @param oppositeDirection the oppostie of the direction in use
     */
    public DirectionTest(Direction direction, Direction oppositeDirection) {
        this.direction = direction;
        this.oppositeDirection = oppositeDirection;
    }

    /**
     * setting up the correct values
     * @return a list with the direction and the opposite of this direction
     */
    @Parameterized.Parameters
    public static Collection<Object[]> testConditions(){
        return Arrays.asList(new Object[][]{
                {Direction.EAST, Direction.WEST},
                {Direction.SOUTHEAST, Direction.NORTHWEST},
                {Direction.SOUTH, Direction.NORTH},
                {Direction.SOUTHWEST, Direction.NORTHEAST}
        });
    }

    /**
     * testing the getOppsite method
     */
    @Test
    public void getOpposite(){
        assertEquals(oppositeDirection, direction.getOpposite());
    }

    /**
     * testing the add method
     */
    @Test
    public void add(){
        assertEquals(Direction.EAST.add(2), Direction.SOUTH);
        assertEquals(Direction.WEST.add(-2), Direction.SOUTH);
        assertEquals(Direction.NORTH.add(8), direction.NORTH);
    }
}