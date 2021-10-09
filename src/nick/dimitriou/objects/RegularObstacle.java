package nick.dimitriou.objects;

/**
 * Class for a regular obstacle
 * @author Nick Dimitriou
 */
public class RegularObstacle extends MapObject{
    private static int counter = 0;// to count the number of obstacles

    /**
     * Create a regular obstacle at the position x,y and of the size rad
     * @param x the width position
     * @param y the height position
     * @param rad the size
     */
    public RegularObstacle(double x, double y, double rad) {
        super(x, y, rad, 'g');
        ID = counter++;// add the counter for the unique identifier and add one to the count
    }


    /**
     * return the type of the obstacle
     * @return
     */
    @Override
    public String getType() {
        return "Regular Obstacle";
    }


    /**
     * reset the obstacle counter
     */
    protected static void resetObstacleCounter(){
        counter =0;

    }
}
