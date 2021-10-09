package nick.dimitriou.objects;

import nick.dimitriou.GUI.MyCanvas;

/**
 * Class for a shielded drone
 * @author Nick Dimitriou
 */
public class ShieldedDrone extends Drone{
    private static int counter = 0;// counter for the number of shielded drones created

    /**
     * Create a shielded drone at the position x,y of the size rad with a speed
     * @param x the width position
     * @param y the height position
     * @param rad the size
     * @param droneSpeed the speed
     */
    public ShieldedDrone(double x, double y, double rad, double droneSpeed) {
        super(x, y, rad,droneSpeed,'r');
        counter++;
    }

    /**
     * the type of the drone
     * @return a string with the type
     */
    @Override
    public String getType() {
        return "Shielded Drone";
    }

    /**
     * draw the shielded drone
     * @param myCanvas the canvas to draw the object
     */
    public void drawObject(MyCanvas myCanvas){
        myCanvas.showCircle(x, y, rad, 's');//the outsize circle
        myCanvas.showCircle(x, y, rad-5 , col);// the inside circle
    }

    /**
     * return how many shielded drones where created
     * @return
     */
    public static int getCounter() {
        return counter;
    }

    /**
     * resets the shielded counter
     */
    public static void resetShieldedCounter(){
        counter = 0;
    }
}
