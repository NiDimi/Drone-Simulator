package nick.dimitriou.objects;

/**
 * Class for the bird obstacle
 * @author Nick Dimitriou
 */
public class Bird extends RegularObstacle {
    private double speed;// the speed that the bird will move
    private final Direction DIRECTION;// the direction that the bird will move

    /**
     * Create a bird at the position x,y of the size rad
     * @param x the width position
     * @param y the height position
     * @param rad the size
     */
    public Bird(double x, double y, double rad) {
        super(x, y, rad);
        speed = 10;
        DIRECTION = Direction.EAST;
        col = 'p';
    }

    /**
     * move the drone based on the speed and the direction
     */
    public void adjustBird(){
            double radAngle = DIRECTION.getAngle() * Math.PI/180;
            x += speed * Math.cos(radAngle);
            y += speed * Math.sin(radAngle);
    }

    /**
     * the type of the obstacle
     * @return a string bird
     */
    @Override
    public String getType() {
        return "Bird";
    }
}
