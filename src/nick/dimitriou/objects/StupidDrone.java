package nick.dimitriou.objects;

/**
 * Class for the stupid drone
 * @author Nick Dimitriou
 */
public class StupidDrone extends Drone {
    private static int counter = 0;// how many stupid drones where created

    /**
     * Create a shielded drone at the position x,y of the size rad with a speed
     * @param x the width position
     * @param y the height position
     * @param rad the size
     * @param droneSpeed
     */
    public StupidDrone(double x, double y, double rad, double droneSpeed) {
        super(x, y, rad, droneSpeed, 'y');
        counter++;
    }

    /**
     * the type of the drone
     * @return a string with the type
     */
    @Override
    public String getType() {
        return "Stupid Drone";
    }

    /**
     * return the string description of the object
     * @return
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * return how many stupid drones where created
     * @return
     */
    public static int getCounter() {
        return counter;
    }
    /**
     * reset the obstacle counter
     */
    public static void resetStupidCounter(){
        counter = 0;
    }

}