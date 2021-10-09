package nick.dimitriou.objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * JUnit tests for the MapObject class
 * @author Nick Dimitriou
 */
public class MapObjectTest {
    private MapObject mapObject;

    /**
     * initializing the mapobject every time before the methods are called
     */
    @Before
    public void setUp(){
        mapObject =  new MapObject(10.32,11.67,9.87,'r') {
            @Override
            public String getType() {// returning null it will be tested on the other methods
                return null;
            }
        };
    }

    /**
     * testing that the method getX returns the correct X
     */
    @Test
    public void getX() {
        assertEquals(10.32,mapObject.getX(),.01);// leaving a bit of room for differnt types of computers
    }

    /**
     * testing that the method getY returns the correct Y
     */
    @Test
    public void getY() {
        assertEquals(11.67,mapObject.getY(),.01);// leaving a bit of room for differnt types of computers
    }

    /**
     * testing that the method getRad returns the correct Rad
     */
    @Test
    public void getRad() {
        assertEquals(9.87,mapObject.getRad(),.01);// leaving a bit of room for differnt types of computers
    }

    /**
     * testing that the setXY methods set the correct X and Y
     */
    @Test
    public void setXY() {
        mapObject.setXY(10.24, 87.45);
        assertEquals(10.24, mapObject.getX(),.01);
        assertEquals(87.45,mapObject.getY(),.01);// leaving a bit of room for differnt types of computers
    }
}